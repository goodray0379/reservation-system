package kr.or.connect.reservation.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.or.connect.reservation.dto.Comment;
import kr.or.connect.reservation.dto.DetailProduct;
import kr.or.connect.reservation.dto.DisplayInfoImage;
import kr.or.connect.reservation.dto.Product;
import kr.or.connect.reservation.dto.ProductImage;
import kr.or.connect.reservation.dto.ProductPrice;
import kr.or.connect.reservation.service.CommentService;
import kr.or.connect.reservation.service.DetailService;
import kr.or.connect.reservation.service.ProductPriceService;
import kr.or.connect.reservation.service.TapService;

@RestController
@RequestMapping(path = "/api/products")
public class RestProductController {

	@Autowired
	TapService tapService;

	@Autowired
	DetailService detailService;

	@Autowired
	CommentService commentService;

	@Autowired
	ProductPriceService productPriceService;
	
	@GetMapping
	public Map<String, Object> getProducts(
			@RequestParam(name = "categoryId", required = false, defaultValue = "0") int categoryId,
			@RequestParam(name = "start", required = false, defaultValue = "0") int start) {

		List<Product> productList = tapService.getProducts(categoryId, start);

		Map<String, Object> productMap = new HashMap<>();

		productMap.put("items", productList);

		return productMap;
	}

	@GetMapping(path = "/{displayinfo}")
	public Map<String, Object> getdisplayinfo(@PathVariable(name = "displayinfo") int dId) {

		DetailProduct product = detailService.getDetailProduct(dId);
		List<ProductImage> productImageList = detailService.getProductImage(dId);
		List<DisplayInfoImage> displayInfoImageList = detailService.getDisplayInfoImage(dId);
		List<ProductPrice> productPriceList = detailService.getProductPrice(dId);
		List<Comment> commentList = commentService.getComments(dId);
		double avgScore = commentService.getAvgscore(dId);

		Map<String, Object> map = new HashMap<>();

		map.put("product", product);
		map.put("productImages", productImageList);
		map.put("displayInfoImages", displayInfoImageList);
		map.put("comments", commentList);
		map.put("avgScore", avgScore);
		map.put("productPrices", productPriceList);
		return map;
	}

	@GetMapping(path = "/prices/{displayinfo}")
	public Map<String, Object> getPrices(@PathVariable(name = "displayinfo") int dId) {
		List<ProductPrice> priceList = productPriceService.getProductPrice(dId);
		Map<String, Object> priceMap = new HashMap<>();
		priceMap.put("price", priceList);

		return priceMap;
	}
}
