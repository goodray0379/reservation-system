package kr.or.connect.reservation.dao;

import static kr.or.connect.reservation.dao.ReservationUserCommentImageDaoSqls.*;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.or.connect.reservation.dto.ReservationUserCommentImage;

@Repository
public class ReservationUserCommentImageDao {
	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<ReservationUserCommentImage> rowMapper = BeanPropertyRowMapper.newInstance(ReservationUserCommentImage.class);

	public ReservationUserCommentImageDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}
	
	public ReservationUserCommentImage selectCommentImages(int rid){
		Map<String, Integer> params = new HashMap<>();
		params.put("rid", rid);
		try {
			return jdbc.queryForObject(SELECT_COMMENT_IMAGES, params, rowMapper);
		}
		catch(DataAccessException e){
			return null;
		}
	}
}
