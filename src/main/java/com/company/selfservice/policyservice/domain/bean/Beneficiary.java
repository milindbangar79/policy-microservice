package com.company.selfservice.policyservice.domain.bean;


import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "policiescol")
public class Beneficiary {
	@Id
	private ObjectId _id;
	public Beneficiary(int customerId, String name) {
		this.customerId=customerId;
		this.name=name;		
	}
	public Beneficiary() {
		// TODO Auto-generated constructor stub
	}
	public ObjectId get_id() {
		return _id;
	}
	public void set_id(ObjectId _id) {
		this._id = _id;
	}
	private int customerId;
	private String name;
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}	
}
