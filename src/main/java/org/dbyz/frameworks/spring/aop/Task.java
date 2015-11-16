package org.dbyz.frameworks.spring.aop;

import org.springframework.stereotype.Component;

/**
 * 
 *
 * @ClassName: Task
 * @author: 作者 E-mail <a href="mailto:845927437@qq.com">Dbyz</a> 
 * @version: V1.0
 */
@Component("task")
// @Scope(value="prototype")//默认是单例
public class Task {
	private Integer count = 0;

	@Log(level = "ERROR")
	public Long helo(String src) {
		count++;
		System.out.println("run " + count + src);
		return 5L;
	}
}
