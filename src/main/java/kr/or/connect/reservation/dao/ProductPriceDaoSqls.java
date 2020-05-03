package kr.or.connect.reservation.dao;

public class ProductPriceDaoSqls {
	
	public static final String SELECT_PRODUCT_PRICE ="SELECT *\r\n" + 
			"FROM product_price\r\n" + 
			"WHERE product_id \r\n" + 
			"in (SELECT product_id\r\n" + 
			"FROM display_info\r\n" + 
			"where display_info.id = :num);";
	
}
