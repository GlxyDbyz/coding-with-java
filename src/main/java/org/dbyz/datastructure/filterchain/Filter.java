package org.dbyz.datastructure.filterchain;

/**
 * 过滤器
 *
 * @ClassName: Filter
 * @author: 作者 E-mail <a href="mailto:845927437@qq.com">Dbyz</a>
 * @version: V1.0
 */
public interface Filter {
	void doFilter(Request req, Response res, FilterChain chain);
}
