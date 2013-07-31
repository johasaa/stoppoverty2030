$(document).ready(function(){
	   
	   $(".stoppovertytag").instagram({
		      hash: 'stoppoverty'
		    , clientId: 'f6edb97eb7c649688fa0b55144e2b372'
		  },'11');
	   $(".0poortag").instagram({
		      hash: '0poor'
		    , clientId: 'f6edb97eb7c649688fa0b55144e2b372'
		  },'11');
	   $.getJSON( "/checksocial" , function( data ) {			  
			  if (data == true){
				  $('.thankyou').css('display','block').hide().show().removeClass('none');
			    
			  }
		  });	  
			

}); // end document.ready
