package com.infosys.fa4project.User.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infosys.fa4project.User.dto.WishlistDTO;
import com.infosys.fa4project.User.entity.MyKey;
import com.infosys.fa4project.User.entity.Wishlist;
import com.infosys.fa4project.User.repository.WishlistRepository;

@Service
public class WishlistService {

	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	WishlistRepository wishRepo;
	
	public void createWishlist(Integer buyerId, Integer prodId) {
		logger.info("Creation request for wishlist");
		WishlistDTO wishDTO = new WishlistDTO();
		wishDTO.setBuyerId(buyerId);
		wishDTO.setProdId(prodId);
		Wishlist cust = wishDTO.createEntity();
		wishRepo.save(cust);
	}
	
	public WishlistDTO getById(Integer buyerId, Integer prodId) {
		logger.info("get request for wishlist of buyer {}", buyerId);
		WishlistDTO wishlistDTO = null;
		MyKey myKey = new MyKey();
		myKey.setBuyerId(buyerId);
		myKey.setProdId(prodId);
		Optional<Wishlist> wishlistOpt = wishRepo.findById(myKey);
		if(wishlistOpt.isPresent()){
			Wishlist wishlist = wishlistOpt.get();
			wishlistDTO = WishlistDTO.valueOf(wishlist);
		}
		return wishlistDTO;
	}
	
	public List<WishlistDTO> getByBuyerId(Integer buyerId) {
		logger.info("get request for wishlist of buyer {}", buyerId);
		List<WishlistDTO> wishlistDTOList = new ArrayList<WishlistDTO>();
		List<Wishlist> wishlistList = wishRepo.findByMyKeyBuyerId(buyerId);
		for (Wishlist wishlist : wishlistList) {
			WishlistDTO wishlistDTO = WishlistDTO.valueOf(wishlist);
			wishlistDTOList.add(wishlistDTO);
		}
		return wishlistDTOList;
	}

}
