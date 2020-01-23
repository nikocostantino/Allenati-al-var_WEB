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
		
		if(voti!=null)
		{
			
			ArrayList<Integer> listaVoti = DBManager.getInstance().dammiVoti(DBManager.getInstance().getUtenteCorrente());
			
			System.out.println("lista voti ="+listaVoti);
			
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
			System.out.println(totale);
			resp.getOutputStream().println(totale);
			
		}
	}

}
