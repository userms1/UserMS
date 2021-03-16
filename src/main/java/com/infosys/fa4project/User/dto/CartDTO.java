package com.infosys.fa4project.User.dto;

import com.infosys.fa4project.User.entity.Cart;
import com.infosys.fa4project.User.entity.MyKey;

public class CartDTO {

	Integer buyerId;
	Integer prodId;
	Integer quantity;
	
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
	
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity=quantity;
	}
	
	public static CartDTO valueOf(Cart cart) {
		CartDTO cartDTO = new CartDTO();
		cartDTO.setBuyerId(cart.getMyKey().getBuyerId());
		cartDTO.setProdId(cart.getMyKey().getProdId());
		cartDTO.setQuantity(cart.getQuantity());
		return cartDTO;
	}
	
	public Cart createEntity() {
		Cart cart = new Cart();
		MyKey myKey = new MyKey();
		myKey.setBuyerId(this.getBuyerId());
		myKey.setProdId(this.getProdId());
		cart.setMyKey(myKey);
		cart.setQuantity(this.getQuantity());
		return cart;
	}
	
}
