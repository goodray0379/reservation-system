package kr.or.connect.reservation.dao;

import static kr.or.connect.reservation.dao.DetailProductDaoSqls.*;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.or.connect.reservation.dto.DetailProduct;

@Repository
public class DetailProductDao {
	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<DetailProduct> rowMapper = BeanPropertyRowMapper.newInstance(DetailProduct.class);

	public DetailProductDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}

	public DetailProduct selectProduct(int num) {
		Map<String, Integer> params = new HashMap<>();
		params.put("num", num);
		return jdbc.queryForObject(SELECT_PRODUCT, params, rowMapper);
	}

}
