package org.dbyz.frameworks.zookeeper;

import java.io.IOException;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;
import org.junit.Test;

/**
 * ZooKeeper 测试
 *
 * @ClassName: Consumer
 * @author: 作者 E-mail <a href="mailto:glxydbyz@gmail.com">Dbyz</a>
 * @version: V1.0
 */
public class ZooKeeperTest {

    @Test
    public void link() throws IOException, KeeperException, InterruptedException {
        ZooKeeper zk = new ZooKeeper("127.0.0.1:2181", 200, new Watcher() {
            public void process(WatchedEvent event) {
                // 监控所有被触发的事件
                System.out.println(
                        "event:" + event.getType() + ",path:" + event.getPath());
            }
        });

        // 创建一个目录节点
        Stat stat = zk.exists("/rootPath", false);
        if (stat == null) {
            zk.create("/rootPath", "rootData".getBytes(), Ids.OPEN_ACL_UNSAFE,
                    CreateMode.PERSISTENT);
        }

        // 创建一个子目录节点
        stat = zk.exists("/rootPath/childPath1", false);
        if (stat == null) {
            zk.create("/rootPath/childPath1", "childData1".getBytes(),
                    Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        }

        System.out.println(new String(zk.getData("/rootPath", false, null)));

        // 取出子目录节点列表
        System.out.println(zk.getChildren("/rootPath", true));

        // 修改子目录节点数据
        zk.setData("/rootPath/childPath1", "modifyChildData1".getBytes(), -1);

        System.out.println("目录节点状态：[" + zk.exists("/rootPath", true) + "]");

        // 创建另外一个子目录节点
        zk.create("/rootPath/childPath2", "childData2".getBytes(),
                Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);

        System.out
                .println(new String(zk.getData("/rootPath/childPath2", true, null)));

        // 删除子目录节点
        zk.delete("/rootPath/childPath1", -1);
        zk.delete("/rootPath/childPath2", -1);

        // 删除父目录节点
        zk.delete("/rootPath", -1);

        // 关闭连接
        zk.close();
    }
}
