package com.kaislab.inventoryservice.web;

import java.util.List;
import java.util.UUID;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import com.kaislab.inventoryservice.dto.ProductRequestDTO;
import com.kaislab.inventoryservice.entities.Category;
import com.kaislab.inventoryservice.entities.Product;
import com.kaislab.inventoryservice.repo.CategoryRepository;
import com.kaislab.inventoryservice.repo.ProductRepository;

@Controller
public class ProductQraphController {
	
	private ProductRepository productRepository;
	private CategoryRepository categoryRepository;
	
	public ProductQraphController(ProductRepository productRepository,CategoryRepository categoryRepository)
	{
		 this.categoryRepository=categoryRepository;
		 this.productRepository =productRepository;
	}
	
	@QueryMapping
	public List<Product> productList()
	{
		return productRepository.findAll();
	}
	
	@QueryMapping
	public Product productById(@Argument String id)
	{		
		return productRepository.findById(id).orElseThrow(
				() -> new RuntimeException(String.format("Product %s not found",id)));
	}
	
	@QueryMapping
	public List<Category> categories()
	{
		return categoryRepository.findAll();
	}
	
	@QueryMapping
	public Category categoryById(@Argument Long id)
	{
		return categoryRepository.findById(id)
				.orElseThrow(() -> new RuntimeException(String.format("Category %s not found ",id)));
	}
	
	@MutationMapping
	public Product saveProduct(@Argument ProductRequestDTO product )
	{
	   Category category = categoryRepository.findById(product.categoryId()).orElse(null);
	   Product productToSave = new Product();
	   productToSave.setId(UUID.randomUUID().toString());
	   productToSave.setName(product.name());
	   productToSave.setPrice(product.price());
	   productToSave.setQuantity(product.quantity());
	   productToSave.setCategory(category);
	  
	   return productRepository.save(productToSave);	
	}
	
	@MutationMapping
	public Product updateProduct(@Argument String id,@Argument ProductRequestDTO product )
	{
	   Category category = categoryRepository.findById(product.categoryId()).orElse(null);
	   Product productToSave = new Product();
	   productToSave.setId(id);
	   productToSave.setName(product.name());
	   productToSave.setPrice(product.price());
	   productToSave.setQuantity(product.quantity());
	   productToSave.setCategory(category);
	  
	   return productRepository.save(productToSave);	
	}
	
	
	@MutationMapping
	public void deleteProduct(@Argument String id)
	{		
	         productRepository.deleteById(id);
	}
	
	

}

	