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

import com.infosys.fa4project.User.dto.BuyerDTO;
import com.infosys.fa4project.User.dto.CartDTO;
import com.infosys.fa4project.User.dto.LoginDTO;
import com.infosys.fa4project.User.dto.OrderDTO;
import com.infosys.fa4project.User.dto.OrderStatus;
import com.infosys.fa4project.User.dto.WishlistDTO;
import com.infosys.fa4project.User.service.BuyerService;
import com.infosys.fa4project.User.service.CartService;
import com.infosys.fa4project.User.service.WishlistService;



@RestController
@CrossOrigin
@RequestMapping(value="/users/buyers")
public class BuyerController {

	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	BuyerService buyerService;
	
	@Autowired
	WishlistService wishlistService;
	
	@Autowired
	CartService cartService;
	
	@Autowired
	Environment env;
	
	@Value("${productUrl}")
	String productUrl;
	
	@Value("${orderUrl}")
	String orderUrl;
	

	
	@GetMapping(value= "", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<BuyerDTO> getAllBuyers() {
		return buyerService.fetchBuyer();
	}
	
	@PostMapping(value = "/register",  consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> registerBuyer(@RequestBody BuyerDTO buyerDTO) {
		ResponseEntity<String> response = null;
		try{
			logger.info("Creation request for buyer");
			Integer buyerId = buyerService.registerBuyer(buyerDTO);
			logger.info("Creation successfull for buyer id {}", buyerId);
			String successMessage = env.getProperty("UserInterface.REGISTRATION_SUCCESS") + "for buyer id " + buyerId;
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
			buyerService.login(loginDTO);
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
	
	@GetMapping(value= "/{buyerId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public BuyerDTO getSellerById(@PathVariable Integer buyerId) {
		return buyerService.getBuyerById(buyerId);
	}
	
	@PostMapping(value = "/{buyerId}/deactivate", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void deactivateAccount(@PathVariable Integer buyerId) {
		buyerService.deactivateAccount(buyerId);
	}
	
	@PostMapping(value = "/{buyerId}/privileged", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> makePrivileged(@PathVariable Integer buyerId) {
		logger.info("Request for privileged user");
		ResponseEntity<String> response = null;
		try {
			buyerService.makePrivileged(buyerId);
			String successMessage = env.getProperty("UserInterface.PIVILEGE_SUCCESS");
			response = new ResponseEntity<String>(successMessage, HttpStatus.OK);
			logger.error("Successfully became a privileged user");
		} catch (Exception e) {
			String errorMessage = env.getProperty(e.getMessage());
			logger.error(errorMessage);
			response = new ResponseEntity<String>(env.getProperty(errorMessage), HttpStatus.BAD_REQUEST);
		}
		return response;
	}
	
	@GetMapping(value = "/{buyerId}/orders", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<OrderDTO> createOrder( @PathVariable Integer buyerId){
		String url = orderUrl +"/findbybuyerid?buyerid=" + buyerId;
		@SuppressWarnings("unchecked")
		List<OrderDTO> orderDTOList = new RestTemplate().getForObject(url, List.class);
		return orderDTOList;
	}
	
	@PostMapping(value = "/{buyerId}/orders/add", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void createOrder(@RequestBody OrderDTO orderDTO, @PathVariable Integer buyerId){
		String url = orderUrl + "/add";
		orderDTO.setBuyerId(buyerId);
		orderDTO.setStatus(OrderStatus.ORDER_PLACED);
		new RestTemplate().postForObject(url, orderDTO, Void.class);
	}
	
	@GetMapping(value = "/{buyerId}/wishlist", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<WishlistDTO> getWishlist(@PathVariable Integer buyerId){
		return wishlistService.getByBuyerId(buyerId);
	}
	
	@GetMapping(value = "/{buyerId}/wishlist/findbyprodid", produces = MediaType.APPLICATION_JSON_VALUE)
	public WishlistDTO getWishlistByProdid(@PathVariable Integer buyerId, @RequestParam(value="prodid", required=true) Integer prodId){
		return wishlistService.getById(buyerId, prodId);
	}
	
	@PostMapping(value = "/{buyerId}/wishlist/add", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void createWishlist(@PathVariable Integer buyerId, @RequestParam(value="prodid", required=true) Integer prodId){
		wishlistService.createWishlist(buyerId, prodId);
	}
	
	@GetMapping(value = "/{buyerId}/cart", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<CartDTO> getCart(@PathVariable Integer buyerId){
		return cartService.getCartByBuyerId(buyerId);
	}
	
	@PostMapping(value = "/{buyerId}/cart/add", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void createWishlist(@PathVariable Integer buyerId, @RequestBody CartDTO cartDTO){
		cartService.createCart(cartDTO);
	}
}
