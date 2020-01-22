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
	<LINK rel="stylesheet" href="../css/home.css" type="text/css">
	<script type="text/javascript" src="../js/home.js"></script>
	
	<meta charset="UTF-8">
	<title>ALLENATI AL VAR - Pagina di ricerca</title>
</head>
<body>
<%@include file="header_default.jsp" %>
<!-- RISULTATO RICERCA -->
<div id="jumbo1" class="jumbotron">

		



	<h1><span class="badge badge-light">RISULTATO RICERCA</span></h1>
	
	<!--Carousel Wrapper-->
	<div id="video-carousel-piu_visti" class="carousel slide carousel-fade" data-ride="carousel">
	  
	  <!--Indicators-->
	  <ol class="carousel-indicators">
	    <li data-target="#video-carousel-piu_visti" data-slide-to="0" class="active"></li>
	    <li data-target="#video-carousel-piu_visti" data-slide-to="1"></li>
	    <li data-target="#video-carousel-piu_visti" data-slide-to="2"></li>
	  </ol>
	  <!--/.Indicators-->
	  
	  <!--Slides-->
	  <div class="carousel-inner" role="listbox" id="columnCenter">
	    
	    <c:set var = "first" scope = "session" value = "${true}"/>
	    <c:set var = "cont" scope = "request" value ="${0}" />
	    <c:forEach items="${risultatoRicerca}" var="r">
       	
	    	<c:if test="${first == true && cont % 5 == 0}">
	        	<div class="carousel-item active">
	       	</c:if>
			<c:if test="${first == false && cont % 5 == 0 }">
				<div class="carousel-item">
			</c:if>
			<div class="inline" align="center">
				<div class=rowUp>
					
						<iframe id="single_video" src="${r.url}" class="video-fluid"></iframe>
					
				</div>
				<div class="rowDown">
				 <a class="badge badge-secondary"  id="textNomeVideo" href="pagina_video?url=${r.url}">${r.nome}</a> 
				</div>
			</div>
			<c:set var = "cont" scope = "request" value ="${cont + 1}" />
			<c:set var = "first" scope = "session" value = "${false}"/>
			<c:if test="${cont % 5 == 0}">	
				</div>
			</c:if>
		</c:forEach>
	  </div>
	  <!--/.Slides-->
	  
	  <!--Controls-->
	  
	  <a id="freccia" class="carousel-control-prev" href="#video-carousel-piu_visti" role="button" data-slide="prev">
	    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
	    <span class="sr-only">Previous</span>
	  </a>
	 
	  <a id="freccia" class="carousel-control-next" href="#video-carousel-piu_visti" role="button" data-slide="next">
	    <span class="carousel-control-next-icon" aria-hidden="true"></span>
	    <span class="sr-only">Next</span>
	  </a>
	 
	  <!--/.Controls-->
	</div>
	</div> 
	<!--Carousel Wrapper-->
</div>

</body>
</html>