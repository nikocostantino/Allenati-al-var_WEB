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
	<link rel="stylesheet" href="../css/home.css" type="text/css">
		<link rel="stylesheet" href="../css/richiesteAmministratori.css" type="text/css">
	
	<script type="text/javascript" src="../js/home.js"></script>

	<meta charset="UTF-8">

	<title>ALLENATI AL VAR - Richieste Amministratori</title>
	
</head>

<body>

	<%@include file="header_default.jsp" %>
	
	<div class="jumbotron container" id="risposte">
	
		<h1><span class="badge badge-dark">RICHIESTE AMMINISTRATORI</span></h1>
		
		<ul class="list-group">
		
			<c:forEach items="${richiesteAmministratori}" var="s">
					<li class="list-group-item">
						
						
								
						
						
								<div class="row">
							
								<div class="col-sm">
									<h2><span class="badge badge-light">Richiesta amministratore da ${s}</span></h2>

								</div>	
								<div class="col-sm">
								<div id="ricAmm" align="right">
								<a type="button" class="btn btn-success btn-sm" href="javascript:accetta('<c:out value="${s}"/>')">Approva</a>
								<a type="button" class="btn btn-info btn-sm" href="gestorePagine?pagina=statistiche&&email=${s}">Statistiche</a>
								<a type="button" class="btn btn-danger btn-sm" href="javascript:declina('<c:out value="${s}"/>')">Declina</a>
								
								</div>
								</div>	
								
								</div>
						
					</li>
				
					
				
			</c:forEach>
		</ul>
	</div>


</body>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
	<script>
			
		function declina(email){
					var email = email;
				$.ajax({
					type: "GET",
					url: "gestoreAccount",
					data: {declina: email},
					success: function(info){
						$("document").ready(function(){
									location.href="gestorePagine?pagina=richiesteAmministratori";					
							})
						}
					});
	
		}
	</script>
	
	<script>
			
		function accetta(email){
					var email = email;
				$.ajax({
					type: "GET",
					url: "gestoreAccount",
					data: {accettaRichiesta: email},
					success: function(info){
						$("document").ready(function(){
									location.href="gestorePagine?pagina=richiesteAmministratori";					
							})
						}
					});
	
		}
	</script>
</html>



