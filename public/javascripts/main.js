$(document).ready(function(){
	   	   
	   $(".stoppovertytag").instagram({
		      hash: 'stoppoverty'
		    , clientId: 'f6edb97eb7c649688fa0b55144e2b372'
		  },'11');
	   $(".0poortag").instagram({
		      hash: '0poor'
		    , clientId: 'f6edb97eb7c649688fa0b55144e2b372'
		  },'11');

}); // end document.ready

function shareOnFacebook(){
	
	FB.getLoginStatus(function(response){
    	 if (response.status === 'connected') {			    			
    		 var uid = response.authResponse.userID;
    		 var accessToken = response.authResponse.accessToken;
    		 console.log("uid and token: " + uid + ", " + accessToken);
    		 doShare();
    	 }else {			    			  
    		  FB.login(function(response) {
				   if (response.authResponse) {
				     console.log('Welcome!  Fetching your information.... ');
				     FB.api('/me', function(response) {
				       console.log('Good to see you, ' + response.name + '.');
				     });
				     doShare();
				   } else {
				     console.log('User cancelled login or did not fully authorize.');
				   }
				 });
		  }
    	 
    });			
}

function doShare() {
	url = 'https://stoppoverty2030.herokuapp.com';
	FB.ui({
		  method: 'feed',
		  link: url,				  
		  picture: url + '/assets/images/stoppoverty_ny.jpg',
		  name: 'Stop Poverty',
		  caption: 'Eradicate absolute poverty by 2030',
		  description: 'The Earth has enough resources for everybody to live dignified lives. However, man-made policies have createt unjust distribution of resources. As a result, 2.6 billion live in absolute poverty.'
		}, function(response){
				if (response && response.post_id) {
			      alert('Post was published.');
			    } else {
			      alert('Post was not published.');
			    }
		});
}

function signWithFacebook(){
	
	FB.getLoginStatus(function(response){
    	 if (response.status === 'connected') {			    			
    		 var uid = response.authResponse.userID;
    		 var accessToken = response.authResponse.accessToken;
    		 console.log("uid and token: " + uid + ", " + accessToken);
    		 //doSign();
    		 checkBeforeSigning(response, true, true, true);
    	 }else {			    			  
    		  FB.login(function(response) {
				   if (response.authResponse) {
				     console.log('Welcome!  Fetching your information.... ');
				     FB.api('/me', function(response) {
				       console.log('Good to see you, ' + response.name + '.');
				       checkBeforeSigning(response, true, true, true);
				     });
				     
				   } else {
				     console.log('User cancelled login or did not fully authorize.');
				   }
				 });
		  }
    	 
    });			
}

function doSign() {
		var meResponse = getFBMeResponse();
		$.ajax({
			type: 'POST',
			url: '/saveSignature',
			data: JSON.stringify(response),
			dataType: 'json',
			contentType: 'application/json',
			success: function(response){},
			failure: function(response){alert(response);}
		});

}

function getFBMeResponse(){
	FB.api("/me", function (response) {
		console.log("getFBMeResponse: " +response);
		return response;
	});
}

function hasSigned(email){
	$.getJSON( "/checkEmail?email=" + email , function( data ) {
		  console.log(data);
		  var isSigned = !(data == true);
		  console.log(isSigned);
		  return isSigned == true ? true : false;
	});
}

function checkBeforeSigning(response, showImage, runMe, sign) {
	 	var uid = response.authResponse.userID;
	    var accessToken = response.authResponse.accessToken;	    		    
	    console.log("uid and token: " + uid + ", " + accessToken);
	    if (showImage === true) {
		    FB.api('/me/picture', function(response){
		        console.log(response.data);
				var imgHTML = $('<img>').attr('src', response.data.url);				
				$('.showFBInfo').append(imgHTML);
				if (runMe){ 
					FB.api("/me", function (response) {
						console.log("getFBMeResponse: " +response);
						
						$.getJSON( "/checkEmail?email=" + email , function( data ) {
							if (data === true){
				   				console.log(uid + " has already signed this petition");
				   				$('#signWithFB').addClass('hasSigned');
				   				$('#signNoFBForm').addClass('hasSigned');
				   				$('#thankyou').css('display','block').hide().show().removeClass('none');
				   				
				   			 }
							 if (sign === true){
								 doSign();
							 }
						});
						
					});
				}
			 
		    });
	    }
}
