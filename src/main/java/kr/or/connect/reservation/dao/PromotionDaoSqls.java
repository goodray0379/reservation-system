package kr.or.connect.reservation.dao;

public class PromotionDaoSqls {
	public static final String SELECT_PROMOTION = "SELECT pm.id, pd.id product_id, pd.category_id, ct.name category_name, pd.description, pi.id product_image_id\r\n" + 
			"FROM promotion as pm, product as pd, category as ct, product_image as pi\r\n" + 
			"WHERE pm.product_id = pd.id AND pd.category_id = ct.id AND pd.id = pi.product_id\r\n" + 
			"AND pi.type='ma';";
	
	public static final String SELECT_PROMOTION_COUNT = "select count(*) from promotion;";
}
