package kr.or.connect.reservation.dao;

public class CategoryDaoSqls {
	
	public static final String SELECT_COUNT = "SELECT p.category_id id, name, count(*) as count " +
			"FROM product as p inner join category as c on p.category_id=c.id, display_info as d " +
			"WHERE p.id = d.product_id " +
			"GROUP BY p.category_id";
	
	public static final String SELECT_CATEGORY_COUNT = "SELECT COUNT(*) " + 
			"FROM category";
}
