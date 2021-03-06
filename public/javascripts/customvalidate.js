$(document).ready(function(){

	// Validate
	// http://bassistance.de/jquery-plugins/jquery-plugin-validation/
	// http://docs.jquery.com/Plugins/Validation/
	// http://docs.jquery.com/Plugins/Validation/validate#toptions
	
	jQuery.validator.addMethod("containsNum", function(input, element) {
		var len = input.length;			
		var compare = input.match(/[0-9]/gi);		
	    return 	(compare != null ? (compare.length == len) : false);
	}, "This field must only contain numbers");
	
	jQuery.validator.addMethod("containsChars", function(input, element) {
		var compare = input.match(/[0-9]/gi);	
		return 	compare == null || (compare != null ? compare.length == 0 : true);
	}, "This field must only contain valid character from A-Z");

		$('#signNoFBForm').validate({
	    rules: {
	      firstName: {
	        minlength: 2,
	        required: true,
	        "containsChars" : true
	      },
	      lastName: {
	    	  minlength: 2,
	    	  required: true,
	    	  "containsChars" : true
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
		$('#signGroupForm').validate({
		    rules: {
		      firstName: {
		        minlength: 2,
		        required: true,
		        "containsChars" : true
		      },
		      lastName: {
		    	  minlength: 2,
		    	  required: true,
			      "containsChars" : true
		      },
		      email: {
		        required: true,
		        email: true	              	
		      },
		      groupName: {
			        required: true,
		      		minlength: 2
		      },
		      numberOfSignatures: {
			        required: true,
			        minlength: 1,
			        "containsNum" : true      	
		      },
		    },
		    highlight: function(element) {
				$(element).closest('.control-group').removeClass('success').addClass('error');
			},
			success: function(element) {
				element
				.text('OK!').addClass('valid')
				.closest('.control-group').removeClass('error').addClass('success');
			},
			messages: {
				email: {
					email: "Your email must be of format: something@domain.com or similar"
				},
				groupName: {
					required: "Please enter a group name"
				}
			}
		  });
		
		$('#signOrgForm').validate({
		    rules: {
		      firstName: {
		        minlength: 2,
		        required: true,
		        "containsChars" : true
		      },
		      lastName: {
		    	  minlength: 2,
		    	  required: true,
			      "containsChars" : true
		      },
		      email: {
		        required: true,
		        email: true,		              	
		      },
		      organisation: {
			        required: true,
		      		minlength: 2
		      },
		      numberOfSignatures: {
			        required: true,
			        minlength: 1,
			        "containsNum" : true      	
		      },
		    },
		    highlight: function(element) {
				$(element).closest('.control-group').removeClass('success').addClass('error');
			},
			success: function(element) {
				element
				.text('OK!').addClass('valid')
				.closest('.control-group').removeClass('error').addClass('success');
			},
			messages: {
				email: {
					email: "Your email must be of format: something@domain.com or similar"
				},
				organisation: {
					required: "pleas enter a organisation name"
				}
			}
		  });
	    
			

}); // end document.ready
