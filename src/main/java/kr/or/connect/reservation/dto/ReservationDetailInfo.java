package kr.or.connect.reservation.dto;

import java.sql.Date;

public class ReservationDetailInfo {
	private int id;
	private int productId;
	private String productDescription;
	private String productContent;
	private String placeName;
	private String reservationName;
	private String reservationTel;
	private String reservationEmail;
	private String homepage;
	private Date reservationDate;
	private Date createDate;
	private Date modifyDate;
	private boolean cancelFlag;
	private int sumPrice=0;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public String getProductDescription() {
		return productDescription;
	}
	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}
	public String getProductContent() {
		return productContent;
	}
	public void setProductContent(String productContent) {
		this.productContent = productContent;
	}
	public String getHomepage() {
		return homepage;
	}
	public void setHomepage(String homepage) {
		this.homepage = homepage;
	}
	public String getPlaceName() {
		return placeName;
	}
	public void setPlaceName(String placeName) {
		this.placeName = placeName;
	}
	public String getReservationName() {
		return reservationName;
	}
	public void setReservationName(String reservationName) {
		this.reservationName = reservationName;
	}
	public String getReservationTel() {
		return reservationTel;
	}
	public void setReservationTel(String reservationTel) {
		this.reservationTel = reservationTel;
	}
	public String getReservationEmail() {
		return reservationEmail;
	}
	public void setReservationEmail(String reservationEmail) {
		this.reservationEmail = reservationEmail;
	}
	public boolean isCancelFlag() {
		return cancelFlag;
	}
	public void setCancelFlag(boolean cancelFlag) {
		this.cancelFlag = cancelFlag;
	}
	public int getSumPrice() {
		return sumPrice;
	}
	public void setSumPrice(int sumPrice) {
		this.sumPrice = sumPrice;
	}
	public Date getReservationDate() {
		return reservationDate;
	}
	public void setReservationDate(Date reservationDate) {
		this.reservationDate = reservationDate;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}
	@Override
	public String toString() {
		return "ReservationDetailInfo [id=" + id + ", productId=" + productId + ", productDescription="
				+ productDescription + ", productContent=" + productContent + ", placeName=" + placeName
				+ ", reservationName=" + reservationName + ", reservationTel=" + reservationTel + ", reservationEmail="
				+ reservationEmail + ", homepage=" + homepage + ", reservationDate=" + reservationDate + ", createDate="
				+ createDate + ", modifyDate=" + modifyDate + ", cancelFlag=" + cancelFlag + ", sumPrice=" + sumPrice
				+ ", getId()=" + getId() + ", getProductId()=" + getProductId() + ", getProductDescription()="
				+ getProductDescription() + ", getProductContent()=" + getProductContent() + ", getHomepage()="
				+ getHomepage() + ", getPlaceName()=" + getPlaceName() + ", getReservationName()="
				+ getReservationName() + ", getReservationTel()=" + getReservationTel() + ", getReservationEmail()="
				+ getReservationEmail() + ", isCancelFlag()=" + isCancelFlag() + ", getSumPrice()=" + getSumPrice()
				+ ", getReservationDate()=" + getReservationDate() + ", getCreateDate()=" + getCreateDate()
				+ ", getModifyDate()=" + getModifyDate() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}
	
	
	
}
