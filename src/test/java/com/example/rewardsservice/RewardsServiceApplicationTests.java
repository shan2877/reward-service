package com.example.rewardsservice;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mockitoSession;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.example.rewardsservice.domain.Customer;
import com.example.rewardsservice.domain.Transaction;
import com.example.rewardsservice.service.RewardService;
import com.example.rewardsservice.utils.DataSetUtil;
import com.example.rewardsservice.utils.DateUtil;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class RewardsServiceApplicationTests {

	@Autowired
	private MockMvc mockMvc;
	
	
	@MockBean
	DataSetUtil dataSetUtil;
	
	@Test
	void testRewardServiceResponse() throws Exception {
		Mockito.when(dataSetUtil.getCustomerTransactions()).thenReturn(buildTestDataSet());
		MvcResult mvcResult =mockMvc.perform(get("/rewards/duration/MONTH")).andExpect(status().isOk()).andReturn();
		String expected="{\"durationBy\":\"MONTH\",\"total\":50,\"rewards\":[{\"durationValue\":\"12-2022\",\"customerId\":\"022940e8-bcc8-4228-86c2-97446abdc405\",\"rewards\":50}]}";
		
		assertEquals(expected, mvcResult.getResponse().getContentAsString());
		
	}
	
	@Test
	void testRewardServiceResponse_invalidRange() throws Exception {
		Mockito.when(dataSetUtil.getCustomerTransactions()).thenReturn(buildTestDataSet());
		mockMvc.perform(get("/rewards/duration/INVALID")).andExpect(status().is5xxServerError());
	}
	
	private List<Transaction> buildTestDataSet() throws ParseException {
		List<Transaction> customerTransactions = new ArrayList<>();
		Customer customer1 = new Customer();
		

		Transaction customerTransaction = new Transaction();
		customer1.setCustId(UUID.fromString("022940e8-bcc8-4228-86c2-97446abdc405"));
		customer1.setCustName("A");
		customerTransaction.setCustomer(customer1);
		customerTransaction.setTransactionAmount(100.0);
		customerTransaction.setTransactionDate(DateUtil.getDateFromString("12-12-2022"));
		customerTransactions.add(customerTransaction);

		return customerTransactions;
	}
	

}
