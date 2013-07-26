package controllers;

import play.data.Form;
import play.data.validation.Constraints.Email;
import play.data.validation.Constraints.Required;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.index;
import views.html.signNoFB;


public class Application extends Controller {
  
	public static class SignNoFacebook{
		@Required public String firstName;
		@Required public String lastName;
		@Required public Email email;
		@Required public Boolean isPerson;
		public String group;
		public Integer numberOfSignatures;
	}
	
    public static Result index() {    	
        return ok(index.render("Stop Poverty 2030"));
    }
    
    public static Result signPetitionForm() {   
    	
    	return ok(signNoFB.render(new Form<Application.SignNoFacebook>(SignNoFacebook.class), "Stop Poverty 2030"));
    }
    
    public static Result saveSignature(){
    	Form<Application.SignNoFacebook> form = Form.form(SignNoFacebook.class).bindFromRequest();
    	if(form.hasErrors()) {
            return badRequest(signNoFB.render(form, ""));
        } else {
            SignNoFacebook signatureForm = form.get();
            return ok(""
                //hello.render(signatureForm.name, data.repeat, data.color)
            );
        }
    }
  
}
