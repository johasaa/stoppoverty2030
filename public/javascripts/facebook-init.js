$(document).ready(function(){
	$.ajaxSetup({ cache: true });
	$.getScript('//connect.facebook.net/en_UK/all.js', function(){
		// appid prod: 502264383175234
	    FB.init({
	      appId: '135311053344624',
	      channelUrl: '/channel',
	      status: true,
	      xfbml: true
	    });     
	    $('#loginbutton,#feedbutton').removeAttr('disabled');
	    FB.getLoginStatus(function(response){
	    	 if (response.status === 'connected') {
	    		    checkBeforeSigning(response, true, true, false);
	    		  } else if (response.status === 'not_authorized') {
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