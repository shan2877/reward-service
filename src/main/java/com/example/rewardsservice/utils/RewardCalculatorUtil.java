package com.example.rewardsservice.utils;

public class RewardCalculatorUtil {
	
	public static int calculateRewards(Double amount) {
		int rewards=0;
		
		if(amount>100)
		{
			int dollarAboveHundred=	amount.intValue()-100;
			rewards=50+dollarAboveHundred*2;
		}
		else if(amount>50)
		{
			int dollarAboveFifty=amount.intValue()-50;
			rewards=dollarAboveFifty;
		}
		return rewards;
	}

}
