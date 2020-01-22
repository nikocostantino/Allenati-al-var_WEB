<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
  	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
	<link rel="stylesheet" href="../css/preferiti.css" type="text/css">
	<link rel="stylesheet" href="../css/home.css" type="text/css">
	<script type="text/javascript" src="../js/home.js"></script>
	

	<meta charset="UTF-8">
	<title>ALLENATI AL VAR - Preferiti</title>
</head>
<body>
	<%@include file="header_default.jsp" %>
	
	<div class="jumbotron container">
	
		
  			<h1><span class="badge badge-light">PREFERITI</span></h1>	
  				<div class="list-group">
    				
    				
    			    	
    			    	<div class="row">
	    			    	<c:forEach items="${video_preferiti}" var="v">
	    			    		<a href="pagina_video?url=${v.url}" class="list-group-item list-group-item-action">
		    			    	<div class="column left">
		                            	
										<iframe class="embed-responsive-item" src="${v.url}" allowfullscreen></iframe>
									
										
								</div>
								<div class="column right">
		    			    		<p>${v.nome}</p>
		    			    		<p>${v.categoria[0].nome}</p>
		    			    		<p>${v.difficolta}</p>
								</div>
								</a>
							</c:forEach>
						</div>

  				
  				</div>
		</div>
		
</body>
</html>