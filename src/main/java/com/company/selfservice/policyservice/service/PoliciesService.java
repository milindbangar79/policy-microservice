package com.company.selfservice.policyservice.service;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import com.company.selfservice.policyservice.domain.bean.Beneficiary;
import com.company.selfservice.policyservice.domain.bean.BeneficiaryBean;
import com.company.selfservice.policyservice.domain.bean.PoliciesDetails;
import com.company.selfservice.policyservice.domain.bean.Policyd;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

@Service
public class PoliciesService {
	@Autowired(required = true)
	private MongoTemplate mongoTemplate;

	public List<Policyd> getAllPolicies() {
		List<Policyd> policiesDetailList = new ArrayList<Policyd>();
		DBCursor cursor = mongoTemplate.getDb().getCollection("policiesdetailscol").find();
		while (cursor.hasNext()) {
			cursor.next();
			Policyd policiesBean = new Policyd();
			policiesBean.set_id(new ObjectId(cursor.curr().get("_id").toString()));
			String polNum=cursor.curr().get("policyNumber").toString().substring(0,cursor.curr().get("policyNumber").toString().length()-2);
			policiesBean.setPolicyNumber(Integer.valueOf(polNum));
			policiesBean.setPolicyType(cursor.curr().get("policyType").toString());
			policiesBean.setPolicyStartDate(cursor.curr().get("policyStartDate").toString());
			policiesBean.setPolicyEndDate(cursor.curr().get("policyEndDate").toString());
			String prem=cursor.curr().get("premium").toString().substring(0, cursor.curr().get("premium").toString().length()-2);
			policiesBean.setPremium(Integer.valueOf(prem));
			policiesDetailList.add(policiesBean);
		}
		return policiesDetailList;
	}

	public List<PoliciesDetails> getAllPoliciesDetails(int policyNumber) {
		List<PoliciesDetails> policiesDetailList = new ArrayList<PoliciesDetails>();
		DBObject query = new BasicDBObject();
		//ObjectId idS = new ObjectId(_id);
		//int polNum=Integer.parseInt(policyNumber);
		query.put("policyNumber", policyNumber);
		DBCursor cursor = mongoTemplate.getDb().getCollection("policiesdetailscol").find(query);
		while (cursor.hasNext()) {
			cursor.next();
			PoliciesDetails policiesDetailsBean = new PoliciesDetails();
			policiesDetailsBean.set_id(new ObjectId(cursor.curr().get("_id").toString()));
			policiesDetailsBean.setPolicyNumber(Float.valueOf(cursor.curr().get("policyNumber").toString()));
			policiesDetailsBean.setPolicyType(cursor.curr().get("policyType").toString());
			policiesDetailsBean.setPolicyStartDate(cursor.curr().get("policyStartDate").toString());
			policiesDetailsBean.setPolicyEndDate(cursor.curr().get("policyEndDate").toString());
			policiesDetailsBean.setPremium(Float.valueOf(cursor.curr().get("premium").toString()));
			policiesDetailsBean.setSumAssured(Float.valueOf(cursor.curr().get("sumAssured").toString()));
			policiesDetailsBean.setAge(Float.valueOf(cursor.curr().get("age").toString()));
			policiesDetailsBean.setPremiumDueDate(cursor.curr().get("premiumDueDate").toString());
			policiesDetailsBean.setAgentDetails(cursor.curr().get("agentDetails").toString());
			policiesDetailsBean.setPolicyTerm(Float.valueOf(cursor.curr().get("policyTerm").toString()));
			policiesDetailsBean.setPolicyStatus(cursor.curr().get("policyStatus").toString());
			policiesDetailsBean.setServingBranch(cursor.curr().get("servingBranch").toString());
			policiesDetailList.add(policiesDetailsBean);
		}
		return policiesDetailList;
	}

	public List<Beneficiary> getAllBeneficiary() {
		List<Beneficiary> beneficiaryList = new ArrayList<Beneficiary>();
		DBCursor cursor = mongoTemplate.getDb().getCollection("beneficiarycol").find();
		while (cursor.hasNext()) {
			cursor.next();
			Beneficiary beneficiaryBean = new Beneficiary();
			beneficiaryBean.set_id(new ObjectId(cursor.curr().get("_id").toString()));
			beneficiaryBean.setCustomerId(Integer.valueOf(cursor.curr().get("customerId").toString().substring(0, cursor.curr().get("customerId").toString().length()-2)));
			beneficiaryBean.setName(cursor.curr().get("name").toString());
			beneficiaryList.add(beneficiaryBean);
		}
		return beneficiaryList;
	}

	public String delBeneficiary(String _id) {
		String result = "success";
		List<Beneficiary> beneficiaryList = new ArrayList<Beneficiary>();
		DBObject query = new BasicDBObject();
		ObjectId idS = new ObjectId(_id);
		query.put("_id", idS);
		mongoTemplate.getDb().getCollection("beneficiarycol").findAndRemove(query);
		System.out.println("Deleted records from the table...");
		DBObject queryS = new BasicDBObject();
		queryS.put("_id", idS);
		DBObject cursor = mongoTemplate.getDb().getCollection("beneficiarycol").findOne(queryS);
		/*if (cursor != null) {
			Beneficiary beneficiaryBean = new Beneficiary();
			beneficiaryBean.set_id(new ObjectId(cursor.get("_id").toString()));
			beneficiaryBean.setName(cursor.get("name").toString());
			beneficiaryBean.setCustomerId(Integer.valueOf(cursor.get("customerId").toString().substring(0, cursor.get("customerId").toString().length()-2)));
			beneficiaryList.add(beneficiaryBean);
		}
		if (beneficiaryList.size() == 0)
			result = "success";
		else
			result = "failure";*/
		return result;
	}

	public String addBeneficiary(Beneficiary beneficiaryBean) {
		String result = null;
		// List<Beneficiary> BeneficiaryDetailList = new ArrayList<Beneficiary>();
		DBObject query = new BasicDBObject();
		query.put("name", beneficiaryBean.getName());
		query.put("customerId", 1227546);
		mongoTemplate.getDb().getCollection("beneficiarycol").insert(query);
		System.out.println("Inserted records into the table...");
		result = "success";
		return result;

	}

	public BeneficiaryBean updateBeneficiary(String id, String name) {
		String result = null;
		BeneficiaryBean beneficiaryList = new BeneficiaryBean();
		//ObjectId num = id.get_id();
		DBObject query1 = new BasicDBObject();
		query1.put("_id", new ObjectId(id));
		DBObject query = new BasicDBObject();
		query.put("name", name);
		query.put("customerId", 1227546);
		mongoTemplate.getDb().getCollection("beneficiarycol").update(query1, query);
		System.out.println("Updated records into the table...");
		beneficiaryList.setName(name);
		beneficiaryList.setCustomerId("1227546");
		result = "success";
		return beneficiaryList;
	}
}
