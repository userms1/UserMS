package com.infosys.fa4project.User.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.infosys.fa4project.User.entity.Cart;
import com.infosys.fa4project.User.entity.MyKey;

public interface CartRepository extends JpaRepository<Cart, MyKey>{
	public List<Cart> findByMyKeyBuyerId(Integer buyerId);
}
