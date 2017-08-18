package com.company.selfservice.policyservice.controller;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.company.selfservice.policyservice.domain.bean.Beneficiary;
import com.company.selfservice.policyservice.domain.bean.BeneficiaryBean;
import com.company.selfservice.policyservice.domain.bean.Policyd;
import com.company.selfservice.policyservice.domain.bean.PolicydBean;
import com.company.selfservice.policyservice.service.PoliciesService;

@RunWith(SpringRunner.class)
public class PoliciesServiceApplicationMainTest extends PoliciesControllerTest  {

	@TestConfiguration
	static class PoliciesServiceApplicationMainTestContextConfiguration {
		
		@Bean
		public PoliciesService policiesService() {
			return new PoliciesService();
		}
	}
	
	@MockBean
	private PoliciesService policiesService;
	//PoliciesService policiesService;
	
	@MockBean
	MongoTemplate mongoTemplate;
	
	@InjectMocks
	static
	PoliciesController controller;
	List<Policyd> pd = new ArrayList<Policyd>();
	
	@Test
	public void getPolicies() throws Exception {MockitoAnnotations.initMocks(this);
	Policyd policiesDetails = new Policyd(new ObjectId(),111, "Money Back", "20/11/2017", "19/10/2009", 111890);
	pd.add(policiesDetails);
	Mockito.when(policiesService.getAllPolicies()).thenReturn(pd);
	String uri = "/policies";

	setUp(controller);
	MvcResult result = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON)).andReturn();

	String content = result.getResponse().getContentAsString();
	System.out.println("Content:" + content);
	int status = result.getResponse().getStatus();
	List<PolicydBean> polL = super.mapFromJson(content);
	PolicydBean pol = polL.get(0);
	Assert.assertEquals("failure - expected HTTP status", 200, status);
	Assert.assertTrue("failure - expected HTTP response body to have a value", content.trim().length() > 0);
	Assert.assertNotNull("failure - expected policy.number match", pol.getIdNum());
	Assert.assertEquals("failure - expected policy.number match", "111", pol.getPolicyNumber());
	Assert.assertEquals("failure - expected policy.text match", "Money Back", pol.getPolicyType());
	Assert.assertEquals("failure - expected policy.text match", "20/11/2017", pol.getPolicyStartDate());
	Assert.assertEquals("failure - expected policy.text match", "19/10/2009", pol.getPolicyEndDate());
	Assert.assertEquals("failure - expected policy.text match", "111890", pol.getPremium());
		
		
	}
	@Test
	public void postBeneficiary() throws Exception {
		MockitoAnnotations.initMocks(this);
		Beneficiary beneficiary=new Beneficiary(1227546,"John");
		String inputJson = super.mapToJson(beneficiary);
		String uri = "/policies/beneficiary/add";

		setUp(controller);
		MvcResult result = mvc
                .perform(MockMvcRequestBuilders.post(uri)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON).content(inputJson))
                .andReturn();
		String content = result.getResponse().getContentAsString();
		System.out.println("Content:" + content);
		int status = result.getResponse().getStatus();
		BeneficiaryBean ben = super.mapFromJson(content,BeneficiaryBean.class);
		Assert.assertEquals("failure - expected HTTP status", 200, status);
		Assert.assertTrue("failure - expected HTTP response body to have a value", content.trim().length() > 0);
		Assert.assertEquals("failure - expected Beneficiary.CustomerId match", "1227546", ben.getCustomerId());
		Assert.assertEquals("failure - expected Beneficiary.name match", "John", ben.getName());
	}


}