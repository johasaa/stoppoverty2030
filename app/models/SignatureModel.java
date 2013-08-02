package models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

import play.data.validation.Constraints.Required;
import play.db.ebean.Model;

@Entity
public class SignatureModel extends Model{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6623776961537405782L;

	@Id	public Long id;
	
	@Required public String firstName;
	
	@Required public String lastName;
	
	@Required public String email;
	
	public String organisation;
	
	public String groupName;
	
	public Long numberOfSignatures;
	
	public Boolean personalSignature;
	
	public String userId;
	
	public String providerName;
	
	public Date registeredDate;
	
	public Boolean isValid;

	public static Model.Finder<Long,SignatureModel> find = new Model.Finder<Long,SignatureModel>(Long.class, SignatureModel.class);
	
	public Boolean validate(){
		if (isValid == null){
    		isValid = false;
    		if (email != null 
    				&& (personalSignature
    						|| (groupName != null && numberOfSignatures <= 100))){
    			isValid = true;
    		}
    	}
    	return isValid;
	}

	/**
	 * @param firstName
	 * @param lastName
	 * @param email
	 * @param organisation
	 * @param groupName
	 * @param numberOfSignatures
	 * @param userId
	 */
	public SignatureModel(String firstName, String lastName, String email,
			String organisation, String groupName, Long numberOfSignatures,
			String userId) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.organisation = organisation;
		this.groupName = groupName;
		this.numberOfSignatures = numberOfSignatures;
		this.userId = userId;
	}
	
}
