package com.infosys.fa4project.User.dto;

import com.infosys.fa4project.User.entity.Seller;

public class SellerDTO {

	Integer sellerId;
	String name;
	String email;
	Long phoneNumber;
	String password;
	Boolean isActive;
	
	public Integer getSellerId() {
		return sellerId;
	}
	public void setSellerId(Integer sellerId) {
		this.sellerId=sellerId;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name=name;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email=email;
	}
	
	public Long getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(Long integer) {
		this.phoneNumber = integer;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password=password;
	}
	
	public Boolean getIsActive() {
		return isActive;
	}
	public void setIsActive(Boolean isActive) {
		this.isActive=isActive;
	}
	
	public static SellerDTO valueOf(Seller sell) {
		SellerDTO sellerDTO = new SellerDTO();
		sellerDTO.setSellerId(sell.getSellerId());
		sellerDTO.setEmail(sell.getEmail());
		sellerDTO.setIsActive(sell.getIsActive());
		sellerDTO.setName(sell.getName());
		sellerDTO.setPassword(sell.getPassword());
		sellerDTO.setPhoneNumber(sell.getPhoneNumber());
		
		
		return sellerDTO;
	}
	
	public Seller createEntity() {
		Seller sell = new Seller();
	
		sell.setEmail(this.getEmail());
		sell.setIsActive(this.isActive);
		sell.setName(this.getName());
		sell.setPassword(this.getPassword());
		sell.setPhoneNumber(this.getPhoneNumber());
		
		return sell;
	}
	
}
