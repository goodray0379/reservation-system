package kr.or.connect.reservation.dto;

public class Promotion {
	private int id;
	private int productId;
	private int categoryId;
	private String categoryName;
	private String description;
	private int productImageId;
	
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
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getProductImageId() {
		return productImageId;
	}
	public void setProductImageId(int productImageId) {
		this.productImageId = productImageId;
	}
	
	@Override
	public String toString() {
		return "Promotion [id=" + id + ", productId=" + productId + ", categoryId=" + categoryId + ", categoryName="
				+ categoryName + ", description=" + description + ", productImageId=" + productImageId + "]";
	}
	
}
