package com.infosys.fa4project.User.dto;

public class LoginDTO {

	Long phoneNumber;
	String password;
	
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
		this.password = password;
	}
	public LoginDTO() {
		super();
	}
	
	@Override
	public String toString() {
		return "LoginDTO [phoneNumber=" + phoneNumber + ", password=" + password + "]";
	}

	
}
