package kr.or.connect.reservation.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.connect.reservation.service.ReservationService;

@Controller
public class LoginController {
	@Autowired
	ReservationService reservationService;
	
	@GetMapping(path="/login")
	public String login(@RequestParam(name="resrv_email", required=true) String email, 
			HttpSession session,
			RedirectAttributes redirectAttr) {
		
		if(reservationService.isThereEmail(email)) {
			session.setAttribute("email", email);
		}else {
			redirectAttr.addFlashAttribute("errorMessage","이메일을 찾을 수 없습니다.");
			return "redirect:/bookinglogin";
		}
		return "redirect:/myreservation?reservationEmail=" + session.getAttribute("email");
	}
}
