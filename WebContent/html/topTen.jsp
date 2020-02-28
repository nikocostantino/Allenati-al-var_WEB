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

	<title>ALLENATI AL VAR - Top Ten</title>
	
</head>

<body>

	<%@include file="header_default.jsp" %>
	
	<div class="jumbotron container" id="risposte">
	
		<h1><span class="badge badge-dark">TOP TEN</span></h1>
		
		
								
					<table class="table">
						  <thead class="thead-dark">
						    <tr>
						      <th scope="col">#</th>
						      <th scope="col">Nome</th>
						      <th scope="col">Cognome</th>
						      <th scope="col">Media</th>
						    </tr>
						  </thead>
						  <tbody>
							<c:if test="${size > 0}">
							<tr <c:if test="${utenteCorrente == topTen[0].email}"> class="table-primary"</c:if>>
							
						    
						      <th scope="row">1</th>
						      <td>${topTen[0].nome}</td>
						      <td>${topTen[0].cognome}</td>
						      <td>${topTen[0].media}</td>
						    </tr>
						    </c:if>
						    <c:if test="${size > 1}">
							<tr <c:if test="${utenteCorrente == topTen[1].email}"> class="table-primary"</c:if>>
						      <th scope="row">2</th>
						      <td>${topTen[1].nome}</td>
						      <td>${topTen[1].cognome}</td>
						      <td>${topTen[1].media}</td>
						    </tr>
						    </c:if>
							<c:if test="${size > 2}">						    
							<tr <c:if test="${utenteCorrente == topTen[2].email}"> class="table-primary"</c:if>>
						      <th scope="row">3</th>
						      <td>${topTen[2].nome}</td>
						      <td>${topTen[2].cognome}</td>
						      <td>${topTen[2].media}</td>
						    </tr>
						    </c:if>
							<c:if test="${size > 3}">						    
							<tr <c:if test="${utenteCorrente == topTen[3].email}"> class="table-primary"</c:if>>
						      <th scope="row">4</th>
						      <td>${topTen[3].nome}</td>
						      <td>${topTen[3].cognome}</td>
						      <td>${topTen[3].media}</td>
						    </tr>
						    </c:if>
						    <c:if test="${size > 4}">
							<tr <c:if test="${utenteCorrente == topTen[4].email}"> class="table-primary"</c:if>>
						      <th scope="row">5</th>
						      <td>${topTen[4].nome}</td>
						      <td>${topTen[4].cognome}</td>
						      <td>${topTen[4].media}</td>
						    </tr>
						    </c:if>
							<c:if test="${size > 5}">
							<tr <c:if test="${utenteCorrente == topTen[5].email}"> class="table-primary"</c:if>>
						      <th scope="row">6</th>
						      <td>${topTen[5].nome}</td>
						      <td>${topTen[5].cognome}</td>
						      <td>${topTen[5].media}</td>
						    </tr>
						    </c:if>
						    <c:if test="${size > 6}">
							<tr <c:if test="${utenteCorrente == topTen[6].email}"> class="table-primary"</c:if>>
						      <th scope="row">7</th>
						      <td>${topTen[6].nome}</td>
						      <td>${topTen[6].cognome}</td>
						      <td>${topTen[6].media}</td>
						    </tr>
						    </c:if>
						    <c:if test="${size > 7}">
							<tr <c:if test="${utenteCorrente == topTen[7].email}"> class="table-primary"</c:if>>
						      <th scope="row">8</th>
						      <td>${topTen[7].nome}</td>
						      <td>${topTen[7].cognome}</td>
						      <td>${topTen[7].media}</td>
						    </tr>
						    </c:if>
						    <c:if test="${size > 8}">
							<tr <c:if test="${utenteCorrente == topTen[8].email}"> class="table-primary"</c:if>>
						      <th scope="row">9</th>
						      <td>${topTen[8].nome}</td>
						      <td>${topTen[8].cognome}</td>
						      <td>${topTen[8].media}</td>
						    </tr>
						    </c:if>
						    <c:if test="${size > 9}">
							<tr <c:if test="${utenteCorrente == topTen[9].email}"> class="table-primary"</c:if>>
						      <th scope="row">10</th>
						      <td>${topTen[9].nome}</td>
						      <td>${topTen[9].cognome}</td>
						      <td>${topTen[9].media}</td>
						    </tr>
						    </c:if>
						  </tbody>
						</table>
				
					
				
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



