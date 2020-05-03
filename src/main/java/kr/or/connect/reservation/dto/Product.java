package kr.or.connect.reservation.dto;

import java.sql.Date;

public class Product {
	
	private int id;
	private int displayInfoId;
	private int categoryId;
	private String description;
	private String content ;
	private String event;
	private Date createDate; 
	private Date modifyDate;
	private String placeName;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getDisplayInfoId() {
		return displayInfoId;
	}
	public void setDisplayInfoId(int displayInfoId) {
		this.displayInfoId = displayInfoId;
	}
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
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
	public String getPlaceName() {
		return placeName;
	}
	public void setPlaceName(String placeName) {
		this.placeName = placeName;
	}
	
	@Override
	public String toString() {
		return "Product [id=" + id + ", displayInfoId=" + displayInfoId + ", categoryId=" + categoryId
				+ ", description=" + description + ", content=" + content + ", event=" + event + ", createDate="
				+ createDate + ", modifyDate=" + modifyDate + ", placeName=" + placeName + "]";
	}
	
	
}
