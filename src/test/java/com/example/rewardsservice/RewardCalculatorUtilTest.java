package com.example.rewardsservice;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.example.rewardsservice.utils.RewardCalculatorUtil;

public class RewardCalculatorUtilTest {
	
	@Test
	void lessThanFiftyTest()
	{
		double amount =48.9;
		assertEquals(0, RewardCalculatorUtil.calculateRewards(amount));
	}
	
	@Test
	void moreThanFiftyLessThanHundredTest()
	{
		double amount =78.0;
		int expected = 28;
		assertEquals(expected, RewardCalculatorUtil.calculateRewards(amount));
	}
	
	@Test
	void moreThanHundredTest()
	{
		double amount =125.9;
		int expected = 100;
		assertEquals(expected, RewardCalculatorUtil.calculateRewards(amount));
	}

}
