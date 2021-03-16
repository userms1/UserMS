package com.infosys.fa4project.Validator;

import java.util.List;

import com.infosys.fa4project.User.dto.BuyerDTO;
import com.infosys.fa4project.User.dto.SellerDTO;

public class Validator {

	public static void validateBuyer(List<BuyerDTO> buyerDTOList, BuyerDTO buyerDTO) throws Exception{
		if(!Validate.validateName(buyerDTO.getName())) {
			throw new Exception("Validator.INVALID_NAME");
		}
		if(!Validate.validateEmail(buyerDTO.getEmail())) {
			throw new Exception("Validator.INVALID_EMAIL");
		}
		if(!Validate.validateContactNumber(buyerDTO.getPhoneNumber())) {
			throw new Exception("Validator.INVALID_CONTACTNUMBER");
		}
		if(!Validate.validatePassword(buyerDTO.getPassword())) {
			throw new Exception("Validator.INVALID_PASSWORD");
		}
		for (BuyerDTO buyerDTO2 : buyerDTOList) {
			if(buyerDTO2.getPhoneNumber().equals(buyerDTO.getPhoneNumber())){
				throw new Exception("Validator.CONTACTNUMBER_ALREADY_PRESENT");
			}
		}
	}
	
	public static void validateSeller(List<SellerDTO> sellerDTOList, SellerDTO sellerDTO) throws Exception{
		if(!Validate.validateName(sellerDTO.getName())) {
			throw new Exception("Validator.INVALID_NAME");
		}
		if(!Validate.validateEmail(sellerDTO.getEmail())) {
			throw new Exception("Validator.INVALID_EMAIL");
		}
		if(!Validate.validateContactNumber(sellerDTO.getPhoneNumber())) {
			throw new Exception("Validator.INVALID_CONTACTNUMBER");
		}
		if(!Validate.validatePassword(sellerDTO.getPassword())) {
			throw new Exception("Validator.INVALID_PASSWORD");
		}
		for (SellerDTO sellerDTO2 : sellerDTOList) {
			if(sellerDTO2.getPhoneNumber().equals(sellerDTO.getPhoneNumber())){
				throw new Exception("Validator.CONTACTNUMBER_ALREADY_PRESENT");
			}
		}
	}
}
