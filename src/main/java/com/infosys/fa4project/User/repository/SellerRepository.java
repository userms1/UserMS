package com.infosys.fa4project.User.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.infosys.fa4project.User.entity.Seller;

public interface SellerRepository extends JpaRepository<Seller, Integer>{
	
	public Optional<Seller> findByPhoneNumber(Long phoneNumber);

}
