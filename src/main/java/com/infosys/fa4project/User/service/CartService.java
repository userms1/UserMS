package com.infosys.fa4project.User.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infosys.fa4project.User.dto.CartDTO;
import com.infosys.fa4project.User.entity.Cart;
import com.infosys.fa4project.User.repository.CartRepository;

@Service
public class CartService {

	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	CartRepository cartRepo;
	
   public void createCart(CartDTO cartDTO) {
		logger.info("Creation request for cart of buyer {}", cartDTO.getBuyerId());
		Cart cust = cartDTO.createEntity();
		cartRepo.save(cust);
	}
   
   public List<CartDTO> getCartByBuyerId(Integer buyerId){
	   logger.info("Fetching cart of buyer {}", buyerId);
		List<Cart> cartList = cartRepo.findByMyKeyBuyerId(buyerId);
		List<CartDTO> cartDTOList = new ArrayList<>();
		for(Cart cart:cartList) {
			CartDTO cartDTO = CartDTO.valueOf(cart);
			cartDTOList.add(cartDTO);
		}
		return cartDTOList;
	}
   

}
