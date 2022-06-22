package com.example.rewardsservice.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.example.rewardsservice.domain.Customer;
import com.example.rewardsservice.domain.Transaction;

@Repository
public class DataSetUtil {

	public List<Transaction> getCustomerTransactions() throws ParseException {
		List<Transaction> customerTransactions = new ArrayList<>();
		Customer customer1 = new Customer();
		Customer customer2 = new Customer();
		Customer customer3 = new Customer();

		Transaction customerTransaction = new Transaction();
		customer1.setCustId(UUID.randomUUID());
		customer1.setCustName("A");
		customerTransaction.setCustomer(customer1);
		customerTransaction.setTransactionAmount(100.0);
		customerTransaction.setTransactionDate(DateUtil.getDateFromString("12-12-2022"));
		customerTransactions.add(customerTransaction);

		customerTransaction = new Transaction();
		customer2.setCustId(UUID.randomUUID());
		customer2.setCustName("B");
		customerTransaction.setCustomer(customer2);
		customerTransaction.setTransactionAmount(123.0);
		customerTransaction.setTransactionDate(DateUtil.getDateFromString("11-11-2022"));
		customerTransactions.add(customerTransaction);

		customerTransaction = new Transaction();
		customer3.setCustId(UUID.randomUUID());
		customer3.setCustName("C");
		customerTransaction.setCustomer(customer3);
		customerTransaction.setTransactionAmount(247.0);
		customerTransaction.setTransactionDate(DateUtil.getDateFromString("01-12-2022"));
		customerTransactions.add(customerTransaction);

		return customerTransactions;
	}

}
