package com.kaislab.inventoryservice.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kaislab.inventoryservice.entities.Product;

public interface ProductRepository extends JpaRepository<Product, String>{

}
