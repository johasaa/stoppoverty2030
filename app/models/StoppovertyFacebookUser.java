package models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class StoppovertyFacebookUser {
	
	public String id;
	public String name;
	public String first_name;
	public String last_name;
	public String middle_name;
	public String link;
	public String username;
	public String email;
	public Boolean verified;

}
