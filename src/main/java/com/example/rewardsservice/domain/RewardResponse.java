package com.example.rewardsservice.domain;

import java.beans.Transient;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.example.rewardsservice.utils.DateRange;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class RewardResponse {
	@JsonIgnore
	Map<String,  Map<Customer, Integer>> rewardsByDuration= new HashMap<>();
	private String durationBy;
	private int total;
	private List<TransactionReward> rewards;
	
	public List<TransactionReward> getRewards()
	{
		List<TransactionReward> transactionRewards = new ArrayList<>();
		rewardsByDuration.keySet().stream().forEach(duration -> {
			Map<Customer, Integer> customerRewardsMap=rewardsByDuration.get(duration);
			customerRewardsMap.keySet().stream().forEach(customer -> {
			TransactionReward tranReward= new TransactionReward(
					duration,
					customer.getCustId(),
					customerRewardsMap.get(customer));
			transactionRewards.add(tranReward);
			});});
		return transactionRewards;
	}
}

@Getter
@Setter
@AllArgsConstructor
class TransactionReward{
	private String durationValue;
	private UUID customerId;
	private int rewards;
}
