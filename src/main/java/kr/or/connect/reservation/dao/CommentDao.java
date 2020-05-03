package kr.or.connect.reservation.dao;

import static kr.or.connect.reservation.dao.CommentDaoSqls.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.or.connect.reservation.dto.Comment;

@Repository
public class CommentDao {
	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<Comment> rowMapper = BeanPropertyRowMapper.newInstance(Comment.class);
	
	public CommentDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}
	
	public List<Comment> selectComments(int num) {
		Map<String, Integer> params = new HashMap<>();
		params.put("num", num);
		return jdbc.query(SELECT_COMMENTS, params, rowMapper);
	}
	
	public double selectAvgscore(int num) {
		Map<String, Integer> params = new HashMap<>();
		params.put("num", num);
		double avg = Math.round(jdbc.queryForObject(SELECT_AVGSCORE, params, Double.class)*10d)/10.0;
		return avg;
	}
	
}
