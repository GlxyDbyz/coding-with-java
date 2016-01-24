package org.dbyz.datastructure.filterchain;

/**
 * 模拟过滤器1
 *
 * @ClassName: BadWordFilter
 * @author: 作者 E-mail <a href="mailto:glxydbyz@gmail.com">Dbyz</a>
 * @version: V1.0
 */
public class BadWordFilter implements Filter {

	@Override
	public void doFilter(Request req, Response res, FilterChain chain) {
		req.setContent(req.getContent().replace("bad", "***"));
		chain.doFilter(req, res, chain);
		res.setContent(res.getContent().replace("bad", "***"));
	}

}
