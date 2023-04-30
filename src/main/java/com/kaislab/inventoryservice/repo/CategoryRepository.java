package com.kaislab.inventoryservice.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kaislab.inventoryservice.entities.Category;



public interface CategoryRepository extends JpaRepository<Category,Long>{

	
}
