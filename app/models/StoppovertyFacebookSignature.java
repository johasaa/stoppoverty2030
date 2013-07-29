package models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

import play.data.validation.Constraints.Email;
import play.db.ebean.Model;
import securesocial.core.SocialUser;

@Entity
public class StoppovertyFacebookSignature extends Model {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3184566644710658183L;
	
	
	@Id
	private Long userId;
	
	//Facebook user
	private String externalUser;
	
	//Facebook username
	private String userName;
	
	//Facebook oauth2 acessToken
	private String userToken;
	
	//Expiry date for the token
	private Long expiryDate;
	
	private Date createdDate;
	
	//url to the facebook profile
	private String facebookUrl;
	
	//facebook email
	@Email
	private String email;

	/**
	 * @param socialUser
	 */
	public StoppovertyFacebookSignature(SocialUser user) {
		this.externalUser = user.id().id();
		this.createdDate = new Date();
	}

	/**
	 * @return the externalUser
	 */
	public String getExternalUser() {
		return externalUser;
	}

	/**
	 * @param externalUser the externalUser to set
	 */
	public void setExternalUser(String externalUser) {
		this.externalUser = externalUser;
	}

	/**
	 * @return the userId
	 */
	public Long getUserId() {
		return userId;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @return the userToken
	 */
	public String getUserToken() {
		return userToken;
	}

	/**
	 * @return the expiryDate
	 */
	public Long getExpiryDate() {
		return expiryDate;
	}

	/**
	 * @return the createdDate
	 */
	public Date getCreatedDate() {
		return createdDate;
	}

	/**
	 * @return the facebookUrl
	 */
	public String getFacebookUrl() {
		return facebookUrl;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	

	public static Model.Finder<Long,StoppovertyFacebookSignature> find = new Model.Finder<Long,StoppovertyFacebookSignature>(Long.class, StoppovertyFacebookSignature.class);

}
