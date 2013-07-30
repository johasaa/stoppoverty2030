$(document).ready(function(){

	// Validate
	// http://bassistance.de/jquery-plugins/jquery-plugin-validation/
	// http://docs.jquery.com/Plugins/Validation/
	// http://docs.jquery.com/Plugins/Validation/validate#toptions

		$('#signNoFBForm').validate({
	    rules: {
	      firstName: {
	        minlength: 2,
	        required: true
	      },
	      lastName: {
	    	  minlength: 2,
	    	  required: true
	      },
	      email: {
	        required: true,
	        email: true,
	        remote: "/checkEmail"      	
	      },	      
	    },
			highlight: function(element) {
				$(element).closest('.control-group').removeClass('success').addClass('error');
			},
			success: function(element) {
				element
				.text('OK!').addClass('valid')
				.closest('.control-group').removeClass('error').addClass('success');
			}
	  });
		
	   $.getJSON( "/checksocial" , function( data ) {
		  console.log("Received JSON data: " + data);
		  if (data == true){
			  $('.thankyou').css('display','block').hide().show().removeClass('none');
		    
		  }
	  });
			

}); // end document.ready
