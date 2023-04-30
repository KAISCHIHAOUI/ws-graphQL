package com.kaislab.inventoryservice;

import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.kaislab.inventoryservice.entities.Category;
import com.kaislab.inventoryservice.entities.Product;
import com.kaislab.inventoryservice.repo.CategoryRepository;
import com.kaislab.inventoryservice.repo.ProductRepository;

@SpringBootApplication
public class WsGraphQlApplication {

	public static void main(String[] args) {
		SpringApplication.run(WsGraphQlApplication.class, args);
	}
	
	@Bean
	CommandLineRunner commandLineRunner(CategoryRepository categoryRepository, ProductRepository productRepository) {
		return args -> {

			List.of("Computer", "Printer", "Smartphone").forEach(cat -> {
				Category category = Category.builder().name(cat).build();
				categoryRepository.save(category);
			});
			categoryRepository.findAll().forEach(category -> {
				for (int i = 0; i <10; i++) {
					Product product = Product.builder()
							.id(UUID.randomUUID().toString())
							.name("Computer" + i)
							.price(100 + Math.random() * 50000)
							.quantity(new Random().nextInt(100))
							.category(category)
							.build();
					productRepository.save(product);

				}
			});

		};

	}
       }
