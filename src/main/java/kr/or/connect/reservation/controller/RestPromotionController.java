package kr.or.connect.reservation.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.or.connect.reservation.dto.Promotion;
import kr.or.connect.reservation.service.PromotionService;

@RestController
@RequestMapping(path = "/api/promotions")
public class RestPromotionController {
	
   @Autowired
   PromotionService promotionService;

   @GetMapping
   public Map<String, Object> getPromotions() {

      List<Promotion> promotionList = promotionService.getPromotion();
      int promotionSize = promotionService.getPromotionCount();

      Map<String, Object> promotionMap = new HashMap<>();
      promotionMap.put("items", promotionList);
      promotionMap.put("size", promotionSize);

      return promotionMap;
   }
   
}