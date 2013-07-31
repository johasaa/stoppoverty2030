package controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import models.SignatureModel;
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
    	
    	return ok(signNoFB.render(Form.form(SignatureModel.class), "Stop Poverty 2030"));
    }
    
    public static Result thankyou(){
    	return ok(thankyou.render("Stop Poverty 2030", getNumberOfSignatures()));
    }
    
    public static Result saveSignature(){
    	Form<SignatureModel> signatureForm = Form.form(SignatureModel.class).bindFromRequest();
    	if(signatureForm.hasErrors()) {
            return badRequest(signNoFB.render(signatureForm, ""));
        } else {
            SignatureModel signature = signatureForm.get();
            if (signature.groupName == null 
            		&& signature.organisation == null){
            	signature.personalSignature = true;
            	signature.numberOfSignatures = 1L;
            }
            else {
            	signature.personalSignature = true;
            }
            signature.registeredDate = new Date();
            signature.save();
            return redirect(routes.Application.thankyou());
        }
    }
    
    public static Result saveGroup(){
    	return saveSignature();
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
    
    public static Result loginSecureSocial(){
    	SocialUser user = (SocialUser) SecureSocial.currentUser();
    	System.out.println("Getting the currentUser: " + user);
    	
    	
    	SignatureModel signatureModel = SignatureModel.find.where().eq("userId", user.id().id()).findUnique();
    	if (signatureModel == null){
	    	SignatureModel model = new SignatureModel();
	        model.firstName = user.firstName();
	        model.lastName = user.lastName();
	        model.email = user.email().get();
	        model.personalSignature = true;	
	        model.numberOfSignatures = 1L;
	        model.userId = user.id().id();
	        model.providerName = user.id().providerId();
	        model.save();
	        return redirect(routes.Application.thankyou());
        }
    	return redirect(routes.Application.index());
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
    
    public static Result shareSignature() {
    	SocialUser user = (SocialUser) SecureSocial.currentUser();
    	
		return null;    	
    }
    
    private static String getNumberOfSignatures() {
    	List<SignatureModel> signatureList = SignatureModel.find.all();    	
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
