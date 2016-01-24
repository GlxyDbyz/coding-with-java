package org.dbyz.datastructure.filterchain;

/**
 * 模拟返回
 *
 * @ClassName: Response
 * @author: 作者 E-mail <a href="mailto:glxydbyz@gmail.com">Dbyz</a>
 * @version: V1.0
 */
public class Response {
	private String content;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Response(String content) {
		super();
		this.content = content;
	}

}
