package controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.avaje.ebean.Query;

import models.SignatureModel;
import play.data.Form;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.index;
import views.html.signNoFB;


public class Application extends Controller {
	
	public static Result index() {    	
        return ok(index.render("Stop Poverty 2030", getNumberOfSignatures()));
    }
    
    public static Result signPetitionForm() {   
    	
    	return ok(signNoFB.render(Form.form(SignatureModel.class), "Stop Poverty 2030"));
    }
    
    public static Result saveSignature(){
    	Form<SignatureModel> signatureForm = Form.form(SignatureModel.class).bindFromRequest();
    	if(signatureForm.hasErrors()) {
            return badRequest(signNoFB.render(signatureForm, ""));
        } else {
            SignatureModel signature = signatureForm.get();
            signature.save();
            return redirect(routes.Application.index());
        }
    }
    
    public static Result checkEmail(String email) {
    	
    	Query<SignatureModel> queryString = SignatureModel.find.where().ilike("email", email).query();
    	
    	if (queryString.findRowCount() > 0){
    		return ok(Json.toJson(email + " already exists. You can only sign once"));
    	}
    	return ok(Json.toJson(Boolean.TRUE));
    }
    
    public static Result getSignatures(){
    	List<SignatureModel> signatureList = SignatureModel.find.all();
    	return ok(Json.toJson(signatureList));    	
    }
    
    private static String getNumberOfSignatures() {
    	List<SignatureModel> signatureList = SignatureModel.find.all();
    	Integer size = signatureList.size(); 
    	
    	List<Integer> digitList = new ArrayList<Integer>();
    	
    	while (size > 0){
    		digitList.add(size % 10);
    		size /= 10;
    	}
    	int numberOfZeros = 10 - size;
    	if ((10 - size) > 0){
    		for (int i = 0 ; i <= numberOfZeros; i++){
    			digitList.add(0);
    		}
    	}
    	Collections.reverse(digitList);
    	
    	String numberOfSignatures = "";
    	
    	for (Integer i : digitList){
    		numberOfSignatures += Integer.toString(i);
    	}
    	return numberOfSignatures;
    }
    	
   
}
