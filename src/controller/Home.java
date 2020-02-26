package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Video;
import persistence.DBManager;

public class Home extends HttpServlet {
		@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			
			ArrayList<Video> video_nel_db = DBManager.getInstance().getVideoHome();
			ArrayList<Video> difficili=new ArrayList<Video>();
			ArrayList<Video> normali=new ArrayList<Video>();
			ArrayList<Video> facili=new ArrayList<Video>();
			for(int i=0; i<video_nel_db.size(); i++)
			{
				if(video_nel_db.get(i).getDifficolta().equals("difficile"))
					difficili.add(video_nel_db.get(i));
				else if(video_nel_db.get(i).getDifficolta().equals("normale"))
					normali.add(video_nel_db.get(i));
				else if(video_nel_db.get(i).getDifficolta().equals("facile"))
					facili.add(video_nel_db.get(i));
			}
			req.getSession().setAttribute("difficili", difficili);
			req.getSession().setAttribute("normali", normali);
			req.getSession().setAttribute("facili", facili);
			/*ArrayList<Video> piu_visti = DBManager.getInstance().getPiuVisti(videoUltimi);
			req.getSession().setAttribute("video_piu_visti",video_nel_db);
			*/
			List<Video> recenti = DBManager.getInstance().getUtenteCorrente().getRecenti();
			req.getSession().setAttribute("video_recenti", recenti);

			RequestDispatcher rd = req.getRequestDispatcher("home.jsp");
			rd.forward(req, resp);
		}
		
		@Override
		protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			doGet(req, resp);
		}
}
