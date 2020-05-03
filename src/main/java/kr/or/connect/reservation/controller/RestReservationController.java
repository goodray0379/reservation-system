package kr.or.connect.reservation.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.or.connect.reservation.dto.ReservationDetailInfo;
import kr.or.connect.reservation.dto.ReservationParam;
import kr.or.connect.reservation.service.ReservationService;

@RestController
@RequestMapping(path = "/api/reservations")
public class RestReservationController {
	
	@Autowired
	ReservationService reservationService;
	
	
	@GetMapping(produces=MediaType.APPLICATION_JSON_VALUE)
	public Map<String, Object> getReservation(@RequestParam(name = "reservationEmail", required=true) String email) {
		List<ReservationDetailInfo> reservationInfoList = reservationService.getResInfo(email);
		int size = reservationService.getResCount(email)[0];
		Map<String, Object> reservationInfoMap = new HashMap<>();
		reservationInfoMap.put("reservations", reservationInfoList);
		reservationInfoMap.put("size", size);
		
		return reservationInfoMap;
	}
	
	@GetMapping(path = "/count", produces=MediaType.APPLICATION_JSON_VALUE)
	public Map<String, Integer> getCountReservation(@RequestParam(name = "reservationEmail", required=true) String email) {

		int count[] = reservationService.getResCount(email);
		Map<String, Integer> countMap = new HashMap<>();
		countMap.put("reservationCount", count[0]);
		countMap.put("cancelCount", count[1]);
		
		return countMap;
	}

	@PostMapping
	public void reserve(@RequestBody ReservationParam reservationInfo) {

		reservationService.reserve(reservationInfo);
	}
	
	@PutMapping(path = "/{reservationId}")
	public void cancel(@PathVariable(name = "reservationId", required=true) int id) {
		reservationService.cancelReservation(id);
	}
	
}
