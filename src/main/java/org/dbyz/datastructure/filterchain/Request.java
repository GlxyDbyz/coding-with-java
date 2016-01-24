package org.dbyz.datastructure.filterchain;

/**
 * 模拟请求
 *
 * @ClassName: Request
 * @author: 作者 E-mail <a href="mailto:glxydbyz@gmail.com">Dbyz</a>
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
