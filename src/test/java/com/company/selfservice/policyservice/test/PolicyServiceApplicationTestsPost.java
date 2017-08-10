package com.company.selfservice.policyservice.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.company.selfservice.policyservice.controller.PoliciesController;
import com.company.selfservice.policyservice.controller.PoliciesControllerTest;
import com.company.selfservice.policyservice.domain.bean.Beneficiary;
import com.company.selfservice.policyservice.domain.bean.BeneficiaryBean;
import com.company.selfservice.policyservice.domain.bean.Policyd;
import com.company.selfservice.policyservice.service.PoliciesService;
@RunWith(SpringRunner.class)
@WebMvcTest(value = PoliciesController.class, secure = false)
public class PolicyServiceApplicationTestsPost extends PoliciesControllerTest {

	@Mock
	private PoliciesService policiesService;
	@InjectMocks
	PoliciesController controller;
	List<Policyd> pd = new ArrayList<Policyd>();
	String polJson = "{\"policyNumber\":\"111\"" + ",\"policyType\":\"Money Back\""
			+ ",\"policyStartDate\":\"20/11/2017\"" + ",\"policyEndDate\":\"19/10/2009\"" + ",\"premium\":\"111890\"}";

	@Test
	public void getAllPolicies() throws Exception {
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
