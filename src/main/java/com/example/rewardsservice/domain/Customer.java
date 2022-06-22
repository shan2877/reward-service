package com.example.rewardsservice.domain;

import java.util.List;
import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Customer {
	
	private String custName;
	private UUID custId;
	
	
	@Override
	public boolean equals(Object o)
	{
		boolean isEqual=false;
		if(o!=null) {
			Customer other=(Customer)o;
			if(other.custId!=null && other.custId.equals(this.custId)){
				isEqual=true;
			}
		}
		return isEqual;
	}
	
	@Override
	public int hashCode()
	{
		char[] chars=this.custId.toString().toCharArray();
		int hashValue=0;
		for(int i=0;i<chars.length; i++) {
			char c = chars[i];
			hashValue = hashValue+c;
		}
		return hashValue;
	}
	
	@Override
	public String toString()
	{
		return this.custId.toString();
	}
}
