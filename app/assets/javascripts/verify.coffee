$ -> $.get "/verify", (data) ->	
	$.each data, (index, signature) -> 
		innerHtml = $('<tr>')
		innerHtml.append $('<td>').text signature.id 
		innerHtml.append $('<td>').text signature.firstName
		innerHtml.append $('<td>').text signature.lastName
		innerHtml.append $('<td>').text signature.email
		innerHtml.append $('<td>').text signature.organisation
		innerHtml.append $('<td>').text signature.groupName
		innerHtml.append $('<td>').text signature.numberOfSignatures
		innerHtml.append $('<td>').text signature.isValid
		
		$('#verifybody').append innerHtml
	  
	   