package com.infosys.fa4project.User.dto;

import com.infosys.fa4project.User.entity.Buyer;

public class BuyerDTO {

	Integer buyerId;
	String name;
	String email;
	Long phoneNumber;
	String password;
	Boolean isPrivileged;
	Integer rewardPoints;
	Boolean isActive;
	
	public Integer getBuyerId() {
		return buyerId;
	}
	public void setBuyerId(Integer buyerId) {
		this.buyerId=buyerId;
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
	
	public Boolean getIsPrivileged() {
		return isPrivileged;
	}
	public void setIsPrivileged(Boolean isPrivileged) {
		this.isPrivileged=isPrivileged;
	}
	
	public Integer getRewardPoints() {
		return rewardPoints;
	}
	public void setRewardPoints(Integer rewardPoints) {
		this.rewardPoints=rewardPoints;
	}
	
	public Boolean getIsActive() {
		return isActive;
	}
	public void setIsActive(Boolean isActive) {
		this.isActive=isActive;
	}
	
	public static BuyerDTO valueOf(Buyer buy) {
		BuyerDTO buyerDTO = new BuyerDTO();
		buyerDTO.setBuyerId(buy.getBuyerId());
		buyerDTO.setEmail(buy.getEmail());
		buyerDTO.setIsActive(buy.getIsActive());
		buyerDTO.setIsPrivileged(buy.getIsPrivileged());
		buyerDTO.setName(buy.getName());
		buyerDTO.setPassword(buy.getPassword());
		buyerDTO.setPhoneNumber(buy.getPhoneNumber());
		buyerDTO.setRewardPoints(buy.getRewardPoints());
		
		return buyerDTO;
	}
	
	public Buyer createEntity() {
		Buyer buy = new Buyer();
	
		buy.setEmail(this.getEmail());
		buy.setIsActive(this.isActive);
		buy.setIsPrivileged(this.isPrivileged);
		buy.setName(this.getName());
		buy.setPassword(this.getPassword());
		buy.setPhoneNumber(this.getPhoneNumber());
		buy.setRewardPoints(this.getRewardPoints());
		
		return buy;
	}
	
}
