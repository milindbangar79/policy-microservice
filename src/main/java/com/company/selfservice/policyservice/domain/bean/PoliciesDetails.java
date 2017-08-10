package com.company.selfservice.policyservice.domain.bean;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "policiesDetailscol")
public class PoliciesDetails {
	@Id
	private ObjectId _id;
    public PoliciesDetails(){}
	public PoliciesDetails(float policyNumber, String policyType, String policyStartDate, String policyEndDate,
			float premium, float sumAssured, float age, String premiumDueDate, String agentDetails, float policyTerm,
			String policyStatus, String servingBranch) {
		this.policyNumber=policyNumber;
		this.policyType = policyType;
		this.policyStartDate = policyStartDate;
		this.policyEndDate = policyEndDate;
		this.premium = premium;
		this.sumAssured = sumAssured;
		this.age = age;
		this.premiumDueDate = premiumDueDate;
		this.agentDetails = agentDetails;
		this.policyTerm = policyTerm;
		this.policyStatus = policyStatus;
		this.servingBranch = servingBranch;

	}

	public ObjectId get_id() {
		return _id;
	}

	public void set_id(ObjectId _id) {
		this._id = _id;
	}

	private float policyNumber;
	private String policyType;
	private String policyStartDate;
	private String policyEndDate;
	private float premium;
	private float sumAssured;
	private float age;
	private String premiumDueDate;
	private String agentDetails;
	private float policyTerm;
	private String policyStatus;
	private String servingBranch;

	public float getPremium() {
		return premium;
	}

	public void setPremium(float premium) {
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

	public float getPolicyNumber() {
		return policyNumber;
	}

	public void setPolicyNumber(float policyNumber) {
		this.policyNumber = policyNumber;
	}

	public float getSumAssured() {
		return sumAssured;
	}

	public void setSumAssured(Float sumAssured) {
		this.sumAssured = sumAssured;
	}

	public float getAge() {
		return age;
	}

	public void setAge(Float age) {
		this.age = age;
	}

	public String getPremiumDueDate() {
		return premiumDueDate;
	}

	public void setPremiumDueDate(String premiumDueDate) {
		this.premiumDueDate = premiumDueDate;
	}

	public String getAgentDetails() {
		return agentDetails;
	}

	public void setAgentDetails(String agentDetails) {
		this.agentDetails = agentDetails;
	}

	public float getPolicyTerm() {
		return policyTerm;
	}

	public void setPolicyTerm(Float float1) {
		this.policyTerm = float1;
	}

	public String getPolicyStatus() {
		return policyStatus;
	}

	public void setPolicyStatus(String policyStatus) {
		this.policyStatus = policyStatus;
	}

	public String getServingBranch() {
		return servingBranch;
	}

	public void setServingBranch(String servingBranch) {
		this.servingBranch = servingBranch;
	}

}
