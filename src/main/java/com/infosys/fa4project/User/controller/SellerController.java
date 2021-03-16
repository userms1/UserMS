package com.infosys.fa4project.User.controller;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.infosys.fa4project.User.dto.LoginDTO;
import com.infosys.fa4project.User.dto.OrderDTO;
import com.infosys.fa4project.User.dto.OrderStatus;
import com.infosys.fa4project.User.dto.ProductDTO;
import com.infosys.fa4project.User.dto.SellerDTO;
import com.infosys.fa4project.User.service.SellerService;

@RestController
@CrossOrigin
@RequestMapping(value="/users/sellers")
public class SellerController {

	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	SellerService sellerService;
	
	@Autowired
	Environment env;

	@Value("${productUrl}")
	String productUrl;
	
	@Value("${orderUrl}")
	String orderUrl;
	
	
	
	@GetMapping(value= "", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<SellerDTO> getAllSellers() {
		return sellerService.fetchSeller();
	}
	
	@PostMapping(value = "/register",  consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> registerSeller(@RequestBody SellerDTO sellerDTO) {
		ResponseEntity<String> response = null;
		try{
			logger.info("Creation request for seller");
			Integer sellerId = sellerService.registerSeller(sellerDTO);
			logger.info("Creation successfull for seller id {}", sellerId);
			String successMessage = env.getProperty("UserInterface.REGISTRATION_SUCCESS") + "for seller id " + sellerId;
			response = new ResponseEntity<String>(successMessage, HttpStatus.OK);
		} catch(Exception e) {
			String errorMessage = env.getProperty(e.getMessage());
			logger.error(errorMessage);
			response = new ResponseEntity<String>(env.getProperty(errorMessage), HttpStatus.BAD_REQUEST);
		}
		return response;
	}
	
	@PostMapping(value = "/login",consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> login(@RequestBody LoginDTO loginDTO) {
		ResponseEntity<String> response = null;
		try {
			logger.info("Login request for user");
			sellerService.login(loginDTO);
			String successMessage = env.getProperty("UserInterface.LOGIN_SUCCESS");
			response = new ResponseEntity<String>(successMessage, HttpStatus.OK);
			logger.info("User logged in successfully");
		} catch (Exception e) {
			String errorMessage = env.getProperty(e.getMessage());
			logger.error(errorMessage);
			response = new ResponseEntity<String>(env.getProperty(errorMessage), HttpStatus.BAD_REQUEST);
		}
		return response;
	}
	
	@GetMapping(value= "/{sellerId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public SellerDTO getSellerById(@PathVariable Integer sellerId) {
		return sellerService.getSellerById(sellerId);
	}
	
	@PostMapping(value = "/{sellerId}/deactivate", produces = MediaType.APPLICATION_JSON_VALUE)
	public void deactivateAccount(@PathVariable Integer sellerId) {
		sellerService.deactivateAccount(sellerId);
	}
	
	@GetMapping(value = "/{sellerId}/products", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<ProductDTO> getProducts(@PathVariable Integer sellerId) {
		String url = productUrl + "/findbysellerid?sellerid=" + sellerId.toString();
		@SuppressWarnings("unchecked")
		List<ProductDTO> productDTOList = new RestTemplate().getForObject(url, List.class);
		return productDTOList;
	}
	
	@PostMapping(value = "/{sellerId}/products/add", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> addProducts(@PathVariable Integer sellerId, @RequestBody ProductDTO productDTO) {
		String url = productUrl + "/add";
		productDTO.setSellerId(sellerId);
		ResponseEntity<String> response = new RestTemplate().postForEntity(url, productDTO, String.class);
		return response;
	}
	
	@GetMapping(value = "/orders/{orderId}")
	public OrderDTO getOrders(@PathVariable Integer orderId) {
		OrderDTO orderDTO = null;
		String url = orderUrl + "/" + orderId;
		orderDTO = new RestTemplate().getForObject(url, OrderDTO.class);
		return orderDTO;
	}
	
	@PostMapping(value = "/orders/{orderId}/update")
	public void updateOrder(@PathVariable Integer orderId, @RequestParam("orderstatus") OrderStatus orderStatus) {
		String url = orderUrl + "/" + orderId + "/update?orderstatus=" + orderStatus;
		new RestTemplate().postForObject(url, Void.class, Void.class);
	}
	
}
