package com.example.rewardsservice.service;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.rewardsservice.domain.Customer;
import com.example.rewardsservice.domain.RewardResponse;
import com.example.rewardsservice.domain.Transaction;
import com.example.rewardsservice.utils.DataSetUtil;
import com.example.rewardsservice.utils.DateRange;
import com.example.rewardsservice.utils.DateUtil;
import com.example.rewardsservice.utils.RewardCalculatorUtil;

@Service
public class RewardService {

	@Autowired
	DataSetUtil dataSetUtil;

	public RewardResponse getRewards(DateRange range) throws Exception {
		List<Transaction> allTransactions = dataSetUtil.getCustomerTransactions();
		Map<String, Map<Customer, Integer>> customerTransactionOnPeriod = new HashMap<>();
		int totalRewards = 0;
		RewardResponse response = new RewardResponse();
		switch (range) {
		case MONTH:
			for (Transaction transaction : allTransactions) {
				int year = DateUtil.getDateValue(transaction.getTransactionDate(), Calendar.YEAR);
				int month = DateUtil.getDateValue(transaction.getTransactionDate(), Calendar.MONTH)+1;
				String monthAndYear = month + "-" + year;
				int transactionreward = calculateRewardsForGivenRange(transaction, monthAndYear,
						customerTransactionOnPeriod);
				totalRewards = totalRewards + transactionreward;
			}
			response.setDurationBy(range.name());
			break;
		case DAY:
			for (Transaction transaction : allTransactions) {
				String dateStr = transaction.getTransactionDate().toString();
				int transactionreward = calculateRewardsForGivenRange(transaction, dateStr,
						customerTransactionOnPeriod);
				totalRewards = totalRewards + transactionreward;
			}
			response.setDurationBy(range.name());
			break;
		case YEAR:
			for (Transaction transaction : allTransactions) {
				String year = DateUtil.getDateValue(transaction.getTransactionDate(), Calendar.YEAR).toString();
				int transactionreward = calculateRewardsForGivenRange(transaction, year, customerTransactionOnPeriod);
				totalRewards = totalRewards + transactionreward;
			}
			response.setDurationBy(range.name());
			break;
		default:
			throw new Exception("Not a valid period");
		}
		response.setRewardsByDuration(customerTransactionOnPeriod);
		response.setTotal(totalRewards);
		return response;

	}

	public int calculateRewardsForGivenRange(final Transaction transaction, final String key,
			Map<String, Map<Customer, Integer>> customerTransactionOnPeriod) {
		Customer customer = transaction.getCustomer();
		int transactionreward = 0;
		Map<Customer, Integer> customerTransactionMap = customerTransactionOnPeriod.get(key);
		if (customerTransactionMap != null) {
			transactionreward = RewardCalculatorUtil.calculateRewards(transaction.getTransactionAmount());
			if(customerTransactionMap.get(customer) !=null) {
				customerTransactionMap.put(customer, customerTransactionMap.get(customer) + (transactionreward));
			}
			else {
				customerTransactionMap.put(customer,transactionreward);
			}
		} else {
			customerTransactionMap = new HashMap<>();
			transactionreward = RewardCalculatorUtil.calculateRewards(transaction.getTransactionAmount());
			customerTransactionMap.put(customer, transactionreward);
			customerTransactionOnPeriod.put(key, customerTransactionMap);
		}
		return transactionreward;
	}
}
