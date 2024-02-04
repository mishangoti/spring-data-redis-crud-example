package com.redis.startup.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.redis.startup.entity.Product;

@Repository
public class ProductDao {
	
	public static final String HASH_KEY = "Product";
	
	@Autowired
	private RedisTemplate template;
	
	// save product to redis database 
	public Product save(Product product) {
		template.opsForHash().put(HASH_KEY, product.getId(), product);
		return product;
	}
	
	// return all the products available in database
	public List<Product> findAll() {
		return template.opsForHash().values(HASH_KEY);
	}
	
	// return single product by ID
	public Product findProductById(int id) {
		System.out.println("Called findProductById from db:");
		return (Product) template.opsForHash().get(HASH_KEY, id);
	}
	
	// delete product from database
	public String deleteProduct(int id) {
		template.opsForHash().delete(HASH_KEY, id);
		return "Product Deleted Successfully.";
	}
}
