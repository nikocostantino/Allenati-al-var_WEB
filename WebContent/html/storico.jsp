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
	<link rel="stylesheet" href="../css/esito.css" type="text/css">
	<link rel="stylesheet" href="../css/home.css" type="text/css">
	<script type="text/javascript" src="../js/home.js"></script>

	<meta charset="UTF-8">

	<title>ALLENATI AL VAR - Storico</title>
	
</head>

<body>

	<%@include file="header_default.jsp" %>
	
	<div class="jumbotron container" id="risposte">
	
		<h1><span class="badge badge-dark">STORICO</span></h1>
		
		<ul class="list-group">
		
			<c:forEach items="${storico}" var="s">
				<c:if test="${s.risultato==true}">
					<li class="list-group-item list-group-item-success">
						
							<div class="row" id="storicoRiga">
							
								<div class="col-sm" >
									<h2><span class="badge badge-light">Prova del ${s.data}</span></h2>									
								</div>	
								<div class="col-sm" >
									<h2><span class="badge badge-success">Esito: POSITIVO</span></h2>
								</div>
								<div class="col-sm" >
									 <h2><a type="button" class="btn btn-info btn-sm" href="esito?data=${s.data}&&id_esito=${s.id}">Visualizza</a></h2>					
								</div>
								
							</div>
						
					</li>
				</c:if>
				<c:if test="${s.risultato==false}">
					<li class="list-group-item list-group-item-danger">
							<div class="row" id="storicoRiga">
							
								<div class="col-sm" >
									<h2><span class="badge badge-light">Prova del ${s.data}</span></h2>									
								</div>	
								<div class="col-sm" >
									<h2><span class="badge badge-danger">Esito: NEGATIVO</span></h2>
								</div>
								<div class="col-sm" >
									 <h2><a type="button" class="btn btn-info btn-sm" href="esito?data=${s.data}&&id_esito=${s.id}">Visualizza</a></h2>					
								</div>
								
							</div>
													
					</li>
				</c:if>
					
				
			</c:forEach>
		</ul>
	</div>

</body>
</html>