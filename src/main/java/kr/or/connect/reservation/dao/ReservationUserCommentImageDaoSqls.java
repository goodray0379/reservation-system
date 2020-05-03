package kr.or.connect.reservation.dao;

public class ReservationUserCommentImageDaoSqls {

	public static final String SELECT_COMMENT_IMAGES ="SELECT *\r\n" + 
			"FROM reservation_user_comment_image ruci, file_info fi\r\n" + 
			"WHERE ruci.file_id = fi.id AND reservation_user_comment_id = :rid;";
	
}
