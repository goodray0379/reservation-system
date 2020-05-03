package kr.or.connect.reservation.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.connect.reservation.dao.ProductPriceDao;
import kr.or.connect.reservation.dto.ProductPrice;

@Service
public class ProductPriceServiceImpl implements ProductPriceService {

	@Autowired
	ProductPriceDao productPrice;
	
	@Override
	public List<ProductPrice> getProductPrice(int num) {
		return productPrice.selectProductPrice(num);
	}

}
