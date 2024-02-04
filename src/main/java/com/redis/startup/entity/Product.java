package com.redis.startup.entity;

import java.io.Serializable;

import org.springframework.data.redis.core.RedisHash;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RedisHash("Product")
public class Product implements Serializable{
	
	@org.springframework.data.annotation.Id
	private int Id;
	private String name;
	private int qty;
	private long price;
}
