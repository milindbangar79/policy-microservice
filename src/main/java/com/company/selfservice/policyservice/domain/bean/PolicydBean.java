package com.company.selfservice.policyservice.domain.bean;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(value = "session")
public class PolicydBean {
	private String idNum;
	private String policyNumber;
    private String policyType;
    private String policyStartDate;
    private String policyEndDate;
    private String premium;
    public PolicydBean(String policyNumber, String policyType, String policyStartDate, String policyEndDate, String premium) {
		this.policyNumber=policyNumber;
		this.policyType=policyType;
		this.policyStartDate=policyStartDate;
		this.policyEndDate=policyEndDate;
		this.premium=premium;
	    
	}
	public PolicydBean() {
	}
	public String getIdNum() {
		return idNum;
	}
	public void setIdNum(String idNum) {
		this.idNum = idNum;
	}
	public String getPremium() {
		return premium;
	}
	public void setPremium(String premium) {
		this.premium = premium;
	}
	public String getPolicyEndDate() {
		return policyEndDate;
	}
	public void setPolicyEndDate(String policyEndDate) {
		this.policyEndDate = policyEndDate;
	}
	public String getPolicyType() {
		return policyType;
	}
	public void setPolicyType(String policyType) {
		this.policyType = policyType;
	}
	public String getPolicyStartDate() {
		return policyStartDate;
	}
	public void setPolicyStartDate(String policyStartDate) {
		this.policyStartDate = policyStartDate;
	}
	public String getPolicyNumber() {
		return policyNumber;
	}
    public void setPolicyNumber(String policyNumber) {
		this.policyNumber = policyNumber;
		
	}
}
