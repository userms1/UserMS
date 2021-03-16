package com.infosys.fa4project.Validator;

public class Validate {

	public static Boolean validateName(String name){
		String regex = "^[\\p{L} .'-]+$";
		if(name.matches(regex)) {
			return true;
		}
		return false;
	}
	
	public static Boolean validateEmail(String email) {
		String regex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";
		if(email.matches(regex)){
			return true;
		}
		return false;
	}
	
	public static Boolean validateContactNumber(Long phoneNumber) {
		if(phoneNumber.toString().length()==10) {
			return true;
		}
		return false;
	}
	
	public static Boolean validatePassword(String password) {
		String regex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&-+=()])(?=\\S+$).{8,20}$";
		if(password.matches(regex)) {
			return true;
		}
		return false;
	}
	
}
