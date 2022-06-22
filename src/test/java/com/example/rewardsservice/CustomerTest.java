package com.example.rewardsservice;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.UUID;

import org.junit.jupiter.api.Test;

import com.example.rewardsservice.domain.Customer;

public class CustomerTest {
	
	@Test
	void customerEqualTest()
	{
		Customer customer1=new Customer();
		customer1.setCustId(UUID.fromString("022940e8-bcc8-4228-86c2-97446abdc405"));
		Customer customer2= new Customer();
		customer2.setCustId(UUID.fromString("022940e8-bcc8-4228-86c2-97446abdc405"));
		
		assertTrue(customer1.equals(customer2));
	}
	
	@Test
	void customerNotEqualTest()
	{
		Customer customer1=new Customer();
		customer1.setCustId(UUID.fromString("022940e8-bcc8-4228-86c2-97446abdc405"));
		Customer customer2= new Customer();
		customer2.setCustId(UUID.fromString("022940e8-bcc8-4228-86c2-97446abdc406"));
		
		assertFalse(customer1.equals(customer2));
	}

}
