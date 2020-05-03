package kr.or.connect.reservation.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.connect.reservation.dao.CategoryDao;
import kr.or.connect.reservation.dao.ProductDao;
import kr.or.connect.reservation.dto.Category;
import kr.or.connect.reservation.dto.Product;
import kr.or.connect.reservation.service.TapService;

@Service
public class TapServiceImpl implements TapService {
	
	@Autowired
	ProductDao productDao;
	
	@Autowired
	CategoryDao categoryDao;

	@Override
	@Transactional
	public List<Product> getProducts(int categoryId, int start) {
		if(categoryId==0) {
			List<Product> list = productDao.selectAll(start, LIMIT);
			return list;
		}else {
			List<Product> list = productDao.selectPart(categoryId, start, LIMIT);
			return list;
		}
	}
	
	
	@Override
	public List<Category> getCount() {
		return categoryDao.selectCount();
	}
	
	@Override
	public int getCategoriesCount() {
		return categoryDao.selectCategoryCount();
	}

}
