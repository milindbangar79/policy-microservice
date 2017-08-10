package com.company.selfservice.policyservice.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.company.selfservice.policyservice.domain.bean.Beneficiary;
import com.company.selfservice.policyservice.domain.bean.BeneficiaryBean;
import com.company.selfservice.policyservice.domain.bean.PoliciesDetails;
import com.company.selfservice.policyservice.domain.bean.PoliciesDetailsBean;
import com.company.selfservice.policyservice.domain.bean.Policyd;
import com.company.selfservice.policyservice.domain.bean.PolicydBean;
import com.company.selfservice.policyservice.service.PoliciesService;

@RestController
public class PoliciesController {
	
	@Autowired
	private PoliciesService policiesService;
	
	@RequestMapping("/policies")
	public List<PolicydBean> getAllPoliciesList(){
		List<Policyd> polList= policiesService.getAllPolicies();
		List<PolicydBean> polBean=prepareListofBean(polList);
		return polBean;
		
	}
	@RequestMapping(value = "/policies", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<List<PolicydBean>> getAllPoliciesListRestApi() {
        List<Policyd> polList = policiesService.getAllPolicies();
        List<PolicydBean> polBean=prepareListofBean(polList);
        if (polBean.isEmpty()) {
            return new ResponseEntity<List<PolicydBean>>(HttpStatus.NO_CONTENT);
            // You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<PolicydBean>>(polBean, HttpStatus.OK);
    }
	@RequestMapping(value = "/policies/details/{policyNumber}", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<List<PoliciesDetailsBean>> getAllPoliciesDetailsListRestApi(@PathVariable("policyNumber") int policyNumber)
	{
        List<PoliciesDetails> polList = policiesService.getAllPoliciesDetails(policyNumber);
        List<PoliciesDetailsBean> polBean=prepareDetailsListofBean(polList);
        if (polBean.isEmpty()) {
            return new ResponseEntity<List<PoliciesDetailsBean>>(HttpStatus.NO_CONTENT);
            // You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<PoliciesDetailsBean>>(polBean, HttpStatus.OK);
    }
	@RequestMapping(value = "/policies/beneficiary", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<List<BeneficiaryBean>> getAllBeneficiaryListRestApi() {
        List<Beneficiary> bList = policiesService.getAllBeneficiary();
        List<BeneficiaryBean> polBean=prepareDetailsListof(bList);
        if (polBean.isEmpty()) {
            return new ResponseEntity<List<BeneficiaryBean>>(HttpStatus.NO_CONTENT);
            // You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<BeneficiaryBean>>(polBean, HttpStatus.OK);
    }
	
	private List<BeneficiaryBean> prepareDetailsListof(List<Beneficiary> bList) {
		List<BeneficiaryBean> beneficiaryBeanList = null;
		if (bList != null && !bList.isEmpty()) {
			beneficiaryBeanList = new ArrayList<BeneficiaryBean>();
			BeneficiaryBean beneficiaryBean = null;
			for (Beneficiary ben : bList) {
				beneficiaryBean = new BeneficiaryBean();
				beneficiaryBean.setIdNum(ben.get_id().toString());
				beneficiaryBean.setName(ben.getName());
				beneficiaryBean.setCustomerId(ben.getCustomerId()+"");
				beneficiaryBeanList.add(beneficiaryBean);
			}

		}
		return beneficiaryBeanList;
	}
	@RequestMapping(value = "/policies/beneficiary/del/{idNum}", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody ResponseEntity<BeneficiaryBean> delBeneficiaryDetailsListRestApi(@PathVariable("idNum") String idNum) {
		System.out.println("in delete func");
        String bList = policiesService.delBeneficiary(idNum);
        BeneficiaryBean bBean=new BeneficiaryBean();
        //List<BeneficiaryBean> bBean=prepareDetailsListof(bList);
        //if (!(bList.equalsIgnoreCase("success"))) {
          //  return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
            // You many decide to return HttpStatus.NOT_FOUND
        //}
        return new ResponseEntity<BeneficiaryBean>(bBean, HttpStatus.OK);
    }
	@RequestMapping(value = "/policies/beneficiary/update/{id}/{name}", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody ResponseEntity<BeneficiaryBean> updateBeneficiaryDetailsListRestApi(@PathVariable("id") String id,@PathVariable("name") String name) {
		//Beneficiary beneficiary=prepareModel(beneficiaryBean);
        BeneficiaryBean bList = policiesService.updateBeneficiary(id,name);
        //if (!(bList.equalsIgnoreCase("success"))) {
        //    return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
            // You many decide to return HttpStatus.NOT_FOUND
        //}
        return new ResponseEntity<BeneficiaryBean>(bList, HttpStatus.OK);
    }
	@RequestMapping(value = "/policies/beneficiary/add", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public @ResponseBody ResponseEntity<BeneficiaryBean> addBeneficiaryDetailsListRestApi(@RequestBody BeneficiaryBean beneficiaryBean) {
		Beneficiary beneficiary=prepareModel(beneficiaryBean);
        String bList = policiesService.addBeneficiary(beneficiary);
        //if (!(bList.equalsIgnoreCase("success"))) {
          //  return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
            // You many decide to return HttpStatus.NOT_FOUND
       // }
        return new ResponseEntity<BeneficiaryBean>(beneficiaryBean, HttpStatus.OK);
    }
	private Beneficiary prepareModel(BeneficiaryBean beneficiaryBean)  {
		Beneficiary beneficiary = new Beneficiary();
		beneficiary.setCustomerId(1227546);
		beneficiary.setName(beneficiaryBean.getName());
		System.out.println("beneficiaryBean.getName()"+beneficiaryBean.getName());
		return beneficiary;
	}
	private List<PolicydBean> prepareListofBean(List<Policyd> policies) {
		List<PolicydBean> policiesBeanList = null;
		if (policies != null && !policies.isEmpty()) {
			policiesBeanList = new ArrayList<PolicydBean>();
			PolicydBean policiesBean = null;
			for (Policyd pol : policies) {
				policiesBean = new PolicydBean();
				policiesBean.setIdNum(pol.get_id().toString());
				policiesBean.setPolicyNumber(pol.getPolicyNumber()+"");
				policiesBean.setPolicyType(pol.getPolicyType());
				policiesBean.setPolicyStartDate(pol.getPolicyStartDate());
				policiesBean.setPolicyEndDate(pol.getPolicyEndDate());
				policiesBean.setPolicyStartDate(pol.getPolicyStartDate());
				policiesBean.setPremium(pol.getPremium()+"");
				policiesBeanList.add(policiesBean);
			}

		}
		return policiesBeanList;
	}
	private List<PoliciesDetailsBean> prepareDetailsListofBean(List<PoliciesDetails> policiesDetails) {
		List<PoliciesDetailsBean> policiesDetailsBeanList = null;
		if (policiesDetails != null && !policiesDetails.isEmpty()) {
			policiesDetailsBeanList = new ArrayList<PoliciesDetailsBean>();
			PoliciesDetailsBean policiesDetailsBean = null;
			for (PoliciesDetails pol : policiesDetails) {
				policiesDetailsBean = new PoliciesDetailsBean();
				policiesDetailsBean.setIdNum(pol.get_id().toString());
				policiesDetailsBean.setPolicyNumber(pol.getPolicyNumber()+"");
				policiesDetailsBean.setPolicyType(pol.getPolicyType());
				policiesDetailsBean.setPolicyStartDate(pol.getPolicyStartDate());
				policiesDetailsBean.setPolicyEndDate(pol.getPolicyEndDate());
				policiesDetailsBean.setPolicyStartDate(pol.getPolicyStartDate());
				policiesDetailsBean.setPremium(pol.getPremium()+"");
				policiesDetailsBean.setAge(pol.getAge()+"");
			    policiesDetailsBean.setPremiumDueDate(pol.getPremiumDueDate());
			    policiesDetailsBean.setAgentDetails(pol.getAgentDetails());
			    policiesDetailsBean.setPolicyTerm(pol.getPolicyTerm()+"");
			    policiesDetailsBean.setPolicyStatus(pol.getPolicyStatus());
			    policiesDetailsBean.setServingBranch(pol.getServingBranch());
			    policiesDetailsBean.setSumAssured(pol.getSumAssured()+"");
			 
			    policiesDetailsBeanList.add(policiesDetailsBean);
			}

		}
		return policiesDetailsBeanList;
	}

}
