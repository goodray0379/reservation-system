package kr.or.connect.reservation.dao;

import static kr.or.connect.reservation.dao.CategoryDaoSqls.*;

import java.util.Collections;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.or.connect.reservation.dto.Category;

@Repository
public class CategoryDao {
	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<Category> rowMapper = BeanPropertyRowMapper.newInstance(Category.class);

	public CategoryDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}

	public List<Category> selectCount() {
		return jdbc.query(SELECT_COUNT, Collections.emptyMap(), rowMapper);
	}

	public int selectCategoryCount() {
		return jdbc.queryForObject(SELECT_CATEGORY_COUNT, Collections.emptyMap(), Integer.class);
	}

}
