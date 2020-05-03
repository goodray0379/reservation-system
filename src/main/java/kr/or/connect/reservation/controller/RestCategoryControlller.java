package kr.or.connect.reservation.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.or.connect.reservation.dto.Category;
import kr.or.connect.reservation.service.TapService;

@RestController
@RequestMapping(path = "/api/categories")
public class RestCategoryControlller {

	@Autowired
	TapService tapService;

	@GetMapping
	public Map<String, Object> getCategories() {

		List<Category> categoryList = tapService.getCount();
		int caegorySize = tapService.getCategoriesCount();

		Map<String, Object> categoryMap = new HashMap<>();

		categoryMap.put("items", categoryList);
		categoryMap.put("size", caegorySize);

		return categoryMap;
	}
}
