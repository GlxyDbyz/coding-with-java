package org.dbyz.datastructure.filterchain;

/**
 * 模拟请求
 *
 * @ClassName: Request
 * @author: 作者 E-mail <a href="mailto:845927437@qq.com">Dbyz</a>
 * @version: V1.0
 */
public class Request {
	private String content;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Request(String content) {
		super();
		this.content = content;
	}
}
