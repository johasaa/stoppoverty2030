$(document).ready(function(){
	$.ajaxSetup({ cache: true });
	$.getScript('//connect.facebook.net/en_UK/all.js', function(){
	    FB.init({
	      appId: '502264383175234',
	      channelUrl: '/channel',
	      status: true,
	      xfbml: true
	    });     
	    $('#loginbutton,#feedbutton').removeAttr('disabled');
	    FB.getLoginStatus(function(response){
	    	 if (response.status === 'connected') {
	    		    // the user is logged in and has authenticated your
	    		    // app, and response.authResponse supplies
	    		    // the user's ID, a valid access token, a signed
	    		    // request, and the time the access token 
	    		    // and signed request each expire
	    		    var uid = response.authResponse.userID;
	    		    var accessToken = response.authResponse.accessToken;
	    		    console.log("uid and token: " + uid + ", " + accessToken);
	    		  } else if (response.status === 'not_authorized') {
	    		    // the user is logged in to Facebook, 
	    		    // but has not authenticated your app
	    			  console.log("not_autorized by app");
	    		  } else {
	    			console.log("not logged into facebook");
	    		  }
	    });
	    FB.Event.subscribe('auth.authResponseChange', function(response) {
	    	  console.log('The status of the session is: ' + response.status);
	    });
	});
});