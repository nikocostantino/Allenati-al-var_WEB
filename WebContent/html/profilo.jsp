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
	<link rel="stylesheet" href="../css/storico.css" type="text/css">
	<link rel="stylesheet" href="../css/home.css" type="text/css">
	<link rel="stylesheet" href="../css/profilo.css" type="text/css">
	
	<script type="text/javascript" src="../js/home.js"></script>
	
	
	

	<meta charset="UTF-8">
	<title>ALLENATI AL VAR - Profilo</title>
</head>
<body>
	<%@include file="header_default.jsp" %>
	
	<div class="jumbotron container">
	
		
  			<h1><span class="badge badge-dark">PROFILO</span></h1>	
  			<c:if test="${modificheEffettuate == true}">
  			<div class="alert alert-success alert-dismissible fade show" id="alertModifica" role="alert">
						  <strong>Dati modificati correttamente!</strong>
						  <button type="button" class="close" data-dismiss="alert" aria-label="Close">
						    <span aria-hidden="true">&times;</span>
						  </button>
			</div>
			</c:if>
			
			<c:if test="${passwordModificata == true}">
  			<div class="alert alert-success alert-dismissible fade show" id="alertPassword" role="alert">
						  <strong>Password modificata correttamente!</strong>
						  Ti abbiamo inviato una notifica di conferma sulla tua email.
						  <button type="button" class="close" data-dismiss="alert" aria-label="Close">
						    <span aria-hidden="true">&times;</span>
						  </button>
			</div>
			</c:if>
			
  			<div class="container emp-profile" id="datiProfilo">
                <div class="row">
                    
                    <div class="col-md-6">
                        <div class="profile-head">
                                    <h5>${nome} ${cognome}</h5>
                                    <h6>
                                    <c:if test="${amministratore == true}">
                                        Utente Amministratore
                                    </c:if>
                                    <c:if test="${amministratore == false}">
                                        Utente semplice
                                    </c:if>
                                    </h6>
                            <ul class="nav nav-tabs" id="myTab" role="tablist">
                                <li class="nav-item">
                                    <a class="nav-link active" id="home-tab" data-toggle="tab" role="tab" aria-controls="home" aria-selected="true">Dati</a>
                                </li>
                                
                            </ul>
                        </div>
                    </div>
                    <div class="col-md-6">
	                    <div class="btn-group-vertical">
	
	                    						<button type="button" class="btn btn-primary" onclick="javascript:abilitaModifiche()">Modifica dati</button>
	                    						
	                    						<button type="button" class="btn btn-primary" onclick="javascript:modificaPassword()">Modifica Password</button>
	                    						
	                    						 <button type="button" class="btn btn-danger" id="eliminaAccount" data-toggle="modal" data-target="#myAccount">Elimina Account</button>
	                    </div>
                    </div>
                </div>
                <div class="row">
                    
                    <div class="col-md-8">
                        <div class="tab-content profile-tab" id="myTabContent">
                            <div class="tab-pane fade show active" id="home" role="tabpanel" aria-labelledby="home-tab">
                                        <div class="row">
                                            <div class="col-md-6">
                                                <label>Nome</label>
                                            </div>
                                            <div class="col-md-6">
                                                <p>${nome}</p>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-md-6">
                                                <label>Cognome</label>
                                            </div>
                                            <div class="col-md-6">
                                                <p>${cognome}</p>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-md-6">
                                                <label>Email</label>
                                            </div>
                                            <div class="col-md-6">
                                                <p>${email}</p>
                                            </div>
                                        </div>
               
                            </div>
                            
                            </div>
                        </div>
                    </div>
                </div>
                
                <div class="container emp-profile inactive" align="center" id="modificaDati">
                <form id="myForm">
                			<div id="vuoto" class='alert alert-danger alert-dismissible fade show inactive' role='alert'><strong>Accertati che tutti i campi siano compilati!</strong></div>
                
				      		<input type="text" id="nome" class="fadeIn second" name="nome" placeholder="nome" value="${nome}" required>
				      		
				      		<input type="text" id="cognome" class="fadeIn third" name="cognome" placeholder="cognome" value="${cognome}" required>
				      		
				      		<input type="email" id="email" class="fadeIn second" name="email" placeholder="email" value="${email}" required>
				      		<div id="emailErrata" class='alert alert-danger alert-dismissible fade show inactive' role='alert'><strong>Email già presente!</strong></div>
				      		<div id="btnModificaDati" align="center">
				      		<button type="button" class="btn btn-danger" onclick="annullaModifiche()">Annulla</button>
				      		<button type="button" class="btn btn-primary" onclick="salvaModifiche()">Salva</button>
				      		</div>
	      		</form>
                </div>
                
                
                <div class="container emp-profile inactive" align="center" id="modificaPassword">
                <form id="myForm">
                			<div id="vuoto2" class='alert alert-danger alert-dismissible fade show inactive' role='alert'><strong>Accertati che tutti i campi siano compilati!</strong></div>
                
				      		<input type="password" id="passwordAttuale" class="fadeIn second" name="passwordAttuale" placeholder="password attuale" required>
				      		
				      		<input type="password" id="nuovaPassword" class="fadeIn third" name="nuovaPassword" placeholder="nuova password" required>
				      		
				      		<input type="password" id="confermaPassword" class="fadeIn third" name="confermaPassword" placeholder="conferma nuova password" required>
				      		<div id="errorePassword" class='alert alert-danger alert-dismissible fade show inactive' role='alert'>
				      		<strong>Errore! Accertati che:</strong>
				      		<ul>
				      		<li>- la password attuale sia corretta</li>
				      		<li>- nuova password e conferma password corrispondano</li>
				      		<li>- la nuova password sia diversa dalla precedente</li>
				      		</ul>
				      		</div>
				      		
				      		
				      		
				      		<div id="btnModificaDati" align="center">
				      		<button type="button" class="btn btn-danger" onclick="annullaModifiche()">Annulla</button>
				      		<button type="button" class="btn btn-primary" onclick="salvaPassword()">Salva</button>
				      		</div>
	      		</form>
                </div>
                       
        </div>

	
		
</body>

<!-- INIZIO CUSTOM ALERT -->
<div class="modal fade" id="myAccount">
    <div class="modal-dialog modal-sm">
      <div class="modal-content">
      
        <!-- Modal Header -->
        <div class="modal-header">
          <h4 class="modal-title">Sei sicuro?</h4>
          <button type="button" class="close" data-dismiss="modal">&times;</button>
        </div>
        
        <!-- Modal body -->
        <div class="modal-body">
          Stai per eliminare il tuo account.
        </div>
        
        <!-- Modal footer -->
        <div class="modal-footer">
          <button type="button" class="btn btn-secondary" data-dismiss="modal">Annulla</button>
          <a class="btn btn-danger" href="gestoreAccount?elimina=true">Elimina</a>
        </div>
        
      </div>
    </div>
  </div>

<!-- FINE CUSTOM ALERT -->

</html>


<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>

<script>

	function annullaModifiche()
	{
		
		location.href="gestorePagine?pagina=profilo";


	}
	
	function abilitaModifiche()
	{
		$("#datiProfilo").addClass("inactive");
		$("#modificaDati").removeClass("inactive");
		$("#alertModifica").addClass("inactive");
		$("#emailErrata").addClass("inactive");		
	}
	
	function modificaPassword()
	{
		$("#datiProfilo").addClass("inactive");
		$("#modificaPassword").removeClass("inactive");
		$("#alertPassword").addClass("inactive");
	}

	
	
		function salvaModifiche(){
			var nome = $("#nome").val();
			var cognome = $("#cognome").val();
			var email = $("#email").val();
			var bool = true;
			if(nome == "" || cognome == "" || email == "")
			{
				$("#vuoto").removeClass("inactive");
				bool = false;
			}
			if(bool == true)
			{
				$.ajax({
					type: "GET",
					
					
					url: "gestoreAccount?nome="+nome+"&&cognome="+cognome+"&&email="+email,
					data: {modifiche: "true"},
					success: function(info){
						if(info.match("erroreEmail"))
						{
							$("#emailErrata").removeClass("inactive");
							$("#alertModifica").addClass("inactive");
						}
						else
						{
							location.href="gestorePagine?pagina=profilo&&salvaModifiche=true";
						}
						
						
					}
				});
			}
		}
	
		function salvaPassword(){
			var passwordAttuale = $("#passwordAttuale").val();
			var nuovaPassword = $("#nuovaPassword").val();
			var confermaPassword = $("#confermaPassword").val();
			var bool = true;
			if(passwordAttuale == "" || nuovaPassword == "" || confermaPassword == "")
			{
				$("#vuoto2").removeClass("inactive");
				bool = false;
			}
			if(bool == true)
			{
				$("#vuoto2").addClass("inactive");

				$.ajax({
					type: "GET",
					
					
					url: "gestoreAccount?passwordAttuale="+passwordAttuale+"&&nuovaPassword="+nuovaPassword+"&&confermaPassword="+confermaPassword,
					data: {modificaPassword: "true"},
					success: function(info){
						if(info.match("errorePassword"))
						{
							$("#errorePassword").removeClass("inactive");
						}
						else
						{
							location.href="gestorePagine?pagina=profilo&&salvaPassword=true";
						}
						
						
					}
				});
			}
		}
		

</script>





