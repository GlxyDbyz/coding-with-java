package org.dbyz.datastructure.filterchain;

import java.util.List;

/**
 * 过滤器链
 *
 * @ClassName: FilterChain
 * @author: 作者 E-mail <a href="mailto:glxydbyz@gmail.com">Dbyz</a>
 * @version: V1.0
 */
public class FilterChain implements Filter {
	private int index = 0;

	private List<Filter> filters;

	public FilterChain(List<Filter> filters) {
		super();
		this.filters = filters;
	}

	public List<Filter> getFilters() {
		return filters;
	}

	public void setFilters(List<Filter> filters) {
		this.filters = filters;
	}

	@Override
	public void doFilter(Request req, Response res, FilterChain chain) {
		while (index < chain.filters.size()) {
			chain.getFilters().get(index++).doFilter(req, res, this);// this==chain
			// chain.getFilters().get(index ++ ).doFilter(req, res, chain);
		}
	}
}
