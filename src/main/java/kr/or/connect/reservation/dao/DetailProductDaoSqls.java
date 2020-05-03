package kr.or.connect.reservation.dao;

public class DetailProductDaoSqls {
	
	public static final String SELECT_PRODUCT ="SELECT p.id, p.category_id, d.id display_info_id, c.name, \r\n" + 
			"p.description, p.content, p.event, d.opening_hours, d.place_name, d.place_lot, d.place_street,\r\n" + 
			"d.tel, d.homepage, d.email, d.create_date, d.modify_date\r\n" + 
			"FROM product p, display_info d, category c\r\n" + 
			"WHERE p.id = d.product_id AND p.category_id = c.id AND d.id = :num;";
	
}
