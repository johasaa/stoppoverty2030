package controllers;

import models.SignatureModel;
import models.StoppovertyFacebookSignature;
import securesocial.core.Identity;
import securesocial.core.SocialUser;
import securesocial.core.UserId;
import securesocial.core.java.BaseUserService;
import securesocial.core.java.Token;

import com.avaje.ebean.Query;


public class StoppovertyUserservice extends BaseUserService{
	
	public StoppovertyUserservice(play.Application application) {
        super(application);
    }

    /**
     * Saves the user.  This method gets called when a user logs in.
     * This is your chance to save the user information in your backing store.
     * @param user
     * @return 
     */
    @Override
    public Identity doSave(Identity user) {
    	Query<StoppovertyFacebookSignature> signatureUser = StoppovertyFacebookSignature.find.where().ilike("externalUser", user.id().id()).query();
    	SocialUser socialUser = (SocialUser)user;
    	if (signatureUser.findIds().isEmpty()){
	    	StoppovertyFacebookSignature newSignatureUser = new StoppovertyFacebookSignature(socialUser);
	    	newSignatureUser.save();
	        SignatureModel model = new SignatureModel();
	        model.firstName = socialUser.firstName();
	        model.lastName = socialUser.lastName();
	        model.email = socialUser.email().get();
	        model.personalSignature = true;
	        model.facebookUser = newSignatureUser;
	        model.save();
    	}
    	return socialUser;
    }

    /**
     * Finds an Identity in the backing store.       
     * @return an Identity instance or null if no user matches the specified id
     */
    @Override
    public Identity doFind(UserId userId) {
        Query<StoppovertyFacebookSignature> signatureUser = StoppovertyFacebookSignature.find.where().ilike("externalUser", userId.id()).query();
        SocialUser user = null;
        if (signatureUser.findRowCount() == 1){
        	user = signatureUser.findList().get(0).getSocialUser();
        }
        
    	return user;
    }

    /**
     * Finds an identity by email and provider id.
     *
     * Note: If you do not plan to use the UsernamePassword provider just provide en empty
     * implementation.
     *
     * @param email - the user email
     * @param providerId - the provider id
     * @return an Identity instance or null if no user matches the specified id
     */
    @Override
    public Identity doFindByEmailAndProvider(String email, String providerId) {
        // implement me
    	return null;
    }

    /**
     * Saves a token
     */
    @Override
    public void doSave(Token token) {
        // implement me
    }

    /**
     * Finds a token by id
     *
     * Note: If you do not plan to use the UsernamePassword provider just provide en empty
     * implementation
     *
     * @return a Token instance or null if no token matches the id
     */
    @Override
    public Token doFindToken(String tokenId) {
        // implement me
    	return null;
    }


    /**
     * Deletes a token
     *
     * Note: If you do not plan to use the UsernamePassword provider just provide en empty
     * implementation
     *
     * @param uuid the token id
     */
    @Override
    public void doDeleteToken(String uuid) {
        // implement me
    }

    /**
     * Deletes all expired tokens
     *
     * Note: If you do not plan to use the UsernamePassword provider just provide en empty
     * implementation
     *
     */
    @Override
    public void doDeleteExpiredTokens() {
        // implement me
    }

}
