package com.infosys.fa4project.User.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="seller")
public class Seller {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="sellerid", nullable=false)
	Integer sellerId;
	@Column(name="name", nullable=false, length=50)
	String name;
	@Column(name="email", nullable=false, length=50)
	String email;
	@Column(name="phonenumber", nullable=false, length=15)
	Long phoneNumber;
	@Column(name="password", nullable=false, length=50)
	String password;
	@Column(name="isactive", nullable=false)
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
	public void setPhoneNumber(Long phoneNumber) {
		this.phoneNumber = phoneNumber;
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
}
