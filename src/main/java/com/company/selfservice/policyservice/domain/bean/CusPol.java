package com.company.selfservice.policyservice.domain.bean;


import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "cuspolcol")
public class CusPol {
	@Id
	private ObjectId _id;
	public ObjectId get_id() {
		return _id;
	}
	public void set_id(ObjectId _id) {
		this._id = _id;
	}
	private float customerId;
	private float policyNumber;
    
	public float getPolicyNumber() {
		return policyNumber;
	}
	public void setPolicyNumber(float policyNumber) {
		this.policyNumber = policyNumber;
	}
	public float getCustomerId() {
		return customerId;
	}
	public void setCustomerId(float customerId) {
		this.customerId = customerId;
	}
	
}
