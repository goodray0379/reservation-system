package kr.or.connect.reservation.dao;

import static kr.or.connect.reservation.dao.PromotionDaoSqls.*;

import java.util.Collections;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.or.connect.reservation.dto.Promotion;

@Repository
public class PromotionDao {
	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<Promotion> rowMapper = BeanPropertyRowMapper.newInstance(Promotion.class);
	
	public PromotionDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}
	
	public List<Promotion> selectPromotion(){
		return jdbc.query(SELECT_PROMOTION, Collections.emptyMap(), rowMapper);	
	}
	
	public int selectPromotionCount() {
		return jdbc.queryForObject(SELECT_PROMOTION_COUNT, Collections.emptyMap(), Integer.class);
	}
}
