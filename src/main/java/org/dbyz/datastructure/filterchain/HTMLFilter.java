package org.dbyz.datastructure.filterchain;

/**
 * 模拟过滤器2
 *
 * @ClassName: HTMLFilter
 * @author: 作者 E-mail <a href="mailto:845927437@qq.com">Dbyz</a>
 * @version: V1.0
 */
public class HTMLFilter implements Filter {
	@Override
	public void doFilter(Request req, Response res, FilterChain chain) {
		req.setContent(req.getContent().replace("<", "["));
		req.setContent(req.getContent().replace(">", "]"));
		chain.doFilter(req, res, chain);
		res.setContent(res.getContent().replace("<", "["));
		res.setContent(res.getContent().replace(">", "]"));
	}
}
