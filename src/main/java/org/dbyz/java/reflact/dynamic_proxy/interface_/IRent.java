package org.dbyz.java.reflact.dynamic_proxy.interface_;

/**
 * 租房接口
 *
 * @ClassName: Rent
 * @author: 作者 E-mail <a href="mailto:glxydbyz@gmail.com">Dbyz</a>
 * @version: V1.0
 */
public interface IRent {
	/**
	 * 获取月租
	 * 
	 * @Title: getFeePerMonth
	 * @param @return    
	 * @return: int
	 * @since V1.0
	 */
	int getFeePerMonth();
	
	
	/**
	 * 租房给房客
	 * 
	 * @Title: rentHouse
	 * @param @param money    
	 * @return: void
	 * @since V1.0
	 */
	void rentHouse(int money);
}
