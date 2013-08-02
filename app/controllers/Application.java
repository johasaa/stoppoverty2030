package controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.codehaus.jackson.JsonNode;

import models.SignatureModel;
import models.StoppovertyFacebookUser;
import play.data.Form;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import securesocial.core.SocialUser;
import securesocial.core.java.SecureSocial;
import views.html.index;
import views.html.signNoFB;
import views.html.thankyou;
import views.html.channel;
import views.html.verification;

import com.avaje.ebean.Query;


public class Application extends Controller {
	
	public static Result index() {    	
        return ok(index.render("Stop Poverty 2030", getNumberOfSignatures()));
    }
	
	//for facebook jssdk
	public static Result channel() {
		return ok(channel.render());
	}
    
    public static Result signPetitionForm() {   
    	
    	return ok(signNoFB.render());
    }
    
    public static Result thankyou(){
    	return ok(thankyou.render("Stop Poverty 2030", getNumberOfSignatures()));
    }
    
    public static Result saveSignature(){
    	Form<SignatureModel> signatureForm = Form.form(SignatureModel.class).bindFromRequest();
    	    	
        save(signatureForm.get());
        
    	return redirect(routes.Application.thankyou());
    }
    
    public static Result saveGroup(){
    	Form<SignatureModel> signatureForm = Form.form(SignatureModel.class).bindFromRequest();
    	System.out.println(signatureForm);
    	if (signatureForm.get() != null){
    		System.out.println((SignatureModel)signatureForm.get());
    		save(signatureForm.get());
    	}
    	return redirect(routes.Application.thankyou());
    }
    
    public static Result checkEmail(String email) {
    	
    	Query<SignatureModel> queryString = SignatureModel.find.where()
    										.eq("email", email)
    										.eq("personalSignature", true)
    										.query();
    	
    	if (queryString.findRowCount() > 0){
    		return ok(Json.toJson(email + " already exists. You can only sign once"));
    	}
    	return ok(Json.toJson(Boolean.TRUE));
    }
    
    public static Result getSignatures(){
    	List<SignatureModel> signatureList = SignatureModel.find.all();
    	return ok(Json.toJson(signatureList));    	
    }
    
    public static Result checkSocialSignature(){
    	SocialUser user = (SocialUser) SecureSocial.currentUser();
    	Boolean hasSignedAlready = Boolean.FALSE;
    	if (user != null){
    		SignatureModel signatureModel = SignatureModel.find.where().eq("userId", user.id().id()).findUnique();
    		hasSignedAlready = signatureModel != null;
    	}
    	return ok(Json.toJson(hasSignedAlready));
    }
    
    public static Result saveJSONSignature() {
    	JsonNode jsonNode = request().body().asJson();
    	if (jsonNode != null){
		StoppovertyFacebookUser fbUser = Json.fromJson(jsonNode, StoppovertyFacebookUser.class);
		SignatureModel signature = new SignatureModel(fbUser.first_name + " " + fbUser.middle_name,  
													  fbUser.last_name, 
													  fbUser.email, 
													  null, //organisation name not necessary
													  null, //groupname not necessary
													  1L, //always one from facebook
													  fbUser.id);
			save(signature);		
			return ok(jsonNode);
    	}
    	
		return ok(Json.toJson(Boolean.FALSE));    	
    }
    
    public static Result verificationList(){
    	return ok(verification.render());
    }
    
    public static Result approveSignatures() {
    	JsonNode jsonNode = request().body().asJson();
    	
    	List<JsonNode> list =  jsonNode.findValues("id");
    	List<Long> ids = new ArrayList<Long>();
    	for (JsonNode n : list){
    		System.out.println(n.asLong());
    		ids.add(n.asLong());
    	}
    	
    	for (Long id: ids){
    		SignatureModel signatureToApprove = SignatureModel.find.byId(id);
    		if (signatureToApprove != null){
    			signatureToApprove.isValid = true;
    			signatureToApprove.update();
    		}
    	}
    	
    	return ok(jsonNode.findValues("id").toString());
    }
    
    public static Result getNonValidSignatures() {
    	Query<SignatureModel> signatureValidQuery = SignatureModel.find.where().eq("isValid", false).query();
    	
    	return ok(Json.toJson(signatureValidQuery.findList()));
    }
    
    private static void save(SignatureModel signature) {
    	if (signature.groupName == null 
        		&& signature.organisation == null){
        	signature.personalSignature = true;
        	signature.numberOfSignatures = 1L;
        	signature.isValid = true;
        }
        else {
        	signature.personalSignature = false;
        }
    	if (signature.userId != null){
    		signature.providerName = "facebook";
    	}
        signature.registeredDate = new Date();
        signature.validateData();
        signature.save();        
    }
    
    private static String getNumberOfSignatures() {    	  
    	Query<SignatureModel> signatureValidQuery = SignatureModel.find.where().eq("isValid", true).query();
    	List<SignatureModel> signatureList = signatureValidQuery.findList();
    	Long signatureCount = 0L; 
    	for (SignatureModel m : signatureList){
    		signatureCount += m.numberOfSignatures;
    	}

    	System.out.println("count: " + signatureCount);
    	
    	List<Long> digitList = new ArrayList<Long>();
    	
    	while (signatureCount > 0){
    		digitList.add(signatureCount % 10);
    		signatureCount /= 10;
    	}
    	Integer signatureDigitNumber = digitList.size();
    	Long numberOfZeros = 10 - signatureDigitNumber.longValue();
    	if (numberOfZeros > 0){
    		for (int i = 0 ; i < numberOfZeros; i++){
    			digitList.add(0L);
    		}
    	}
    	Collections.reverse(digitList);
    	
    	String numberOfSignatures = "";
    	
    	for (Long i : digitList){
    		numberOfSignatures += Long.toString(i);
    	}
    	return numberOfSignatures;
    }
    	
   
}
