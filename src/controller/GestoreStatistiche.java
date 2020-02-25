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
		String votiTotali=req.getParameter("votiTotali");
		String videoCategorie=req.getParameter("videoCategorie");
		String videoDifficolta=req.getParameter("videoDifficolta");

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
			
			
			System.out.println("lista voti e "+listaVoti);
			
			ArrayList<Integer> votiOrdinati=new ArrayList<Integer>();
			for(int i=0; i<11; i++)
			{
				votiOrdinati.add(0);
			}
			for(int i=0; i<listaVoti.size(); i++)
			{
				if(listaVoti.get(i)>=0)
					votiOrdinati.set(listaVoti.get(i), votiOrdinati.get(listaVoti.get(i))+1);
			}
			String totale="";
			for(int i=0; i<11; i++)
			{
				totale=totale+votiOrdinati.get(i)+" ";
			}
			resp.getOutputStream().println(totale);
			
		}
		if(votiTotali!=null)
		{
			
			System.out.println("sto facendo get voti");
			
			ArrayList<Boolean> listaVoti = DBManager.getInstance().dammiVoti();
			int verde = 0;
			int rosso = 0;
			
			
						
			String totale="";
			for(int i=0; i<listaVoti.size(); i++)
			{
				if(listaVoti.get(i)==true)
					verde++;
				else
					rosso++;
			}
			if(verde*100!=0)
				verde = (verde*100)/listaVoti.size();
			else verde=0;
			rosso = 100-verde;
			totale = totale + verde + " " + rosso + " ";
			resp.getOutputStream().println(totale);
			
		}
		if(videoCategorie!=null)
		{
			ArrayList<String> listaCategorie = DBManager.getInstance().dammiVideoCategorie();
			int rigore = 0;
			int rosso = 0;
			int giallo = 0;
			int fuorigioco = 0;
			int fallo_di_mano = 0;
			int goal = 0;
			
			String totale="";
			for(int i=0; i<listaCategorie.size(); i++)
			{
				if(listaCategorie.get(i).equals("rigore"))
					rigore++;
				else if(listaCategorie.get(i).equals("rosso"))
					rosso++;
				else if(listaCategorie.get(i).equals("giallo"))
					giallo++;
				else if(listaCategorie.get(i).equals("fuorigioco"))
					fuorigioco++;
				else if(listaCategorie.get(i).equals("fallo di mano"))
					fallo_di_mano++;
				else if(listaCategorie.get(i).equals("goal"))
					goal++;
				
			}
			totale = totale + rigore + " " + rosso + " " + giallo + " " + fuorigioco + " " + fallo_di_mano + " " + goal + " ";

			resp.getOutputStream().println(totale);
		}
		if(videoDifficolta!=null)
		{
			ArrayList<String> listaDifficolta = DBManager.getInstance().dammiVideoDifficolta();
			int normale = 0;
			int difficile = 0;
			int facile = 0;
			
			
			String totale="";
			for(int i=0; i<listaDifficolta.size(); i++)
			{
				if(listaDifficolta.get(i).equals("normale"))
					normale++;
				else if(listaDifficolta.get(i).equals("difficile"))
					difficile++;
				else if(listaDifficolta.get(i).equals("facile"))
					facile++;
				
			}
			totale = totale + normale + " " + difficile + " " + facile + " ";

			resp.getOutputStream().println(totale);
		}
	}
}

