package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import persistence.DBManager;

public class GestoreStatistiche extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String voti=req.getParameter("voti");
		String ultimi10Voti=req.getParameter("ultimi10Voti");
		String email = (String) req.getSession().getAttribute("email");
		if(ultimi10Voti!=null)
		{
			ArrayList<Integer> listaVoti10;
			if(email!=null)
			{
				listaVoti10 = DBManager.getInstance().getUltimiDieciVoti(email);
			}
			else
			{
				listaVoti10 = DBManager.getInstance().getUltimiDieciVoti(DBManager.getInstance().getUtenteCorrente().getEmail());
			}
			

			if(listaVoti10.size()<10)
			{
				for(int i=listaVoti10.size(); i<10; i++)
				{
					listaVoti10.add(-1);
				}
			}
			
			String totale="";
			for(int i=0; i<10; i++)
			{
				totale=totale+listaVoti10.get(i)+" ";
			}
			totale=totale+" "+DBManager.getInstance().getUtenteCorrente().getNome()+" "+DBManager.getInstance().getUtenteCorrente().getCognome();
			resp.getOutputStream().println(totale);
		}
		if(voti!=null)
		{
			
			ArrayList<Integer> listaVoti;
			if(email!=null)
			{
				listaVoti = DBManager.getInstance().dammiVoti(email);

			}
			else
			{
				listaVoti = DBManager.getInstance().dammiVoti(DBManager.getInstance().getUtenteCorrente().getEmail());
			}
			
			
			
			
			ArrayList<Integer> votiOrdinati=new ArrayList<Integer>();
			for(int i=0; i<11; i++)
			{
				votiOrdinati.add(0);
			}
			for(int i=0; i<listaVoti.size(); i++)
			{
				votiOrdinati.set(listaVoti.get(i), votiOrdinati.get(listaVoti.get(i))+1);
			}
			String totale="";
			for(int i=0; i<11; i++)
			{
				totale=totale+votiOrdinati.get(i)+" ";
			}
			resp.getOutputStream().println(totale);
			
		}
	}
}

