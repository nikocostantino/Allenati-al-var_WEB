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
	<link rel="stylesheet" href="../css/index.css" type="text/css">
	<script type="text/javascript" src="../js/index.js"></script>
	<meta charset="UTF-8">
	<title>ALLENATI AL VAR - Recupera Password</title>
</head>

<body>
<div class="row">
	<div class="column col-sm-4">
		<img id="imgIndex" src="../img/logo.png">
	</div>

<div class="column col-sm-8 wrapper fadeInDown">
	  	<div id="formContent">

		 	<div class="fadeIn first">
	      		<img src="../img/referee.png" id="icon" alt="User Icon" />
	    	</div>
	    	

	    	<form method="POST" action="gestorePagine?pagina=recupera">
	      	
		      	
	      	<h3>Recupera password</h3>
	      		<input type="email" id="email" class="fadeIn second" name="email" placeholder="email" required/>
	      		
	      	
	      	
	      	<c:if test="${emailNonPresente != null}">
	      
	      			<div id="formatErrato" class='alert alert-danger alert-dismissible fade show' role='alert'><strong>L'email inserita non &egrave; presente nel sistema!</strong><br>Inserisci un'email valida.</div>
	      
	      	</c:if>
	      	
	      	<br>
	      		<button class="btn btn-primary" id="loginBTN" type="submit">Recupera</button>
	    	</form>
	
	    	<div id="formFooter">
				<div class="d-flex justify-content-center links">
					<a id="buttonRegistrati" class="btn btn-secondary" type="submit" href="gestorePagine?pagina=index">Indietro</a>
				</div>
			</div>
	
		</div>
	</div>
</div>


</body>
</html>