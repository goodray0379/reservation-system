package kr.or.connect.reservation.service;

import java.util.List;

import kr.or.connect.reservation.dto.DetailProduct;
import kr.or.connect.reservation.dto.DisplayInfoImage;
import kr.or.connect.reservation.dto.ProductImage;
import kr.or.connect.reservation.dto.ProductPrice;

public interface DetailService {
	public DetailProduct getDetailProduct(int num);
	public List<ProductImage> getProductImage(int num);
	public List<DisplayInfoImage> getDisplayInfoImage(int num);
	public List<ProductPrice> getProductPrice(int num);
}
