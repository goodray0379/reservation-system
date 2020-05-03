package kr.or.connect.reservation.service;

import java.util.List;

import kr.or.connect.reservation.dto.Category;
import kr.or.connect.reservation.dto.Product;

public interface TapService {
	public static final Integer LIMIT = 4;
	public List<Product> getProducts(int category, int start);
	public List<Category> getCount();
	public int getCategoriesCount();
}
