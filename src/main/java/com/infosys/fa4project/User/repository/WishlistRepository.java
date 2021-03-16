package com.infosys.fa4project.User.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.infosys.fa4project.User.entity.MyKey;
import com.infosys.fa4project.User.entity.Wishlist;

public interface WishlistRepository extends JpaRepository<Wishlist, MyKey>{
	public List<Wishlist> findByMyKeyBuyerId(Integer buyerId);
}
