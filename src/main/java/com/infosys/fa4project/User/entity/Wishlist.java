package com.infosys.fa4project.User.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class Wishlist {

	@EmbeddedId
	private MyKey myKey;
	
	public MyKey getMyKey() {
		return myKey;
	}
	public void setMyKey(MyKey myKey) {
		this.myKey=myKey;
	}
	
}
