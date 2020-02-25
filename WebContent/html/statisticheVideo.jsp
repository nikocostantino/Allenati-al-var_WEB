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
<title>ALLENATI AL VAR - Statistiche Video</title>
</head>
<body>
	<%@include file="header_default.jsp" %>  
   
   
   
   
	<div id="jumbo1" class="jumbotron">
	
            		<h1 style="text-align: left;" ><span class="badge badge-dark">STATISTICHE VIDEO</span></h1>
            
			       
                    <!-- /.row -->
                    <div class="row"  id="rigaStatVideoSopra">
                        <div class="col-lg-6">
                            <div class="alert alert-light" role="alert">
                            
                            <div class="panel panel-default">
                                
                                <div class="panel-heading">
                                    <h4><i class="fa fa-bar-chart-o fa-fw"></i>Video per categorie</h4>
                                </div>
                                <!-- /.panel-heading -->
                                <div class="panel-body">
                                    <div class="row">
                                        
                                        <!-- /.col-lg-4 (nested) -->
                                        <div class="col-lg-12">
                                            <div id="morris-bar-chart1"></div>
                                        </div>
                                        <!-- /.col-lg-8 (nested) -->
                                    </div>
                                    <!-- /.row -->
                                </div>
                                <!-- /.panel-body -->
                               
                               
                               
                            </div>
                            <!-- /.panel -->
                            
                            
                        </div>
                        </div>
                        
                        <div class="col-lg-3" >
                            <div class="alert alert-light" role="alert">
                            
                            <div class="panel panel-default">
                                
                                <div class="panel-heading">
                                    <h4><i class="fa fa-bar-chart-o fa-fw"></i>Video per difficoltà</h4>
                                </div>
                                <!-- /.panel-heading -->
                                <div class="panel-body">
                                    <div class="row">
                                        
                                        <!-- /.col-lg-4 (nested) -->
                                            <div class="col-lg-12">
                                        
                                            <div id="morris-bar-chart2"></div>
                                            </div>
                                        <!-- /.col-lg-8 (nested) -->
                                    </div>
                                    <!-- /.row -->
                                </div>
                                <!-- /.panel-body -->
                               
                               
                               
                            </div>
                            <!-- /.panel -->
                            
                            
                        </div>
                        </div>
                        
                        <!-- /.col-lg-8 -->
                        <div class="col-lg-3">
                            <div class="alert alert-light" role="alert">
                            
                            <!-- /.panel -->
                            <div class="panel panel-default">
                                <div class="panel-heading">
                                    <h4><i class="fa fa-bar-chart-o fa-fw"></i> Risposte Video</h4>
                                </div>
                                <div class="panel-body">
                                    <c:if test="${proveEffettuate!=0}"><div id="morris-donut-chart2"></div></c:if>
                                    <c:if test="${proveEffettuate==0}"><h3>Non sono state svolte prove!</h3></c:if>
                                </div>
                                <!-- /.panel-body -->
                            </div>
                            
                        </div>
                        <!-- /.col-lg-4 -->
                    </div>
                    </div>
                    
                    <div class="row" id="rigaStatVideo">
			    <div class="col-sm">
							<div class="alert alert-dark" role="alert">
							  <h4 class="alert-heading" align="center">Video inseriti</h4>
								<h1 align="center">${videoInseriti}</h1>
							  <hr>
							  <p class="mb-0">Quanti video sono presenti nel sistema.</p>
							</div>			   
				</div>
				
			    <div class="col-sm">
			      <div class="alert alert-success" role="alert">
							  <h4 class="alert-heading" align="center">Video più indovinato</h4>
								<h5 align="center"><a href="${paginaVideoCorretto}" id="collVerde">${videoCorretto}</a></h5>
							  <hr>
							  <p class="mb-0">Il video che ha ricevuto più risposte corrette tra tutte le prove</p>
							  
							</div>
			    </div>
			    <div class="col-sm">
			<div class="alert alert-danger" role="alert">
							  <h4 class="alert-heading" align="center">Video più sbagliato</h4>
								<h5 align="center"><a href="${paginaVideoSbagliato}" id="collRosso">${videoSbagliato}</a></h5>
							  <hr>
							  <p class="mb-0">Il video che ha ricevuto più risposte errate tra tutte le prove</p>
							</div>
			    </div>
			    <div class="col-sm">
			<div class="alert alert-success" role="alert">
							  <h4 class="alert-heading" align="center">Prove Superate</h4>
								<h1 align="center">${proveSuperate}</h1>
							  <hr>
							  <p class="mb-0">Numero di prove superate da tutti gli utenti nel sistema.</p>
							</div>
			    </div>
			    <div class="col-sm">
			<div class="alert alert-danger" role="alert">
							  <h4 class="alert-heading" align="center">Prove non superate</h4>
								<h1 align="center">${proveNonSuperate}</h1>
							  <hr>
							  <p class="mb-0">Numero di prove non superate da tutti gli utenti nel sistema.</p>
							</div>
			    </div>
			    <div class="col-sm">
			<div class="alert alert-info" role="alert">
							  <h4 class="alert-heading" align="center">Voto medio</h4>
								<h1 align="center">${votoMedio}</h1>
							  <hr>
							  <p class="mb-0">Voto medio risultante da tutte le prove svolte dagli utenti.</p>
							</div>
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
        <script src="../js/morris-data2.js"></script>

        <!-- Custom Theme JavaScript -->
        <script src="../js/startmin.js"></script>

    </body>
</html>