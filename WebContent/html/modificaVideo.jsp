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
	
	<link rel="stylesheet" href="../css/aggiungi_video.css" type="text/css">
	<link rel="stylesheet" href="../css/home.css" type="text/css">
	<script type="text/javascript" src="../js/home.js"></script>
	<meta charset="UTF-8">
	<title>ALLENATI AL VAR - Modifica Video</title>
</head>

<body>
	<%@include file="header_default.jsp" %>



<div class="jumbotron container">

  			<h1><span class="badge badge-light">MODIFICA VIDEO</span></h1>	
  			
  			<div class="row">
  			<div class="column left">
  			
  			
  			<form id="formContent">
	      	
	      	
	      	<div id="formModifica">
	      	<input type="text" id="nome" class="fadeIn second" name="nome" placeholder="nome" value="${nome}" required/>
				<div id="nomeErrato" class="inactive">
	      				  <div id="formatErrato" class='alert alert-danger alert-dismissible fade show' role='alert'><strong>Inserisci un nome!</strong></div>	
	      			
	      		</div>				
				
				
			<div class="fadeIn second" id="categoria" >
				<select id="cat" name="categoria" required>
					<option value="" disabled selected>${categoria}</option>
					<option value="rigore">rigore</option>
					<option value="rosso">rosso</option>
					<option value="giallo">giallo</option>
					<option value="fuorigioco">fuorigioco</option>					
					<option value="fallo di mano">fallo di mano</option>
				
				</select>
			</div>	
			<div id="controlloCategoria" class="inactive">
				  <div id="formatErrato" class='alert alert-danger alert-dismissible fade show' role='alert'><strong>Seleziona una categoria!</strong></div>	
			
			</div>
			
			<div class="fadeIn second" id="grado di difficoltà" >
				<select id="sel" name="grado di difficoltà" required>
					<option value="" disabled selected>${difficolta}</option>
					<option value="normale">normale</option>
					<option value="difficile">difficile</option>
					<option value="facile">facile</option>						
				</select>
			</div>
			<div id="controlloDifficolta" class="inactive">
				  <div id="formatErrato" class='alert alert-danger alert-dismissible fade show' role='alert'><strong>Seleziona un grado di difficolt&agrave;!</strong></div>	
			
			</div>
			
			<input type="text" id="opzioneRispostaCorretta" class="fadeIn second" name="opzioneRispostaCorretta" placeholder="opzione di risposta corretta" value="${rispostaCorretta}" required/>
			<div id="controlloOpzioneUno" class="inactive">
	      			<div id="formatErrato" class='alert alert-danger alert-dismissible fade show' role='alert'><strong>Inserisci l'opzione di risposta corretta!</strong></div>	
	      	</div>
	      	
			<input type="text" id="opzioneRispostaErrata" class="fadeIn second" name="opzioneRispostaErrata" placeholder="opzione di risposta errata" value="${rispostaErrata}" required/>
			<div id="controlloOpzioneDue" class="inactive">
	      			<div id="formatErrato" class='alert alert-danger alert-dismissible fade show' role='alert'><strong>Inserisci l'opzione di risposta errata!</strong></div>	
	      	</div>
	      	<div id="controlloOpzioniUguali" class="inactive">
	      			<div id="formatErrato" class='alert alert-danger alert-dismissible fade show' role='alert'><strong>Le opzioni di risposta sono uguali!</strong></div>	
	      	</div>
			
			<textarea class="fadeIn second" id="descrizione"  name="descrizione" placeholder="descrizione" required>${descrizione}</textarea>
			<div id="controlloDescrizione" class="inactive">
	      			<div id="formatErrato" class='alert alert-danger alert-dismissible fade show' role='alert'><strong>Inserisci una descrizione per il video!</strong></div>	
	      	</div>
	      	<div id="controlloLunghezzaDescrizione" class="inactive">
	      			<div id="formatErrato" class='alert alert-danger alert-dismissible fade show' role='alert'><strong>La descrizione che hai inserito è troppo lunga!</strong></div>	
	      	</div>
					  
			<div class="btn-group-toggle" data-toggle="buttons">
				<a class="btn btn-secondary" id="Indietro" href="pagina_video?url=${url}" type="submit">Indietro</a>
	      		<a class="btn btn-primary" id="Aggiungi" onclick="javascript:controllaCaratteristiche('<c:out value="${url}"/>')" href="javascript:void(0)" type="submit">Salva</a>
	      		
	      			      		

			</div>
			</div>		
	      	
	    	</form>
	    </div>
	    <div id="anteprimaVideo" class="column right">
				<iframe width='90%' id='video' src='${url}' height='56%'></iframe>	
	    	</div>
			</div> 
	    	
</div>


<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
	<script>
			
		function controllaCaratteristiche(url){
			
			var url = url;
			
			var $temp= $("form");
			var nome = $("#nome").val();
			var descrizione = $("#descrizione").val();
			var opzioneUno = $("#opzioneRispostaCorretta").val();
			var opzioneDue = $("#opzioneRispostaErrata").val();
			
			var selectorDifficolta = document.getElementById('sel');
		    var valueDifficolta = selectorDifficolta[selectorDifficolta.selectedIndex].value;
		    
		    var selectorCategoria = document.getElementById('cat');
		    var valueCategoria = selectorCategoria[selectorCategoria.selectedIndex].value;
    
		   	var lunghezzaDescrizione = descrizione.length;

		   	var bool = true;
		   	
		   	if(nome=="")
		   	{
				$temp.find("#nomeErrato").removeClass("inactive");
				bool=false;
		   	}
		   	else
		   	{
				$temp.find("#nomeErrato").addClass("inactive");
		   	}
		   	
			if(valueDifficolta=="")
			{
				$temp.find("#controlloDifficolta").removeClass("inactive");
				bool=false;
			}
			else
			{
				$temp.find("#controlloDifficolta").addClass("inactive");
			}
			
			
			if(valueCategoria=="")
			{
				$temp.find("#controlloCategoria").removeClass("inactive");
				bool=false;
			}
			else
			{
				$temp.find("#controlloCategoria").addClass("inactive");

			}

			if(descrizione == "")
			{
				$temp.find("#controlloDescrizione").removeClass("inactive");
				$temp.find("#controlloLunghezzaDescrizione").addClass("inactive");
				bool=false;

			}
			else if(lunghezzaDescrizione>100)
			{
				$temp.find("#controlloLunghezzaDescrizione").removeClass("inactive");
				$temp.find("#controlloDescrizione").addClass("inactive");
				bool=false;

			}
			else
			{
				$temp.find("#controlloDescrizione").addClass("inactive");
				$temp.find("#controlloLunghezzaDescrizione").addClass("inactive");

			}
			
			if(opzioneUno == "")
			{
				$temp.find("#controlloOpzioneUno").removeClass("inactive");
				bool=false;
			}
			else
			{
				$temp.find("#controlloOpzioneUno").addClass("inactive");
			}
			
			if(opzioneDue == "")
			{
				$temp.find("#controlloOpzioneDue").removeClass("inactive");
				bool=false;
			}
			else
			{
				$temp.find("#controlloOpzioneDue").addClass("inactive");
			}
			if(opzioneUno!="" && opzioneDue!="")
			{
				if(opzioneUno == opzioneDue)
				{
					$temp.find("#controlloOpzioniUguali").removeClass("inactive");
					bool=false;
				}
				else
				{
					$temp.find("#controlloOpzioniUguali").addClass("inactive");
				}
			}

			if(bool==true){
			
				$.ajax({
					type: "GET",
					url: "gestoreVideo?link="+url+"&&nome="+nome+"&&desc="+descrizione+"&&diff="+valueDifficolta+"&&cat="+valueCategoria+"&&opC="+opzioneUno+"&&opE="+opzioneDue,
					data: {modificaNome: nome},
					success: function(info){
						$("document").ready(function(){
								if(info.match("tuttoAPosto"))
								{
									window.location.href="gestoreVideo?videoModificato=true&&link="+url+"&&nome="+nome+"&&desc="+descrizione+"&&diff="+valueDifficolta+"&&cat="+valueCategoria+"&&opC="+opzioneUno+"&&opE="+opzioneDue;
								}
								else
								{
									var $temp= $("form");
									$temp.find("#nomeErrato").removeClass("inactive");
									$("#nomeErrato").html(info);
								}
							
							})
						}
					});
				}
		}
		
	</script>
</body>
</html>