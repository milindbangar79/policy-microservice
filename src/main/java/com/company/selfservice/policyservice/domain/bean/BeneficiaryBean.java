package com.company.selfservice.policyservice.domain.bean;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(value = "session")
public class BeneficiaryBean {
	private String idNum;
	private String name;
	private String customerId;
	public BeneficiaryBean(String idNum, String name, String customerId) {
	this.idNum=idNum;
	this.name=name;
	this.customerId=customerId;
	}
	public BeneficiaryBean() {
		// TODO Auto-generated constructor stub
	}
	public String getIdNum() {
		return idNum;
	}
	public void setIdNum(String idNum) {
		this.idNum = idNum;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
    }
