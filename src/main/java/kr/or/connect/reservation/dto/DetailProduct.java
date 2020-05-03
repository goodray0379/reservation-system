package kr.or.connect.reservation.dto;

import java.sql.Date;

public class DetailProduct {
	private int id;
	private int categoryId;
	private int displayInfoId;
	private String name;
	private String description;
	private String content;
	private String event;
	private String openingHoures;
	private String placeName;
	private String placeLot;
	private String placeStreet;
	private String tel;
	private String homepage;
	private String email;
	private Date createDate;
	private Date modifyDate;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	public int getDisplayInfoId() {
		return displayInfoId;
	}
	public void setDisplayInfoId(int displayInfoId) {
		this.displayInfoId = displayInfoId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getEvent() {
		return event;
	}
	public void setEvent(String event) {
		this.event = event;
	}
	public String getOpeningHoures() {
		return openingHoures;
	}
	public void setOpeningHoures(String openingHoures) {
		this.openingHoures = openingHoures;
	}
	public String getPlaceName() {
		return placeName;
	}
	public void setPlaceName(String placeName) {
		this.placeName = placeName;
	}
	public String getPlaceLot() {
		return placeLot;
	}
	public void setPlaceLot(String placeLot) {
		this.placeLot = placeLot;
	}
	public String getPlaceStreet() {
		return placeStreet;
	}
	public void setPlaceStreet(String placeStreet) {
		this.placeStreet = placeStreet;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getHomepage() {
		return homepage;
	}
	public void setHomepage(String homepage) {
		this.homepage = homepage;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
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
		return "DetailProduct [id=" + id + ", categoryId=" + categoryId + ", displayInfoId=" + displayInfoId + ", name="
				+ name + ", description=" + description + ", content=" + content + ", event=" + event
				+ ", openingHoures=" + openingHoures + ", placeName=" + placeName + ", placeLot=" + placeLot
				+ ", placeStreet=" + placeStreet + ", tel=" + tel + ", homepage=" + homepage + ", email=" + email
				+ ", createDate=" + createDate + ", modifyDate=" + modifyDate + ", getId()=" + getId()
				+ ", getCategoryId()=" + getCategoryId() + ", getDisplayInfoId()=" + getDisplayInfoId() + ", getName()="
				+ getName() + ", getDescription()=" + getDescription() + ", getContent()=" + getContent()
				+ ", getEvent()=" + getEvent() + ", getOpeningHoures()=" + getOpeningHoures() + ", getPlaceName()="
				+ getPlaceName() + ", getPlaceLot()=" + getPlaceLot() + ", getPlaceStreet()=" + getPlaceStreet()
				+ ", getTel()=" + getTel() + ", getHomepage()=" + getHomepage() + ", getEmail()=" + getEmail()
				+ ", getCreateDate()=" + getCreateDate() + ", getModifyDate()=" + getModifyDate() + ", getClass()="
				+ getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}
	
}
