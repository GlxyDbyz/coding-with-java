coding-with-java
====
一起学习 Java8 新特性、多线程和其他好玩的事情，当然是和Java相关的(持续更新)

## Java 8 编程新特性基本更新完成
	主要是Lambda表达式，简化了编程org.dbyz.java8

## Java 7 编程特性基本更新完成
	这部分是不是有点老，将就看看吧org.dbyz.java7

## Java 中的自带的数据结构解析【见中文代码注释】
#### package：org.dbyz.java.datastructure (基于Java 1.8.0_31 待更新...ing)
	ArrayList
	LinkedList
	HashMap
	LinkedHashMap
	Hashtable
	HashSet
	LinkedHashSet

#### package：org.dbyz.java.datastructure.\_1\_6(基于Java 1.6.0_18 待更新...ing)
	ArrayList 
	LinkedList 
	HashMap 
	LinkedHashMap 
	Hashtable 
	HashSet 
	LinkedHashSet 


## 多线程相关 
#### Thread&Object
	1.新建线程:SimpleThreadTest.java
	2.守护线程:DaemonTest.java
	3.线程同步:TestSynchronized.java
	4.线程等待和唤醒:WaitAndNotifyTest.java & WaitAndNotifyTest2.java
	5.线程中断:InterrupteTest.java
	6.线程合并:JoinTest.java
	7.线程让步:YeildTest.java
	更新ing...

#### JUC框架
	1.数组和链表 实现的阻塞队列 :ArrayBlockingQueueTest.java & LinkedBlockingQueueTest.java
	2.普通HashMap和同步的ConcurrentHashMap简单对比:ConcurrentHashMapTest.java
	3.普通LinkedBlockingQueue和同步的ConcurrentLinkedQueue简单对比:ConcurrentLinkedQueueTest.java
	4.可重入锁简单例子:ReentrantLockTest.java
	5.线程信号量简单例子:SemaphoreTest.java
     
## 其他
#### Java反射
	简单例子、反射写的一个Java toString 例子:ObjectToStringUtil.java

#### Java中的引用
	1.强引用:StrongReferenceTest.java
	2.软引用:SoftReferenceTest.java
	3.弱引用:WeakReferenceTest.java
	4.虚引用:PhantomReferenceTest.java

#### Jaxb使用(自己写的Jaxb简单工具类)
	实现对象和XML之间的转换,对象和XML文件之间转换,以及从URL获取XML并转换成对象:JaxbUtil.java

#### 框架使用 
##### commons-codec
	1.BASE64、MD2、MD5、SHA1、SHA224、SHA256、SHA384、SHA512算法的Java实现DigestTest.java

##### JMS
	1.ActiveMQ和RabbitMQ 入门例子
	org.dbyz.frameworks.activemq.demo1
	org.dbyz.frameworks.rabbitmq.demo1
	2.ActiveMQ和RabbitMQ 简单封装
	org.dbyz.frameworks.activemq.demo2
	org.dbyz.frameworks.rabbitmq.demo2

##### Spring
	1.Spring AOP 简单例子`package`:org.dbyz.frameworks.spring.aop
	2.Spring 定时任务简单例子`package`:org.dbyz.frameworks.spring.schedule

##### MongoDB
	1.MongoDB简单例子:org.dbyz.frameworks.mongo.SimpleMongoTest.java
	2.MongoDB增删改查例子:org.dbyz.frameworks.mongo.MongoCRUDTest.java

##### POI
	1.excel简单读写操作:org.dbyz.frameworks.poi.ReadExcleTest.java & WriteExcleTest.java
	2.word简单读取操作:org.dbyz.frameworks.poi.ReadWordTest.java

#### 其他待更新...

![Dbyz](https://avatars2.githubusercontent.com/u/6849536?v=3&s=64 "Dbyz") 