package kr.or.connect.reservation.dao;

import static kr.or.connect.reservation.dao.ProductImageDaoSqls.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.or.connect.reservation.dto.ProductImage;

@Repository
public class ProductImageDao {
	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<ProductImage> rowMapper = BeanPropertyRowMapper.newInstance(ProductImage.class);
	
	public ProductImageDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}
	
	public List<ProductImage> selectProductImages(int num){
		Map<String, Integer> params = new HashMap<>();
		params.put("num", num);
		List<ProductImage> value = jdbc.query(SELECT_PRODUCT_IMAGES, params, rowMapper);
		if(value.size()>1) {
			Collections.reverse(value);
		}
		return value;
	}
}
