package kr.or.connect.reservation.dao;

public class DisplayInfoImageDaoSqls {
	public static final String SELECT_DISPLAY_INFO_IMAGE ="SELECT d.id, display_info_id, d.file_id, file_name, save_file_name, content_type, delete_flag, create_date, modify_date\r\n" + 
			"FROM display_info_image as d inner join file_info on d.file_id = file_info.id WHERE display_info_id= :num;";
}
