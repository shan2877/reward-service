package com.example.rewardsservice.utils;

public enum DateRange {

	ALL("All"), MONTH("MONTH"), YEAR("YEAR"), DAY("DAY");
	
	String value;
	DateRange(String value){
		this.value=value;
	}

}
