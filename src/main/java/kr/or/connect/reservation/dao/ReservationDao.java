package kr.or.connect.reservation.dao;

import static kr.or.connect.reservation.dao.ReservationDaoSqls.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import kr.or.connect.reservation.dto.ReservationDetailInfo;
import kr.or.connect.reservation.dto.ReservationParam;
import kr.or.connect.reservation.dto.ReservationResponse;

@Repository
public class ReservationDao {
	private SimpleJdbcInsert resInfoInsertAction;
	private SimpleJdbcInsert priceInfoInsertAction;
	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<ReservationDetailInfo> rowMapper = BeanPropertyRowMapper.newInstance(ReservationDetailInfo.class);
	private Date date = new Date();
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public ReservationDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
		this.resInfoInsertAction = new SimpleJdbcInsert(dataSource)
	                .withTableName("reservation_info")
					.usingGeneratedKeyColumns("id");
		this.priceInfoInsertAction = new SimpleJdbcInsert(dataSource)
                .withTableName("reservation_info_price")
				.usingGeneratedKeyColumns("id");
	}
	
	public int SelectReservationCount(String email){
		Map<String, String> params = new HashMap<>();
		params.put("email", email);
		try {
			int count = jdbc.queryForObject(SELECT_COUNT_RESERVATION, params, Integer.class);
			return count;
		} catch (Exception e) {
			return 0;
		}
	}
	
	public int SelectCancelCount(String email){
		Map<String, String> params = new HashMap<>();
		params.put("email", email);
		try {
			int count = jdbc.queryForObject(SELECT_COUNT_CANCLE, params, Integer.class);
			return count;
		} catch (Exception e) {
			return 0;
		}
	}
	
	public List<ReservationDetailInfo> selectReservationInfos(String email){
		Map<String, String> params = new HashMap<>();
		params.put("email", email);
		return jdbc.query(SELECT_RESERVATION_INFOS, params, rowMapper);
	}
	
	public int selectSumPrice(int reservationInfoId) {
		Map<String, Integer> params = new HashMap<>();
		params.put("id", reservationInfoId);
		try {
			int sumPrice =jdbc.queryForObject(SELECT_SUMPRICE, params, Integer.class);
			return sumPrice;
		} catch (Exception e) {
			return 0;
		}
	}
	
	public Long insertReservationInfo(ReservationParam reservationInfo) {
		Map<String, Object> parameters = new HashMap<>();
	    parameters.put("product_id", reservationInfo.getProductId());
	    parameters.put("display_info_id", reservationInfo.getDisplayInfoId());
	    parameters.put("reservation_name", reservationInfo.getReservationName());
	    parameters.put("reservation_tel", reservationInfo.getReservationTelephone());
	    parameters.put("reservation_email", reservationInfo.getReservationEmail());
	    parameters.put("reservation_date", this.sdf.format(this.date));
	    parameters.put("create_date", this.sdf.format(this.date));
	    parameters.put("modify_date", this.sdf.format(this.date));
	    parameters.put("cancel_flag", 0);
	    return resInfoInsertAction.executeAndReturnKey(parameters).longValue();
	}
	
	public Long insertReservationPrice(Long reservationInfoId, int productPriceId, int count) {
		Map<String, Object> parameters = new HashMap<>();
	    parameters.put("reservation_info_id", reservationInfoId);
	    parameters.put("product_price_id", productPriceId);
	    parameters.put("count", count);
	    return priceInfoInsertAction.executeAndReturnKey(parameters).longValue();
	}
	
	public void updateReservationInfo(int id) {
		Map<String, Integer> params = new HashMap<>();
		params.put("id", id);
		jdbc.update(UPDATE_RESERVATION_INFO, params);
	}
}
