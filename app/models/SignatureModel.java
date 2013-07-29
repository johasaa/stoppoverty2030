package models;

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
	
//	@OneToOne
//	@JoinColumn(name="externalUser")
//	public StoppovertyFacebookSignature facebookUser;
	
	public String organisation;
	
	public String groupName;
	
	public Long numberOfSignatures;
	
	public Boolean personalSignature;

	public static Model.Finder<Long,SignatureModel> find = new Model.Finder<Long,SignatureModel>(Long.class, SignatureModel.class);
	
}
