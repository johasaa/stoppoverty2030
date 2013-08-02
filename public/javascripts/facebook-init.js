$(document).ready(function(){
	$.ajaxSetup({ cache: true });
	$.getScript('//connect.facebook.net/en_UK/all.js', function(){
		// appid prod: 502264383175234
		// appid dev: 135311053344624
	    FB.init({
	      appId: '502264383175234',
	      channelUrl: '/channel',
	      status: true,
	      xfbml: true
	    });     
	    $('#loginbutton,#feedbutton').removeAttr('disabled');
	    FB.getLoginStatus(function(response){
	    	 if (response.status === 'connected') {
    		 	FB.api('/me/picture', function(response){
	 		        console.log(response.data);
	 				var imgHTML = $('<img>').attr('src', response.data.url);				
	 				$('.showFBInfo').append(imgHTML);	 				
 					FB.api("/me", function (response) {
 						console.log("getting profile data to check if signed: " +response);
 						
 						$.getJSON( "/checkEmail?email=" + response.email , function( data ) {
 							console.log("checking against db if user is already signed: " + data);
 							if (data !== true){
 				   				console.log(response.id + " has already signed this petition");
 				   				alreadySignedMessage(); 				   				
 				   			 }
 						});	 			 
 					});
    		 	});	    		    
	    	 } else if (response.status === 'not_authorized') {
	    		    console.log("not_autorized by app " + response.data);   		   
	    	 } else {
	    			 console.log("not logged into facebook");
	    	 }
	    });
	    FB.Event.subscribe('auth.authResponseChange', function(response) {
	    	  console.log('The status of the session is: ' + response.status);
	    });
	});
});