package com.infosys.fa4project.User.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.infosys.fa4project.User.dto.LoginDTO;
import com.infosys.fa4project.User.dto.SellerDTO;
import com.infosys.fa4project.User.entity.Seller;
import com.infosys.fa4project.User.repository.SellerRepository;
import com.infosys.fa4project.Validator.Validator;

@Service
public class SellerService {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	SellerRepository sellRepo;
	
	@Autowired
	Environment env;

	public List<SellerDTO> fetchSeller(){
		List<Seller> sellers = sellRepo.findAll();
		List<SellerDTO> sellersDTO = new ArrayList<>();
		for(Seller seller:sellers) {
			SellerDTO sellerDTO = SellerDTO.valueOf(seller);
			sellersDTO.add(sellerDTO);
		}
		return sellersDTO;
	}
	
	public SellerDTO getSellerById(Integer sellerId){
		SellerDTO sellerDTO = null;
		Optional<Seller> sellerOpt = sellRepo.findById(sellerId);
		if(sellerOpt.isPresent()) {
			Seller seller = sellerOpt.get();
			sellerDTO = SellerDTO.valueOf(seller);
		}
		return sellerDTO;
	}
	
	public Integer registerSeller(SellerDTO sellerDTO) throws Exception {
		Integer sellerId = null;
		try {
			logger.info("Creation request for seller");
			List<SellerDTO> sellerDTOList = fetchSeller();
			Validator.validateSeller(sellerDTOList, sellerDTO);
			Seller seller = sellerDTO.createEntity();
			sellRepo.save(seller);
			sellerId = seller.getSellerId();
			logger.info("Created seller with id {}", sellerId);
		} catch (Exception e) {
			String errorMessage = env.getProperty(e.getMessage());
			logger.error(errorMessage);
			throw new Exception(errorMessage);
		}
		return sellerId;
	}
	
	public void login(LoginDTO loginDTO) throws Exception {
		logger.info("Login request for customer {} with password {}", loginDTO.getPhoneNumber(),loginDTO.getPassword());
		Optional<Seller> optCust = sellRepo.findByPhoneNumber(loginDTO.getPhoneNumber());
		if (!optCust.isPresent()) {
			String errorMessage = "Service.USER_NOT_FOUND";
			logger.error(errorMessage);
			throw new Exception(errorMessage);
		}
		Seller cust = optCust.get();
		if (!cust.getPassword().equals(loginDTO.getPassword())) {
			String errorMessage = "Service.INCORRECT_PASSWORD";
			logger.error(errorMessage);
			throw new Exception(errorMessage);
		}
	}
	
	public void deactivateAccount(Integer sellerId) {
		Optional<Seller> sellerOpt = sellRepo.findById(sellerId);
		if(sellerOpt.isPresent()) {
			Seller seller = sellerOpt.get();
			seller.setIsActive(false);
			sellRepo.save(seller);
		}
	}

}
