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
	<script type="text/javascript" src="../js/home.js"></script>
	
<meta charset="UTF-8">
<title>ALLENATI AL VAR - Statistiche</title>
</head>
<body>
	<%@include file="header_default.jsp" %>  
   
   
   
   
	<div id="jumbo1" class="jumbotron">
	
            
                    <!-- /.row -->
                    <div class="row">
                        <div class="col-lg-8">
                            <div class="panel panel-default">
                                
                                <!-- /.panel-heading -->
                                <div class="panel-body">
                                    <div id="morris-area-chart"></div>
                                </div>
                                <!-- /.panel-body -->
                            </div>
                            <!-- /.panel -->
                            
                            
                        </div>
                        <!-- /.col-lg-8 -->
                        <div class="col-lg-4">
                            
                            <!-- /.panel -->
                            <div class="panel panel-default">
                                <div class="panel-heading">
                                    <i class="fa fa-bar-chart-o fa-fw"></i> Donut Chart Example
                                </div>
                                <div class="panel-body">
                                    <div id="morris-donut-chart"></div>
                                    
                                    <a href="#" class="btn btn-default btn-block">View Details</a>
                                </div>
                                <!-- /.panel-body -->
                            </div>
                            
                        </div>
                        <!-- /.col-lg-4 -->
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