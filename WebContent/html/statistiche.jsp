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
	
	<link href="../css/startmin.css" rel="stylesheet">
	<link href="../css/font-awesome.min.css" rel="stylesheet" type="text/css">
	<link rel="stylesheet" href="../css/home.css" type="text/css">
	<link rel="stylesheet" href="../css/statistiche.css" type="text/css">
	<script type="text/javascript" src="../js/home.js"></script>
	
<meta charset="UTF-8">
<title>ALLENATI AL VAR - Statistiche</title>
</head>
<body>
	<%@include file="header_default.jsp" %>  
   
   
   
   
	<div id="jumbo1" class="jumbotron">
	
            		<h1 style="text-align: left;" ><span class="badge badge-dark">STATISTICHE</span></h1>
            
			  <div class="row" id="rigaStat">
			    <div class="col-sm">
							<div class="alert alert-dark" role="alert">
							  <h4 class="alert-heading" align="center">Prove svolte</h4>
								<h1 align="center">${proveEffettuate}</h1>
							  <hr>
							  <p class="mb-0">Prove di autovalutazione svolte finora dall'utente.</p>
							</div>			   
				</div>
			    <div class="col-sm">
			      <div class="alert alert-success" role="alert">
							  <h4 class="alert-heading" align="center">Prove superate</h4>
								<h1 align="center">${proveSuperate}</h1>
							  <hr>
							  <p class="mb-0">Prove di autovalutazione con valutazione maggiore/uguale a 6.</p>
							</div>
			    </div>
			    <div class="col-sm">
			<div class="alert alert-danger" role="alert">
							  <h4 class="alert-heading" align="center">Prove non superate</h4>
								<h1 align="center">${proveNonSuperate}</h1>
							  <hr>
							  <p class="mb-0">Prove di autovalutazione con valutazione inferiore a 6.</p>
							</div>
			    </div>
			    <div class="col-sm">
			<div class="alert alert-info" role="alert">
							  <h4 class="alert-heading" align="center">Media voti</h4>
								<h1 align="center">${media}</h1>
							  <hr>
							  <p class="mb-0">Media delle valutazioni finora ottenute.</p>
							</div>
			    </div>
			    <div class="col-sm">
			<div class="alert alert-info" role="alert">
							  <h4 class="alert-heading" align="center">Voto più frequente</h4>
								<h1 align="center">${votoPiuFrequente}</h1>
							  <hr>
							  <p class="mb-0">Voto più alto più frequente ottenuto nelle valutazioni.</p>
							</div>
			    </div>
			    <div class="col-sm">
			<div class="alert alert-info" role="alert">
							  <h4 class="alert-heading" align="center">Voto meno frequente</h4>
								<h1 align="center">${votoMenoFrequente}</h1>
							  <hr>
							  <p class="mb-0">Voto più basso meno frequente ottenuto nelle valutazioni.</p>
							</div>
			    </div>
			  </div>
			
            
            
            
                    <!-- /.row -->
                    <div class="row">
                        <div class="col-lg-8">
                            <div class="alert alert-light" role="alert">
                            
                            <div class="panel panel-default">
                                
                                <!-- /.panel-heading -->
                                
                                <div class="panel-heading">
                                   <h4> <i class="fa fa-bar-chart-o fa-fw"></i> Andamento ultime 10 prove</h4>
                                </div>
                                <div class="panel-body">
                                     <c:if test="${proveEffettuate!=0}"><div id="morris-area-chart"></div></c:if>
                                    <c:if test="${proveEffettuate==0}"><h3>Non sono state svolte prove!</h3></c:if>
                                </div>
                                <!-- /.panel-body -->
                            </div>
                            <!-- /.panel -->
                            
                            
                        </div>
                        </div>
                        <!-- /.col-lg-8 -->
                        <div class="col-lg-4">
                            <div class="alert alert-light" role="alert">
                            
                            <!-- /.panel -->
                            <div class="panel panel-default">
                                <div class="panel-heading">
                                    <h4><i class="fa fa-bar-chart-o fa-fw"></i> Voti ottenuti</h4>
                                </div>
                                <div class="panel-body">
                                    <c:if test="${proveEffettuate!=0}"><div id="morris-donut-chart"></div></c:if>
                                    <c:if test="${proveEffettuate==0}"><h3>Non sono state svolte prove!</h3></c:if>
                                </div>
                                <!-- /.panel-body -->
                            </div>
                            
                        </div>
                        <!-- /.col-lg-4 -->
                    </div>
                    </div>
                    <!-- /.row -->
                </div>
                <!-- /.container-fluid -->
        <!-- jQuery -->
  <!--      <script src="../js/jquery.min.js"></script>

        <!-- Bootstrap Core JavaScript 
        <script src="../js/bootstrap.min.js"></script>

 --> 
        <!-- Morris Charts JavaScript -->
        <script src="../js/raphael.min.js"></script>
        <script src="../js/morris.min.js"></script>
        <script src="../js/morris-data.js"></script>

        <!-- Custom Theme JavaScript -->
        <script src="../js/startmin.js"></script>

    </body>
</html>