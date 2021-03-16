package com.infosys.fa4project.User.dto;

import com.infosys.fa4project.User.entity.MyKey;
import com.infosys.fa4project.User.entity.Wishlist;

public class WishlistDTO {

	Integer buyerId;
	Integer prodId;
	
	public Integer getBuyerId() {
		return buyerId;
	}
	public void setBuyerId(Integer buyerId) {
		this.buyerId=buyerId;
	}

	public Integer getProdId() {
		return prodId;
	}
	public void setProdId(Integer prodId) {
		this.prodId=prodId;
	}
	
	public static WishlistDTO valueOf(Wishlist wish) {
		WishlistDTO wishDTO = new WishlistDTO();
		wishDTO.setBuyerId(wish.getMyKey().getBuyerId());
		wishDTO.setProdId(wish.getMyKey().getProdId());
		return wishDTO;
	}
	
	public Wishlist createEntity() {
		Wishlist wish = new Wishlist();
		MyKey myKey = new MyKey();
		myKey.setBuyerId(this.getBuyerId());
		myKey.setProdId(this.getProdId());
		wish.setMyKey(myKey);
		return wish;
	}
	
}
