package kr.or.connect.reservation.dao;

public class ProductImageDaoSqls {
	public static final String SELECT_PRODUCT_IMAGES ="SELECT pi.product_id, pi.id product_image_id, pi.type, pi.file_id file_info_id,\r\n" + 
			"fi.file_name, fi.save_file_name, fi.content_type, fi.delete_flag, fi.create_date, fi.modify_date\r\n" + 
			"FROM product_image as pi, file_info as fi\r\n" + 
			"where type != \"th\" and pi.file_id = fi.id AND pi.product_id in \r\n" + 
			"(SELECT product_id\r\n" + 
			"FROM display_info\r\n" + 
			"where display_info.id = :num)\r\n"+
			"ORDER BY type desc limit 2;";
}
