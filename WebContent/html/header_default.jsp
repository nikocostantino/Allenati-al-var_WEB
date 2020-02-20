<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


 <c:if test="${utente == null}">
   			 <script language="javascript">
       				 window.location.href = "gestorePagine?pagina=index"
   			 </script>


<a href="gestorePagine?pagina=index"></a>
   			
   			
   </c:if>


<div id="headerHome" class="container" >
	<header>
	
	<img id="img_header" src="../img/header1.png">
		<!-- <h1>ALLENATI AL VAR</h1>
 			<p>Il sito per gli arbitri</p> -->
	</header>
</div>
<!-- BARRA DI NAVIGAZIONE -->
<nav class="navbar navbar-expand-lg  navbar-dark bg-dark">
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>

  <div class="collapse navbar-collapse" id="navbarSupportedContent">
    <ul class="navbar-nav mr-auto">
      <li class="nav-item active">
        <a class="nav-link" href="home">Home <span class="sr-only">(current)</span></a>
      </li>
      <li class="nav-item dropdown">
				<a class="nav-link dropdown-toggle" data-toggle="dropdown" href="#">Profilo<span class="caret"></span></a>
				<div class="dropdown-menu">
					<a  class="dropdown-item" href="gestorePagine?pagina=profilo">Account</a>		
      				<a class="dropdown-item" href="gestorePagine?pagina=statistiche">Statistiche</a>
					<a  class="dropdown-item" href="gestorePagine?pagina=preferiti">Preferiti</a>
					<a  class="dropdown-item" href="gestorePagine?pagina=storico">Storico</a>		
					<div class="dropdown-divider"></div>		
					<a class="dropdown-item" href="gestorePagine?pagina=index&&logout=true">Logout</a>				
				</div>
		</li>
		<li class="nav-item">
      <a class="nav-link" id="prova_autovalutazione" href="#" data-toggle="modal" data-target="#myModal">Prova Autovalutazione</a>
    </li>
    
    
    <c:if test="${amministratore == true}">
				<li class="nav-item">
			    <a class="nav-link" href="gestorePagine?pagina=aggiungiVideo">Aggiungi Video</a>
  				</li>
	</c:if>
	<c:if test="${amministratore == true}">
				<li class="nav-item">
			    <a class="nav-link" href="gestorePagine?pagina=richiesteAmministratori">Richieste Amministratori</a>
  				</li>
	</c:if>
	
	<c:if test="${amministratore == true}">
				<li class="nav-item">
			    <a class="nav-link" href="gestorePagine?pagina=statisticheVideo">Statistiche Video</a>
  				</li>
	</c:if>
  	<li class="nav-item">
			    <a class="nav-link" href="gestorePagine?pagina=topTen">Top Ten</a>
  	</li>  
  
    <li class="nav-item">
      <a class="nav-link" href="gestorePagine?pagina=info">Info</a>
      
	</li>
    </ul>
    <form class="form-inline my-2 my-lg-0" method="GET" action="risultatoRicerca">
    
   
    <div class="alert my-2 my-sm-0" id="benvenuto">
			<font color="white"><strong>${datiUtente}</strong></font>
		</div>
      <input class="form-control mr-sm-2" name="textRicerca" type="search" placeholder="Cerca un video..." aria-label="Search">
      <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Cerca</button>
    </form>
  </div>
</nav>
<!-- FINE BARRA DI NAVIGAZIONE -->


<!-- INIZIO CUSTOM ALERT -->
<div class="modal fade" id="myModal">
    <div class="modal-dialog modal-sm">
      <div class="modal-content">
      
        <!-- Modal Header -->
        <div class="modal-header">
          <h4 class="modal-title">Sei sicuro?</h4>
          <a type="button" class="close" href="" data-dismiss="modal">&times;</a>
        </div>
        
        <!-- Modal body -->
        <div class="modal-body">
          Stai per iniziare una Prova di Autovalutazione.
        </div>
        
        <!-- Modal footer -->
        <div class="modal-footer">
          <a type="button" class="btn btn-secondary" href="#" data-dismiss="modal">Annulla</a>
          <a class="btn btn-secondary" href="prova_autovalutazione?inizia=true">Inizia</a>
        </div>
        
      </div>
    </div>
  </div>

<!-- FINE CUSTOM ALERT -->

