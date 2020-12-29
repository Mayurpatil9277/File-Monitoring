<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>
	$(document).ready(function(){
		
		getDirStatus();
		//setInterval(getDirStatus(), 500);
	});
	
	function getDirStatus(){
		$.ajax({
			  url: "http://localhost:8080/filemonitor/health",
			  context: document.body
			}).done(function(data ) {
				//alert(data );
			  $( "#resultDiv" ).append( '<p>'+data+'</p>' );
			  getDirStatus();
			});	
		}
</script>
</head>
<body>
	<div id="resultDiv">${message}
	
	</div>
</body>
</html>