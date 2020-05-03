package kr.or.connect.reservation.dao;

public class ProductDaoSqls {
	public static final String SELECT_ALL = "SELECT p.id,  p.category_id, d.id as display_info_id, description, d.place_name, p.content "
			+ "FROM product as p inner join display_info as d on p.id=d.product_id "
			+ "ORDER BY id limit :start, :limit";
	
	public static final String SELECT_PART = "SELECT p.id, p.category_id, d.id as display_info_id, p.category_id,  description, d.place_name, p.content "
			+ "FROM product as p inner join display_info as d on p.id=d.product_id "
			+ "WHERE category_id = :categoryId "
			+ "ORDER BY id limit :start, :limit";
	
}
