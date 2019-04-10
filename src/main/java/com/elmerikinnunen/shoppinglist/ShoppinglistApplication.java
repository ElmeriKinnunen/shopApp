package com.elmerikinnunen.shoppinglist;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

import com.elmerikinnunen.shoppinglist.domain.Department;
import com.elmerikinnunen.shoppinglist.domain.DepartmentRepository;
import com.elmerikinnunen.shoppinglist.domain.Product;
import com.elmerikinnunen.shoppinglist.domain.ProductRepository;
import com.elmerikinnunen.shoppinglist.domain.User;
import com.elmerikinnunen.shoppinglist.domain.UserRepository;


@SpringBootApplication
public class ShoppinglistApplication extends SpringBootServletInitializer {
	private static final Logger L = LoggerFactory.getLogger(ShoppinglistApplication.class);
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(ShoppinglistApplication.class);
	}
	
	public static void main(String[] args)throws Exception {
		SpringApplication.run(ShoppinglistApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner productTest(ProductRepository productRepository, DepartmentRepository departmentRepository, UserRepository userRepository) {
		return (args) -> {
			
			L.info("default departments for the products");
			departmentRepository.save(new Department("HeVi osasto"));
			departmentRepository.save(new Department("Maito osasto"));
			departmentRepository.save(new Department("Liha osasto"));
			departmentRepository.save(new Department("Pakaste osasto"));
			departmentRepository.save(new Department("Leipä osasto"));
			departmentRepository.save(new Department("Teollinen osasto"));
			
			 // productRepository.save(new Product("kurkku", 2, departmentRepository.findByName("HeVi osasto").get(0)));

			User user1 = new User("user", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "user@gmail.com", "USER");
			User user2 = new User("admin", "$2a$10$0MMwY.IQqpsVc1jC8u7IJ.2rT8b0Cd3b3sfIBGV2zfgnPGtT4r0.C", "admin@gmail.com", "ADMIN");
			userRepository.save(user1);
			userRepository.save(user2);
			
			L.info("fetch products");
			for(Product product : productRepository.findAll()) {
				L.info(product.toString());
			}
		};
	}
}
