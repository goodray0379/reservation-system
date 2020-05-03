package kr.or.connect.reservation.dao;

public class CommentDaoSqls {
	public static final String SELECT_COMMENTS ="SELECT id, product_id, reservation_info_id, score, comment, create_date, modify_date \r\n" + 
			"FROM reservation_user_comment\r\n" + 
			"WHERE product_id in (SELECT product_id\r\n" + 
			"	FROM display_info\r\n" + 
			"	where display_info.id = 1);";
	
	public static final String SELECT_AVGSCORE ="SELECT avg(score)\r\n" + 
			"FROM reservation_user_comment\r\n" + 
			"where product_id \r\n" + 
			"in (SELECT product_id\r\n" + 
			"FROM display_info\r\n" + 
			"where display_info.id = 1);";
}
