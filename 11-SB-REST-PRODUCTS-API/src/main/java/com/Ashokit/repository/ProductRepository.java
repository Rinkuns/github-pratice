package com.Ashokit.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Ashokit.binding.Product;

public interface ProductRepository extends JpaRepository<Product,Serializable> {
	

}
