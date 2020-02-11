package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import persistence.DBManager;

public class GestoreAccount extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if(req.getParameter("elimina") != null)
		{
			DBManager.getInstance().eliminaCategoria(DBManager.getInstance().getUtenteCorrente());
			DBManager.getInstance().eliminaCommenti(DBManager.getInstance().getUtenteCorrente());
			DBManager.getInstance().eliminaEsiti(DBManager.getInstance().getUtenteCorrente());
			DBManager.getInstance().eliminaPreferiti(DBManager.getInstance().getUtenteCorrente());
			DBManager.getInstance().eliminaVoti(DBManager.getInstance().getUtenteCorrente());
			DBManager.getInstance().eliminaAccount(DBManager.getInstance().getUtenteCorrente());
			
			req.getSession().setAttribute("eliminazioneCompletata","true");
			RequestDispatcher rd = req.getRequestDispatcher("index.jsp");
			rd.forward(req, resp);
			
			req.getSession().removeAttribute("eliminazioneCompletata");
		}
		
		else if(req.getParameter("modifiche")!=null)
		{
			String nome = req.getParameter("nome");
			String cognome = req.getParameter("cognome");
			String email = req.getParameter("email");
		
			if(!DBManager.getInstance().getUtenteCorrente().getEmail().equals(email) && DBManager.getInstance().esisteEmail(email))
			{
				resp.getOutputStream().println("erroreEmail");
			}
			else
			{
				DBManager.getInstance().modificaEmailCategoria(DBManager.getInstance().getUtenteCorrente().getEmail(),email);
				DBManager.getInstance().modificaEmailCommenti(DBManager.getInstance().getUtenteCorrente().getEmail(),email);
				DBManager.getInstance().modificaEmailEsiti(DBManager.getInstance().getUtenteCorrente().getEmail(),email);
				DBManager.getInstance().modificaEmailPreferiti(DBManager.getInstance().getUtenteCorrente().getEmail(),email);
				DBManager.getInstance().modificaEmailVoti(DBManager.getInstance().getUtenteCorrente().getEmail(),email);
				DBManager.getInstance().getUtenteDAO().update(DBManager.getInstance().getUtenteCorrente().getEmail(), nome, cognome, email);
				
				DBManager.getInstance().getUtenteCorrente().setNome(nome);
				DBManager.getInstance().getUtenteCorrente().setCognome(cognome);
				DBManager.getInstance().getUtenteCorrente().setEmail(email);
				req.getSession().setAttribute("nome", nome);
				req.getSession().setAttribute("cognome", cognome);
				req.getSession().setAttribute("email", email);
				req.getSession().setAttribute("datiUtente", "Benvenuto/a "+nome+" "+cognome);

				resp.getOutputStream().println("emailOk");
				
			}
		}
		else if(req.getParameter("modificaPassword")!=null)
		{
			String passwordAttuale = req.getParameter("passwordAttuale");
			String nuovaPassword = req.getParameter("nuovaPassword");
			String confermaPassword = req.getParameter("confermaPassword");
			
			if(!DBManager.getInstance().getUtenteCorrente().getPassword().equals(passwordAttuale) || passwordAttuale.equals(nuovaPassword)|| !nuovaPassword.equals(confermaPassword))
			{
				System.out.println(passwordAttuale);
				System.out.println(nuovaPassword);
				System.out.println(confermaPassword);
				resp.getOutputStream().println("errorePassword");
			}
			else
			{
				DBManager.getInstance().modificaPassword(nuovaPassword);
				DBManager.getInstance().getUtenteCorrente().setPassword(nuovaPassword);
				InviaEmail.getInstance().inviaMailNuovaPassword(DBManager.getInstance().getUtenteCorrente().getEmail());
				resp.getOutputStream().println("nuovaPasswordOK");
			}
		}
		else if(req.getParameter("declina")!=null)
		{
			DBManager.getInstance().declinaAmministratore(req.getParameter("declina"));
			InviaEmail.getInstance().inviaMailApprovazioneAmministratore(req.getParameter("declina"), "no");
			RequestDispatcher rd = req.getRequestDispatcher("richiesteAmministratori.jsp");
			rd.forward(req, resp);
		}
		else if(req.getParameter("accettaRichiesta")!=null)
		{
			DBManager.getInstance().declinaAmministratore(req.getParameter("accettaRichiesta"));
			DBManager.getInstance().updateUtenteAmministratore(req.getParameter("accettaRichiesta"));
			InviaEmail.getInstance().inviaMailApprovazioneAmministratore(req.getParameter("accettaRichiesta"), "si");

			RequestDispatcher rd = req.getRequestDispatcher("richiesteAmministratori.jsp");
			rd.forward(req, resp);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	
	}
}
