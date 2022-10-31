/**
 * 
 */
package wso2.nucsoft.mediator;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.synapse.ManagedLifecycle;
import org.apache.synapse.MessageContext;
import org.apache.synapse.core.SynapseEnvironment;
import org.apache.synapse.mediators.AbstractMediator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;



/**
 * @author Nucsoft
 *
 */
public class CustomMediator extends AbstractMediator{

	private Logger logger = Logger.getLogger(this.getClass());
	public boolean mediate(MessageContext context) {
		String  requestJson = StringUtils.stripToEmpty((String) context.getProperty("payload"));
		JSONObject jsonObject=null;
		logger.info("Request Received in Class Mediator==============>"+requestJson);
		System.out.println("Request Received in Class Mediator==============>"+requestJson);
		try {
			jsonObject = new JSONObject(requestJson);
			
			JSONObject jsonObject2 = new JSONObject(jsonObject.get("mst_inflightCollection").toString());
			JSONArray jsonArray = new JSONArray(jsonObject2.get("mst_inflight").toString());
			JSONObject jsonObject3 = (JSONObject) jsonArray.get(0);
			JSONObject finalJsonObj = new JSONObject();
			JSONObject genericRequestData = new JSONObject();
			genericRequestData.put("applicationNumber",jsonObject3.get("ap_applno").toString());
			genericRequestData.put("requestDate",jsonObject3.get("requestDate").toString());
			genericRequestData.put("userId","6012465");
			JSONObject submitToDOBReqModel = new JSONObject();
			submitToDOBReqModel.put("leadSource","2050002");		
			finalJsonObj.put("genericRequestData", genericRequestData);		
			JSONArray jsonArray2 = new JSONArray();
			for(int i=0;i<jsonArray.length();i++) {
				JSONObject jsonObject4 = (JSONObject) jsonArray.get(i);	
				JSONObject jsonObject5 = new JSONObject();
				jsonObject5.put("applicantType",jsonObject4.get("applicantType").toString());
				jsonObject5.put("firstName",jsonObject4.get("firstName").toString());
				jsonObject5.put("middleName",jsonObject4.get("middleName").toString());
				jsonObject5.put("lastName",jsonObject4.get("lastName").toString());
				
				jsonObject5.put("mobileNo",jsonObject4.get("mobileNo").toString());
				jsonObject5.put("dob",jsonObject4.get("dob").toString());
				jsonObject5.put("aadharNo",jsonObject4.get("aadharNo").toString());
				
				jsonObject5.put("panNo",jsonObject4.get("panNo").toString());
				jsonObject5.put("gender",jsonObject4.get("gender").toString());
				jsonObject5.put("email",jsonObject4.get("email").toString());
				
				jsonObject5.put("telephoneNo",jsonObject4.get("telephoneNo").toString());
				jsonObject5.put("alternateMobileNo",jsonObject4.get("alternateMobileNo").toString());
				jsonObject5.put("preferredLang",jsonObject4.get("preferredLang").toString());
				jsonObject5.put("branchId",jsonObject4.get("branchId").toString());
				JSONArray jsonArray3 = new JSONArray();
				JSONObject jsonObject6 = new JSONObject();
				JSONObject communicationAddress = new JSONObject();
				communicationAddress.put("addressLine1",jsonObject4.get("addressLine1_1").toString());
				communicationAddress.put("addressLine2",jsonObject4.get("addressLine2_1").toString());
				communicationAddress.put("landmark",jsonObject4.get("landmark_1").toString());
				communicationAddress.put("pincode",jsonObject4.get("pincode_1").toString());
				communicationAddress.put("country",jsonObject4.get("country_1").toString());
				communicationAddress.put("state",jsonObject4.get("state_1").toString());
				communicationAddress.put("district",jsonObject4.get("district_1").toString());
				JSONObject permanentAddress = new JSONObject();
				permanentAddress.put("addressLine1",jsonObject4.get("addressLine1_2").toString());
				permanentAddress.put("addressLine2",jsonObject4.get("addressLine2_2").toString());
				permanentAddress.put("landmark",jsonObject4.get("landmark_2".toString()));
				permanentAddress.put("pincode",jsonObject4.get("pincode_2").toString());
				permanentAddress.put("country",jsonObject4.get("country_2").toString());
				permanentAddress.put("state",jsonObject4.get("state_2").toString());
				permanentAddress.put("district",jsonObject4.get("district_2").toString());
				jsonObject6.put("communicationAddress",communicationAddress);
				jsonObject6.put("permanentAddress",permanentAddress);
				jsonArray3.put(jsonObject6);
				jsonObject5.put("addressDetails",jsonArray3);
				JSONObject applicantIncomeDetails = new JSONObject();
				applicantIncomeDetails.put("incomeType",jsonObject4.get("incomeType").toString());
				applicantIncomeDetails.put("netIncomeFromBusiness",jsonObject4.get("netIncomeFromBusiness").toString());
				applicantIncomeDetails.put("obligation",jsonObject4.get("obligation").toString());
				applicantIncomeDetails.put("otherIncome",jsonObject4.get("otherIncome").toString());
				
				applicantIncomeDetails.put("grossIncome",jsonObject4.get("grossIncome").toString());
				applicantIncomeDetails.put("netIncome",jsonObject4.get("netIncome").toString());
				applicantIncomeDetails.put("expenses",jsonObject4.get("expenses").toString());
				applicantIncomeDetails.put("grossSalary",jsonObject4.get("grossSalary").toString());
				applicantIncomeDetails.put("netSalary",jsonObject4.get("netSalary").toString());
				
				jsonObject5.put("applicantIncomeDetails",applicantIncomeDetails);
				JSONObject insuranceDetails = new JSONObject();
				insuranceDetails.put("height", jsonObject4.get("height").toString());
				insuranceDetails.put("weight", jsonObject4.get("weight").toString());
				insuranceDetails.put("nomineeName", jsonObject4.get("nomineeName").toString());
				insuranceDetails.put("birthPlace", jsonObject4.get("birthPlace").toString());
				insuranceDetails.put("isSmoker", jsonObject4.get("isSmoker").toString());
			
				
				jsonObject5.put("insuranceDetails",insuranceDetails);
				jsonArray2.put(jsonObject5);
			}
			submitToDOBReqModel.put("applicantDetails",jsonArray2);
			JSONObject applicantPropertyDetails = new JSONObject();
			applicantPropertyDetails.put("product",jsonObject3.get("product").toString());
			applicantPropertyDetails.put("customerType",jsonObject3.get("customerType").toString());
			applicantPropertyDetails.put("subProduct",jsonObject3.get("subProduct").toString());
			applicantPropertyDetails.put("loanPurpose",jsonObject3.get("loanPurpose").toString());
			applicantPropertyDetails.put("useOfProperty",jsonObject3.get("useOfProperty").toString());
			
			applicantPropertyDetails.put("propertyIdentified",jsonObject3.get("propertyIdentified").toString());
			applicantPropertyDetails.put("sellerName",jsonObject3.get("sellerName").toString());
			applicantPropertyDetails.put("projectName",jsonObject3.get("projectName").toString());
			
			applicantPropertyDetails.put("addressLine1",jsonObject3.get("addressLine1_3").toString());
			applicantPropertyDetails.put("addressLine2",jsonObject3.get("addressLine2_3").toString());
			applicantPropertyDetails.put("landmark",jsonObject3.get("landmark_3").toString());
			
			applicantPropertyDetails.put("pincode",jsonObject3.get("pincode_3").toString());
			applicantPropertyDetails.put("country",jsonObject3.get("country_3").toString());
			applicantPropertyDetails.put("state",jsonObject3.get("state_3").toString());
			applicantPropertyDetails.put("district",jsonObject3.get("district_3").toString());
			applicantPropertyDetails.put("approvedLocation",jsonObject3.get("approvedLocation").toString());
			applicantPropertyDetails.put("completionStatus",jsonObject3.get("completionStatus").toString());
			applicantPropertyDetails.put("underConstructionStage",jsonObject3.get("underConstructionStage").toString());
			
			applicantPropertyDetails.put("landCost",jsonObject3.get("landCost").toString());
			applicantPropertyDetails.put("agreementValue",jsonObject3.get("agreementValue").toString());
			applicantPropertyDetails.put("amenitiesAgreementValue",jsonObject3.get("amenitiesAgreementValue").toString());
			applicantPropertyDetails.put("registrationCharge",jsonObject3.get("registrationCharge").toString());
			applicantPropertyDetails.put("constructionCost",jsonObject3.get("constructionCost").toString());
			
			applicantPropertyDetails.put("incidentalValue",jsonObject3.get("incidentalValue").toString());
			applicantPropertyDetails.put("total_estimated_cost",jsonObject3.get("total_estimated_cost").toString());
			applicantPropertyDetails.put("savings",jsonObject3.get("savings").toString());
			
			applicantPropertyDetails.put("disposalOfAssets",jsonObject3.get("disposalOfAssets").toString());
			applicantPropertyDetails.put("familyOrFriends",jsonObject3.get("familyOrFriends").toString());
			
			applicantPropertyDetails.put("other",jsonObject3.get("other").toString());
			applicantPropertyDetails.put("totalOwnContribution",jsonObject3.get("totalOwnContribution").toString());
			applicantPropertyDetails.put("requirementOfLoan",jsonObject3.get("requirementOfLoan").toString());
			applicantPropertyDetails.put("tenure",jsonObject3.get("tenure").toString());
			
			
			
			submitToDOBReqModel.put("applicantPropertyDetails",applicantPropertyDetails);
			JSONObject eligibilityDetails = new JSONObject();
			eligibilityDetails.put("rateOfInterest",jsonObject3.get("rateOfInterest").toString());
			submitToDOBReqModel.put("eligibilityDetails",eligibilityDetails);
			JSONObject paymentDetails = new JSONObject();
			paymentDetails.put("isItStaffLoan",jsonObject3.get("isItStaffLoan").toString());
			paymentDetails.put("paymentMode",jsonObject3.get("paymentMode").toString());
			paymentDetails.put("paymentReferenceNo",jsonObject3.get("paymentReferenceNo").toString());
			paymentDetails.put("bankName",jsonObject3.get("bankName").toString());
			paymentDetails.put("amount",jsonObject3.get("amount").toString());
			paymentDetails.put("gst",jsonObject3.get("gst").toString());
			paymentDetails.put("gstAmount",jsonObject3.get("gstAmount").toString());
			paymentDetails.put("totalAmount",jsonObject3.get("totalAmount").toString());
			
			submitToDOBReqModel.put("paymentDetails",paymentDetails);
			finalJsonObj.put("submitToDOBReqModel", submitToDOBReqModel);
			context.setProperty("requestPayload", finalJsonObj.toString());
			logger.info("Final Json Object in Class Mediator======>"+finalJsonObj.toString());
			System.out.println("Final Json Object in Class Mediator======>"+finalJsonObj.toString());
			
		}catch( Exception e){
			logger.error("Error Caused By------->",e);
			System.out.println("Error Caused By------->"+e);
			context.setProperty("requestPayload", requestJson);
			logger.info("Final Json Object in Class Mediator======>"+requestJson);
			System.out.println("Final Json Object in Class Mediator======>"+requestJson);
		}
		
		return true;
	}

}
