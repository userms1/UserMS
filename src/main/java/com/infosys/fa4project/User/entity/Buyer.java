package com.infosys.fa4project.User.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="buyer")
public class Buyer {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="buyerid", nullable=false)
	Integer buyerId;
	@Column(name="name", nullable=false, length=50)
	String name;
	@Column(name="email", nullable=false, length=50)
	String email;
	@Column(name="phonenumber", nullable=false, length=15)
	Long phoneNumber;
	@Column(name="password", nullable=false, length=50)
	String password;
	@Column(name="isprivileged", nullable=false)
	Boolean isPrivileged;
	@Column(name="rewardpoints", nullable=false)
	Integer rewardPoints;
	@Column(name="isactive", nullable=false)
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
	public void setPhoneNumber(Long phoneNumber) {
		this.phoneNumber = phoneNumber;
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
	
}
