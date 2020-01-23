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
			else if(pagina.contentEquals("registrati")){
				//operzioni per la pagina di registrazione
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