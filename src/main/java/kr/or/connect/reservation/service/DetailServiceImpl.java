package kr.or.connect.reservation.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.connect.reservation.dao.DetailProductDao;
import kr.or.connect.reservation.dao.DisplayInfoImageDao;
import kr.or.connect.reservation.dao.ProductImageDao;
import kr.or.connect.reservation.dao.ProductPriceDao;
import kr.or.connect.reservation.dto.DetailProduct;
import kr.or.connect.reservation.dto.DisplayInfoImage;
import kr.or.connect.reservation.dto.ProductImage;
import kr.or.connect.reservation.dto.ProductPrice;
import kr.or.connect.reservation.service.DetailService;

@Service
public class DetailServiceImpl implements DetailService {

	@Autowired
	DetailProductDao detailproductDao;
	
	@Autowired
	ProductImageDao prodcutImageDao;
	
	@Autowired
	DisplayInfoImageDao displayInfoImageDao;
	
	@Autowired
	ProductPriceDao productPriceDao;

	
	@Override
	public DetailProduct getDetailProduct(int num) {
		return detailproductDao.selectProduct(num);
	}

	@Override
	public List<ProductImage> getProductImage(int num) {
		return prodcutImageDao.selectProductImages(num);
	}

	@Override
	public List<DisplayInfoImage> getDisplayInfoImage(int num) {
		return displayInfoImageDao.selectDisplayInfoImage(num);
	}

	@Override
	public List<ProductPrice> getProductPrice(int num) {
		return productPriceDao.selectProductPrice(num);
	}

}
