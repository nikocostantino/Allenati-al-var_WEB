package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.OpzioniRisposte;
import model.Esito;
import model.Video;
import persistence.DBManager;

public class GestoreProvaAutovalutazione extends HttpServlet {

	private ArrayList<Video> videoProva = new ArrayList<Video>();
	private ArrayList<Video> lista_video_con_risposta_utente = new ArrayList<Video>();
	private Random random = new Random();
	private int risposteErrate;
	private ArrayList<Video> video_nel_db = new ArrayList<Video>();;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher rd = req.getRequestDispatcher("prova_autovalutazione.jsp");

		if (req.getParameter("risposta") == null) {
			lista_video_con_risposta_utente.clear();
			risposteErrate = 0;
			video_nel_db = DBManager.getInstance().getVideo();
			
			if(video_nel_db.size()<10) {
				rd = req.getRequestDispatcher("error_page.html");
				rd.forward(req, resp);
				return;
			}
			
			while (videoProva.size() <= 9) {
				int indice = random.nextInt(video_nel_db.size());
				while (videoProva.contains(video_nel_db.get(indice))) {
					indice = random.nextInt(video_nel_db.size());
				}
				
				videoProva.add(video_nel_db.get(indice));
			}
			req.getSession().setAttribute("videoProva", videoProva);
			req.getSession().setAttribute("dimensione", videoProva.size());
		} else {
			
			if (req.getParameter("risposta").equals(DBManager.getInstance().getVideoDAO().getRispostaCorretta(videoProva.get(0).getUrl())))
				videoProva.get(0).getRisposte().setRispostaUtente(true);
			else
				videoProva.get(0).getRisposte().setRispostaUtente(false);

			lista_video_con_risposta_utente.add(videoProva.get(0));
			
			videoProva.remove(0);

			if (videoProva.isEmpty()) {
				Esito esito = new Esito(lista_video_con_risposta_utente);
				for (Video video : lista_video_con_risposta_utente) {
					if(!video.getRisposte().getRispostaUtente())
						risposteErrate++;
				}
				if(risposteErrate>4)
					esito.setRisultato(false);
				
				DBManager.getInstance().aggiungiAlloStorico(esito);
				rd = req.getRequestDispatcher("esito.jsp");
				req.getSession().setAttribute("esito", lista_video_con_risposta_utente);
			}

		}
		req.getSession().setAttribute("ordineRisposte", new Random().nextInt(2));
		req.getSession().setAttribute("indice", lista_video_con_risposta_utente.size()+1);
		rd.forward(req, resp);
	}

}
