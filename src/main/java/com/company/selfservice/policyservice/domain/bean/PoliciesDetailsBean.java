package com.company.selfservice.policyservice.domain.bean;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(value = "session")
public class PoliciesDetailsBean {
	private String idNum;
	private String policyNumber;
    private String policyType;
    private String policyStartDate;
    private String policyEndDate;
    private String premium;
    private String sumAssured;
    private String age;
    private String premiumDueDate;
    private String agentDetails;
    private String policyTerm;
    private String policyStatus;
    private String servingBranch;
    
	public PoliciesDetailsBean() {
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
	public String getServingBranch() {
		return servingBranch;
	}
	public void setServingBranch(String servingBranch) {
		this.servingBranch = servingBranch;
	}
	public String getPolicyTerm() {
		return policyTerm;
	}
	public void setPolicyTerm(String policyTerm) {
		this.policyTerm = policyTerm;
	}
	public String getAgentDetails() {
		return agentDetails;
	}
	public void setAgentDetails(String agentDetails) {
		this.agentDetails = agentDetails;
	}
	public String getPremiumDueDate() {
		return premiumDueDate;
	}
	public void setPremiumDueDate(String premiumDueDate) {
		this.premiumDueDate = premiumDueDate;
	}
	public String getSumAssured() {
		return sumAssured;
	}
	public void setSumAssured(String sumAssured) {
		this.sumAssured = sumAssured;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getPolicyStatus() {
		return policyStatus;
	}
	public void setPolicyStatus(String policyStatus) {
		this.policyStatus = policyStatus;
	}
}
