package com.infosys.fa4project.User.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class Cart {

	@EmbeddedId
	private MyKey myKey;
	
	@Column(name="quantity", nullable=false)
	Integer quantity;
	
	public MyKey getMyKey() {
		return myKey;
	}
	public void setMyKey(MyKey myKey) {
		this.myKey=myKey;
	}
	
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity=quantity;
	}
	
}
