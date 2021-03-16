package com.infosys.fa4project.User.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@SuppressWarnings("serial")
@Embeddable
public class MyKey implements Serializable{

	@Column(name="buyerid", nullable=false)
	Integer buyerId;
	
	@Column(name="prodid", nullable=false)
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
	
	
}
