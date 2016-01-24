package org.dbyz.datastructure.filterchain;

import java.util.ArrayList;
import java.util.List;

/**
 * 简单测试
 *
 * @ClassName: Test
 * @author: 作者 E-mail <a href="mailto:glxydbyz@gmail.com">Dbyz</a>
 * @version: V1.0
 */
public class TestIt {
	public static void main(String[] args) {
		List<Filter> filters = new ArrayList<Filter>();
		filters.add(new BadWordFilter());
		filters.add(new HTMLFilter());
		
		FilterChain chain = new FilterChain(filters);
		
		Request req = new Request("<script>for(;;;){alert(1);}</script> I think someone is very bad !");
		Response res = new Response("<script>for(;;;){alert(1);}</script> I dont think someone is very bad !");
		
		chain.doFilter(req, res, chain);
		
		System.out.println(req.getContent());
		System.out.println(res.getContent());
	}
}
