package com.infosys.fa4project.User.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.infosys.fa4project.User.dto.BuyerDTO;
import com.infosys.fa4project.User.dto.LoginDTO;
import com.infosys.fa4project.User.entity.Buyer;
import com.infosys.fa4project.User.repository.BuyerRepository;
import com.infosys.fa4project.Validator.Validator;

@Service
public class BuyerService {

Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	BuyerRepository buyerRepo;
	
	@Autowired
	Environment env;
	
	public List<BuyerDTO> fetchBuyer(){
		List<Buyer> buyers = buyerRepo.findAll();
		List<BuyerDTO> buyersDTO = new ArrayList<>();
		for(Buyer buyer:buyers) {
			BuyerDTO buyerDTO = BuyerDTO.valueOf(buyer);
			buyersDTO.add(buyerDTO);
		}
		return buyersDTO;
	}
	
	public BuyerDTO getBuyerById(Integer buyerId){
		BuyerDTO buyerDTO = null;
		Optional<Buyer> buyerOpt = buyerRepo.findById(buyerId);
		if(buyerOpt.isPresent()) {
			Buyer buyer = buyerOpt.get();
			buyerDTO = BuyerDTO.valueOf(buyer);
		}
		return buyerDTO;
	}
	
	public Integer registerBuyer(BuyerDTO buyerDTO) throws Exception {
		Integer buyerId = null;
		try {
			logger.info("Creation request for buyer");
			List<BuyerDTO> buyerDTOList = fetchBuyer();
			Validator.validateBuyer(buyerDTOList, buyerDTO);
			buyerDTO.setIsPrivileged(false);
			buyerDTO.setIsActive(true);
			Buyer buyer = buyerDTO.createEntity();
			buyerRepo.save(buyer);
			buyerId = buyer.getBuyerId();
			logger.info("Created buyer with id {}", buyerId);
		} catch (Exception e) {
			String errorMessage = env.getProperty(e.getMessage());
			logger.error(errorMessage);
			throw new Exception(errorMessage);
		}
		return buyerId;
	}
	
	public void login(LoginDTO loginDTO) throws Exception {
		logger.info("Login request for buyer with phone number {}", loginDTO.getPhoneNumber());
		Optional<Buyer> optBuyer = buyerRepo.findByPhoneNumber(loginDTO.getPhoneNumber());
		if (!optBuyer.isPresent()) {
			String errorMessage = "Service.USER_NOT_FOUND";
			logger.error(errorMessage);
			throw new Exception(errorMessage);
		}
		Buyer buyer = optBuyer.get();
		if (!buyer.getPassword().equals(loginDTO.getPassword())) {
			String errorMessage = "Service.INCORRECT_PASSWORD";
			logger.error(errorMessage);
			throw new Exception(errorMessage);
		}
	}
	
	public void makePrivileged(Integer buyerId) throws Exception {
		logger.info("Request for privilege buyer");
		Optional<Buyer> buyerOpt = buyerRepo.findById(buyerId);
		if(buyerOpt.isPresent()) {
			Buyer buyer = buyerOpt.get();
			if(buyer.getRewardPoints() >= 10000) {
				buyer.setIsPrivileged(true);
				logger.info("Request sucessfull");
			} else {
				String errorMessage = "Service.INSUFFICIENT_PRIVILEGE_CONDITION";
				logger.error(errorMessage);
				throw new Exception(errorMessage);
			}
		}
	}
	
	public void deactivateAccount(Integer buyerId) {
		Optional<Buyer> buyerOpt = buyerRepo.findById(buyerId);
		if(buyerOpt.isPresent()) {
			Buyer buyer = buyerOpt.get();
			buyer.setIsActive(false);
			buyerRepo.save(buyer);
		}
	}
}
