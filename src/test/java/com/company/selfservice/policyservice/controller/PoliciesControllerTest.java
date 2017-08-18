package com.company.selfservice.policyservice.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.company.selfservice.policyservice.domain.bean.PolicydBean;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebAppConfiguration
public abstract class PoliciesControllerTest {

	protected MockMvc mvc;

	@Autowired
	protected WebApplicationContext webApplicationContext;

	protected void setUp() {
		mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	protected void setUp(PoliciesController controller) {
		mvc = MockMvcBuilders.standaloneSetup(controller).build();
	}
	
	protected String mapToJson(Object obj) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(obj);
	}

	protected List<PolicydBean> mapFromJson(String json) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		List<PolicydBean> pol = mapper.reader().forType(new TypeReference<List<PolicydBean>>() {
		}).readValue(json);

		return pol;
	}
	protected <T> T mapFromJson(String json, Class<T> clazz)
            throws JsonParseException, JsonMappingException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(json, clazz);
    }

}
