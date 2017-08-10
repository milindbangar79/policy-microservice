package com.company.selfservice.policyservice.test;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
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
import com.company.selfservice.policyservice.domain.bean.Policyd;
import com.company.selfservice.policyservice.domain.bean.PolicydBean;
import com.company.selfservice.policyservice.service.PoliciesService;
@RunWith(SpringRunner.class)
@WebAppConfiguration
@WebMvcTest(value = PoliciesController.class, secure = false)
public class PolicyServiceApplicationTestsGet extends PoliciesControllerTest {

	@Mock
	private PoliciesService policiesService;
	@InjectMocks
	PoliciesController controller;
	List<Policyd> pd = new ArrayList<Policyd>();
	@Test
	public void getAllPolicies() throws Exception {
		MockitoAnnotations.initMocks(this);
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

}
