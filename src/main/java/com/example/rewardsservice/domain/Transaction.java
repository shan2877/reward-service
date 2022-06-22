package com.example.rewardsservice.domain;

import java.util.Date;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Transaction {
	private UUID id= UUID.randomUUID();
	private Double transactionAmount;
	private Date transactionDate;
	private Customer customer;
}
