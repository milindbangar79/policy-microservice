package com.company.selfservice.policyservice.domain.bean;


import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "policiesDetailscol")
public class Policyd {
	@Id
	private ObjectId _id;
	public Policyd(ObjectId id,int policyNumber, String policyType, String policyStartDate, String policyEndDate, int premium) {
		this._id=id;
		this.policyNumber=policyNumber;
		this.policyType=policyType;
		this.policyStartDate=policyStartDate;
		this.policyEndDate=policyEndDate;
		this.premium=premium;
	    
	}
	public Policyd() {
		// TODO Auto-generated constructor stub
	}
	public ObjectId get_id() {
		return _id;
	}
	public void set_id(ObjectId _id) {
		this._id = _id;
	}
	private int policyNumber;
    private String policyType;
    private String policyStartDate;
    private String policyEndDate;
    private int premium;
    
	public int getPremium() {
		return premium;
	}
	public void setPremium(int premium) {
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
	public int getPolicyNumber() {
		return policyNumber;
	}
	public void setPolicyNumber(int policyNumber) {
		this.policyNumber = policyNumber;
	}
	
}
