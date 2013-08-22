package controllers;

import java.util.HashMap;
import java.util.Map;

import securesocial.core.Identity;
import securesocial.core.SocialUser;
import securesocial.core.UserId;
import securesocial.core.java.BaseUserService;
import securesocial.core.java.Token;


public class StoppovertyUserservice extends BaseUserService{
	
	private static Map<String, SocialUser> userMap = new HashMap<String, SocialUser>();
	
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
    	
    	SocialUser socialUser = (SocialUser)user;
    	userMap.put(user.id().id(), socialUser);
    
    	return socialUser;
    }

    /**
     * Finds an Identity in the backing store.       
     * @return an Identity instance or null if no user matches the specified id
     */
    @Override
    public Identity doFind(UserId userId) {        
    	return userMap.get(userId.id());
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
