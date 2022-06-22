package com.example.rewardsservice.controller;

import java.lang.reflect.Method;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.rewardsservice.domain.RewardResponse;
import com.example.rewardsservice.service.RewardService;
import com.example.rewardsservice.utils.DateRange;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/")
public class RewardsController {

	@Autowired
	private RewardService rewardService;

	@GetMapping(value = "/rewards/duration/{durationVal}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RewardResponse> getRewardsPerMonth(@PathVariable String durationVal)
			throws Exception, IllegalArgumentException {
		log.info("received request for getting rewards for {}", durationVal);
		try {
			DateRange period = DateRange.valueOf(durationVal);
			RewardResponse response = rewardService.getRewards(period);
			return ResponseEntity.ok().body(response);
		} catch (IllegalArgumentException e) {
			Method method = getClass().getDeclaredMethod("getRewardsPerMonth", String.class);
			MethodParameter parameter = new MethodParameter(method, 0);
			throw new MissingPathVariableException("duration", parameter);
		}

	}
}
