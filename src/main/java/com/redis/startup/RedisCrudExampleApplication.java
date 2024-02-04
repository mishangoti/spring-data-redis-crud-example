package com.redis.startup;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.redis.startup.entity.Product;
import com.redis.startup.repository.ProductDao;

@SpringBootApplication
@RestController
@RequestMapping("/product")
@EnableCaching
public class RedisCrudExampleApplication {
	
	@Autowired
	private ProductDao productDao;
	
	// save new product
	@PostMapping
	public Product save(@RequestBody Product product) {
		return productDao.save(product);
	}

	// get all product
	@GetMapping
	public List<Product> getAllProduct() {
		return productDao.findAll();
	}
	
	// get single product by id
	@GetMapping("/{id}")
	@Cacheable(key = "#id", value = "Product", unless = "#result.price > 500")
	public Product findProductById(@PathVariable int id) {
		return productDao.findProductById(id);
	}
	
	// delete product by id
	@DeleteMapping("/{id}")
    @CacheEvict(key = "#id",value = "Product")
	public String deleteProductById(@PathVariable int id) {
		return productDao.deleteProduct(id);
	}
	
	public static void main(String[] args) {
		SpringApplication.run(RedisCrudExampleApplication.class, args);
	}
	
}
