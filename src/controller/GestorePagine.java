package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.mail.MessagingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Esito;
import model.Video;
import persistence.DBManager;

public class GestorePagine extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		String pagina = req.getParameter("pagina");
		if(pagina!=null)
		{
			if(pagina.equals("preferiti")) {
				ArrayList<Video> preferiti = DBManager.getInstance().getPreferiti();
				req.getSession().setAttribute("video_preferiti",preferiti);
				RequestDispatcher rd = req.getRequestDispatcher(pagina+".jsp");
				rd.forward(req, resp);
			}
			else if(pagina.contentEquals("storico")) {
				req.getSession().setAttribute("storico",DBManager.getInstance().getStorico());
				RequestDispatcher rd = req.getRequestDispatcher(pagina+".jsp");
				rd.forward(req, resp);
			}
			else if(pagina.contentEquals("richiesteAmministratori"))
			{
				req.getSession().setAttribute("richiesteAmministratori", DBManager.getInstance().getRichieste());
				System.out.println(DBManager.getInstance().getRichieste());
				RequestDispatcher rd = req.getRequestDispatcher(pagina+".jsp");
				rd.forward(req, resp);
			}
			else if(pagina.contentEquals("profilo"))
			{
				req.getSession().setAttribute("nome", DBManager.getInstance().getUtenteCorrente().getNome());
				req.getSession().setAttribute("cognome", DBManager.getInstance().getUtenteCorrente().getCognome());
				req.getSession().setAttribute("email", DBManager.getInstance().getUtenteCorrente().getEmail());
				req.getSession().setAttribute("password", DBManager.getInstance().getUtenteCorrente().getPassword());
				
					req.getSession().setAttribute("amministratore", DBManager.getInstance().getUtenteCorrente().getAmministratore());
				
					
				if(req.getParameter("salvaModifiche")!=null)
				{
					req.getSession().setAttribute("modificheEffettuate", true);
				}
				if(req.getParameter("salvaPassword")!=null)
				{
					req.getSession().setAttribute("passwordModificata", true);
				}
					
				RequestDispatcher rd = req.getRequestDispatcher(pagina+".jsp");
				rd.forward(req, resp);
				
				if(req.getParameter("salvaModifiche")!=null)
				{
					req.getSession().removeAttribute("modificheEffettuate");
				}
				if(req.getParameter("salvaPassword")!=null)
				{
					req.getSession().removeAttribute("passwordModificata");
				}
			}
			else if(pagina.contentEquals("registrati")){
				//operazioni per la pagina di registrazione
				req.getSession().removeAttribute("nome");
				req.getSession().removeAttribute("cognome");
				req.getSession().removeAttribute("email");
				req.getSession().removeAttribute("password");
				req.getSession().removeAttribute("confPassword");
				req.getSession().removeAttribute("amministratoreSi");
				
				
				req.getSession().removeAttribute("passwordSbagliata");
				req.getSession().removeAttribute("emailSbagliato");
				RequestDispatcher rd = req.getRequestDispatcher(pagina+".jsp");
				rd.forward(req, resp);
				return;
			}
			else if(pagina.equals("statistiche"))
			{
				String email= req.getParameter("email");
				req.getSession().setAttribute("email", email);
				int proveEffettuate;
				int proveSuperate;
				int proveNonSuperate;
				double media;
				int votoPiuFrequente;
				int votoMenoFrequente;
				if(email!=null)
				{
					proveEffettuate = DBManager.getInstance().getProveEffettuate(email);
					proveSuperate = DBManager.getInstance().getProveSuperate(email);
					proveNonSuperate = DBManager.getInstance().getProveNonSuperate(email);
					media = DBManager.getInstance().getMedia(email);
					votoPiuFrequente = DBManager.getInstance().getVotoPiuFrequente(email);
					votoMenoFrequente = DBManager.getInstance().getVotoMenoFrequente(email);

				}
				else
				{
					proveEffettuate = DBManager.getInstance().getProveEffettuate(DBManager.getInstance().getUtenteCorrente().getEmail());
					proveSuperate = DBManager.getInstance().getProveSuperate(DBManager.getInstance().getUtenteCorrente().getEmail());
					proveNonSuperate = DBManager.getInstance().getProveNonSuperate(DBManager.getInstance().getUtenteCorrente().getEmail());
					media = DBManager.getInstance().getMedia(DBManager.getInstance().getUtenteCorrente().getEmail());
					votoPiuFrequente = DBManager.getInstance().getVotoPiuFrequente(DBManager.getInstance().getUtenteCorrente().getEmail());
					votoMenoFrequente = DBManager.getInstance().getVotoMenoFrequente(DBManager.getInstance().getUtenteCorrente().getEmail());
				}
				req.getSession().setAttribute("proveEffettuate", proveEffettuate);
				req.getSession().setAttribute("proveSuperate", proveSuperate);
				req.getSession().setAttribute("proveNonSuperate", proveNonSuperate);
				req.getSession().setAttribute("media", media);
				if(votoPiuFrequente==-1)
					req.getSession().setAttribute("votoPiuFrequente", "-");
				else
					req.getSession().setAttribute("votoPiuFrequente", votoPiuFrequente);
				
				if(votoMenoFrequente==-1)
					req.getSession().setAttribute("votoMenoFrequente", "-");
				else
					req.getSession().setAttribute("votoMenoFrequente", votoMenoFrequente);


				RequestDispatcher rd = req.getRequestDispatcher(pagina+".jsp");
				rd.forward(req, resp);
			}
			else if(pagina.equals("modificaVideo"))
			{
				req.getSession().setAttribute("url", req.getParameter("url"));
				RequestDispatcher rd = req.getRequestDispatcher(pagina+".jsp");
				rd.forward(req, resp);
			}
			else if(pagina.equals("recupera"))
			{
				String email=req.getParameter("email");
				if(DBManager.getInstance().esisteEmail(email))
				{
					
					InviaEmail.getInstance().inviaMail(email);
					
					
					req.getSession().setAttribute("recuperoPasswordEffettuato", "true");
					RequestDispatcher rd = req.getRequestDispatcher("index.jsp");
					rd.forward(req,resp);
					req.getSession().removeAttribute("recuperoPasswordEffettuato");
				}
				else
				{
					req.getSession().setAttribute("emailNonPresente", "true");
					RequestDispatcher rd = req.getRequestDispatcher("recuperoPassword.jsp");
					rd.forward(req,resp);
					req.getSession().removeAttribute("emailNonPresente");
				}
			}
			else
			{
				RequestDispatcher rd = req.getRequestDispatcher(pagina+".jsp");
				rd.forward(req, resp);
			}
		}
		
		
		
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

}