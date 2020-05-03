package kr.or.connect.reservation.dto;

import java.util.List;

public class ReservationParam {
	private int displayInfoId;
	private int productId;
	private String reservationName;
	private String reservationTelephone;
	private String reservationEmail;
	private String reservationYearMonthDay;
	private List<ReservationPrice> prices;
	
	
	public int getDisplayInfoId() {
		return displayInfoId;
	}
	public void setDisplayInfoId(int displayInfoId) {
		this.displayInfoId = displayInfoId;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public String getReservationName() {
		return reservationName;
	}
	public void setReservationName(String reservationName) {
		this.reservationName = reservationName;
	}
	public String getReservationTelephone() {
		return reservationTelephone;
	}
	public void setReservationTelephone(String reservationTelephone) {
		this.reservationTelephone = reservationTelephone;
	}
	public String getReservationEmail() {
		return reservationEmail;
	}
	public void setReservationEmail(String reservationEmail) {
		this.reservationEmail = reservationEmail;
	}
	public String getReservationYearMonthDay() {
		return reservationYearMonthDay;
	}
	public void setReservationYearMonthDay(String reservationYearMonthDay) {
		this.reservationYearMonthDay = reservationYearMonthDay;
	}
	public List<ReservationPrice> getPrices() {
		return prices;
	}
	public void setPrices(List<ReservationPrice> prices) {
		this.prices = prices;
	}
	
	
	
}
