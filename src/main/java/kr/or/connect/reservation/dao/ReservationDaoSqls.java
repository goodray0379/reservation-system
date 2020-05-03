package kr.or.connect.reservation.dao;

public class ReservationDaoSqls {
	public static final String SELECT_COUNT_RESERVATION = 
			"SELECT count(*) FROM reservation_info WHERE reservation_email Like :email";
	public static final String SELECT_COUNT_CANCLE =
			"SELECT count(*) FROM reservation_info WHERE reservation_email Like :email AND cancel_flag=1";
	public static final String SELECT_RESERVATION_INFOS =
			"SELECT ri.id as id, ri.product_id as product_id, description as product_description, content as product_content, place_name, homepage, reservation_name, reservation_tel, reservation_email, reservation_date, ri.create_date as create_date, ri.modify_date as modify_date, cancel_flag\r\n" + 
			"FROM reservation_info as ri, product as p, display_info as di\r\n" + 
			"WHERE ri.product_id = di.id and ri.reservation_email = :email and di.product_id = p.id;";
	public static final String SELECT_SUMPRICE =
			"SELECT sum(price * count) as sum_price\r\n" + 
			"FROM reservation_info as ri, product_price as pp, reservation_info_price as rip\r\n" + 
			"WHERE ri.id = rip.reservation_info_id AND rip.product_price_id = pp.id\r\n" + 
			"AND reservation_info_id= :id;";
	public static final String SELECT_RESERVATION = "";
	public static final String UPDATE_RESERVATION_INFO ="UPDATE reservation_info SET cancel_flag = 1 WHERE id = :id;";
}
