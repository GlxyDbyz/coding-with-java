package org.dbyz.java.reflact.dynamic_proxy.interface_;
/**
 * 租客
 *
 * @ClassName: Customer
 * @author: 作者 E-mail <a href="mailto:glxydbyz@gmail.com">Dbyz</a> 
 * @version: V1.0
 */
public interface ICustomer {
	/**
	 * 获取心理价位
	 * 
	 * @Title: getPsychologicalPrice
	 * @param @return    
	 * @return: int
	 * @since V1.0
	 */
	int getPsychologicalPrice();
	
	/**
	 * 获取实际价位
	 * 
	 * @Title: getRealPrice
	 * @param @return    
	 * @return: int
	 * @since V1.0
	 */
	int getRealPay();
}
