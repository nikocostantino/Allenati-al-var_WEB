package persistence;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import org.apache.jasper.tagplugins.jstl.core.ForEach;

import javafx.util.Pair;
import model.Categoria;
import model.Commenti;
import model.Esito;
import model.OpzioniRisposte;
import model.Utente;
import model.Video;


public class DBManager {
	private static DBManager instance = null;
	private List<Utente> utenti;
	private ArrayList<Video> video;
	private ArrayList<Video> piu_visti;
	private Utente utenteCorrente;
	
	static {
		try {
			Class.forName("org.postgresql.Driver").newInstance();
			//manca un pezzo
		} 
		catch (Exception e) {
			System.err.println("PostgresDAOFactory.class: failed to load MySQL JDBC driver\n"+e);
			e.printStackTrace();
		}
	}
	
	public Connection getConnection() throws SQLException {
	//	Connection connection = DriverManager.getConnection("jdbc:postgresql://dumbo.db.elephantsql.com:5432/pomdxlpa", "pomdxlpa", "CT9m2BEOOTt2prmx3AVWLQh-vrqVr1ba");
		Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/AllenatiALVAR_WEB", "postgres", "7Ronaldo");
		
		return connection;
	}
	
	
	public static DBManager getInstance() {
		if (instance == null) {
			instance = new DBManager();
		}
		return instance;
	}
	


	private DBManager() {
		utenti = new ArrayList<Utente>();
		
		video = new ArrayList<Video>();
		piu_visti = new ArrayList<Video>();
		
		
	
	}
	
	public void inserisciUtente(Utente u) {
		getUtenteDAO().save(u);
	}

	public Utente login(String email, String password) {
		
		return getUtenteDAO().findByPrimaryKey(email,password);
	}
	
	public void aggiungiVideo(Video v) {
		getVideoDAO().save(v);
	}

	public ArrayList<Video> getVideo() {
		return getVideoDAO().findAll();
	}
	
	public ArrayList<Esito> getStorico(){
		return getEsitoDAO().findByPrimaryKey(utenteCorrente.getEmail());
	}
	
	public ArrayList<Video> getEsito(int id_esito){
		return getEsitoDAO().getEsito(utenteCorrente.getEmail(), id_esito);
	}
	
	public ArrayList<Video> getPreferiti() {
		return getPreferitiDAO().findByPrimaryKey(utenteCorrente.getEmail());
	}
	
	public void aggiungiAiPreferiti(Video video) {
		if(getPreferitiDAO().getVideo(video.getUrl(), utenteCorrente.getEmail())== null)
			getPreferitiDAO().save(video);
		else
			getPreferitiDAO().delete(video);
	}
	
	public Boolean isPreferito(Video video) {
		if(getPreferitiDAO().getVideo(video.getUrl(),utenteCorrente.getEmail()) != null) {
			return true;
		}
		else
			return false;
		
	}
	
	public Commenti getCommenti(String url_video) {
		return getCommentiDAO().findByPrimaryKey(url_video);
	}
	
	public void aggiungiCommento(String commento, String url) {
		//set utf8 alla stringa commento
		try {
			String comm = new String(commento.getBytes(), "UTF-8");
			getCommentiDAO().save(comm, url);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void aggiungiAlloStorico(Esito esito) {
		getEsitoDAO().save(esito);
	}
	
	public List<Utente> getUtenti() {
		return getUtenteDAO().getTuttiUtenti();
	}

	public void eliminaVideo(String url)
	{
		getVideoDAO().delete(url);
	}

	public ArrayList<Video> getPiuVisti(ArrayList<Video> video_nel_db) {
			
		try {
			
			int totaleVisualizzazioni = 0;
			piu_visti.clear();
			
			for (Video video : video_nel_db) {
				totaleVisualizzazioni+= video.getVisualizzazioni();
			}
			int mediaVisualizzazioni = totaleVisualizzazioni / video_nel_db.size();
			
			
			
			for (Video video : video_nel_db) {
				if(video.getVisualizzazioni() > mediaVisualizzazioni) {
					if(!piu_visti.contains(video))
						piu_visti.add(video);
				}
			}
		}
		catch (ArithmeticException e) {
			return piu_visti; //db vuoto
		}
		finally {
			return piu_visti;
		}
		
		
	}

	

	public Utente getUtenteCorrente() {
		return utenteCorrente;
	}

	public void setUtenteCorrente(Utente utenteCorrente) {
		this.utenteCorrente = utenteCorrente;
	}

	
	public VideoDAO getVideoDAO() {
		return new VideoDAO_JDBC();
	}
	public VotoDAO getVotoDAO()
	{
		return new VotoDAO_JDBS();
	}
	public CategoriaDAO getCategoriaDAO() {
		return new CategoriaDAO_JDBC();
	}
	
	public CommentiDAO getCommentiDAO() {
		return new CommentiDAO_JDBC();
	}
	
	public PreferitiDAO getPreferitiDAO() {
		return new PreferitiDAO_JDBC();
	}

	public EsitoDAO getEsitoDAO() {
		return new EsitoDAO_JDBC();
	}

	public UtenteDAO getUtenteDAO() {
		return new UtenteDAO_JDBC();
	}


	public void eliminaVideoEsito(String url) {
		getEsitoDAO().delete(url);
	}
	
	public void eliminaVideoCommento(String url) {
		getCommentiDAO().delete(url);
	}
	
	public void eliminaVideoPreferito(String url) {
		getPreferitiDAO().deleteUrlPreferiti(url);
	}
	
	public void eliminaVideoCategoria(String url) {
		getCategoriaDAO().deleteUrlCategoria(url);
	}


	public void modificaVideo(Video v) {
		try {
			v.setNome(new String(v.getNome().getBytes(), "UTF-8"));
			getVideoDAO().update(v);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public boolean esisteEmail(String email) {
		return getUtenteDAO().cercaPerEmail(email);
	}


	public void setVoto(int i, String email) {
		getVotoDAO().setVoto(i,email);
		
	}


	public ArrayList<Integer> dammiVoti(String email) {
		return getVotoDAO().getVoti(email);
	}


	public String getNomeUtentePerEmail(String destinatario) {
		return getUtenteDAO().getNomePerEmail(destinatario);
	}


	public String getPasswordUtentePerEmail(String destinatario) {
		return getUtenteDAO().getPasswordPerEmail(destinatario);
	}
	public ArrayList<Integer> getUltimiDieciVoti(String email)
	{
		return getVotoDAO().getUltimiDieci(email);
	}


	public void eliminaAccount(Utente utente) {
		getUtenteDAO().delete(utente);
	}


	public void eliminaCategoria(Utente utenteCorrente2) {
		getCategoriaDAO().deletePerEmail(utenteCorrente2.getEmail());
		
	}


	public void eliminaCommenti(Utente utenteCorrente2) {
		getCommentiDAO().deletePerEmail(utenteCorrente2.getEmail());
	}


	public void eliminaEsiti(Utente utenteCorrente2) {
		getEsitoDAO().deletePerEmail(utenteCorrente2.getEmail());
	}


	public void eliminaPreferiti(Utente utenteCorrente2) {
		getPreferitiDAO().deletePerEmail(utenteCorrente2.getEmail());
		
	}


	public void eliminaVoti(Utente utenteCorrente2) {
		getVotoDAO().deletePerEmail(utenteCorrente2.getEmail());	
	}


	public void modificaEmailCategoria(String email, String email2) {
		getCategoriaDAO().modificaEmail(email, email2);
	}


	public void modificaEmailCommenti(String email, String email2) {
		getCommentiDAO().modificaEmail(email, email2);	
	}


	public void modificaEmailEsiti(String email, String email2) {
		getEsitoDAO().modificaEmail(email, email2);
	}


	public void modificaEmailPreferiti(String email, String email2) {
		getPreferitiDAO().modificaEmail(email, email2);
	}


	public void modificaEmailVoti(String email, String email2) {
		getVotoDAO().modificaEmail(email, email2);
	}


	public void modificaPassword(String nuovaPassword) {
		getUtenteDAO().modificaPassword(nuovaPassword);
		
	}


	public void inserisciRichiesta(String email) {
		getUtenteDAO().inserisciRichiesta(email);
	}


	public ArrayList<String> getRichieste() {
		return getUtenteDAO().getRichieste();
	}


	public void declinaAmministratore(String email) {
		getUtenteDAO().declinaAmministratore(email);
	}


	public void updateUtenteAmministratore(String email) {
		getUtenteDAO().accettaAmministratore(email);
	}


	public int getProveEffettuate(String email) {
		return getUtenteDAO().getProveEffettuate(email);
	}


	public int getProveSuperate(String email) {
		return getUtenteDAO().getProveSuperate(email);
	}


	public int getProveNonSuperate(String email) {
		return getUtenteDAO().getProveNonSuperate(email);
	}


	public double getMedia(String email) {
		return getUtenteDAO().getMedia(email);
	}


	public int getVotoPiuFrequente(String email) {
		return getUtenteDAO().getVotoPiuFrequente(email);
	}


	public int getVotoMenoFrequente(String email) {
		return getUtenteDAO().getVotoMenoFrequente(email);
	}


	public int getVideoInseriti() {
		return getVideoDAO().getVideoInseriti();
	}


	public int getProveSuperate() {
		return getUtenteDAO().getProveSuperate();
	}
	
	public int getProveNonSuperate() {
		return getUtenteDAO().getProveNonSuperate();
	}


	public double getVotoMedio() {
		return getUtenteDAO().getVotoMedio();
	}


	public String getLinkVideoCorretto() {
		return getVideoDAO().getLinkVideoCorretto();
	}


	public String getLinkVideoSbagliato() {
		return getVideoDAO().getLinkVideoSbagliato();
	}


	public String getNomeVideoCorretto(String linkVideoCorretto) {
		return getVideoDAO().getNomeVideoCorretto(linkVideoCorretto);
	}


	public String getNomeVideoSbagliato(String linkVideoSbagliato) {
		return getVideoDAO().getNomeVideoSbagliato(linkVideoSbagliato);
	}


	public ArrayList<Boolean> dammiVoti() {
		return getVotoDAO().getVoti();
	}


	public ArrayList<String> dammiVideoCategorie() {
		return getVideoDAO().getVideoCategorie();
	}


	public ArrayList<String> dammiVideoDifficolta() {
		return getVideoDAO().getVideoDifficolta();
	}


	public int getRating(String email) {
		return (int) ((getMedia(email)/2)+0.5);
	}


	public List<Utente> getTopTen() {
		
		List<Utente> tuttiUtenti=DBManager.getInstance().getUtenti();
	  

		List<Utente> tuttiUtentiConMedia = DBManager.getInstance().calcolaMedie(tuttiUtenti);
		return DBManager.getInstance().ordina(tuttiUtentiConMedia);
	}


	private List<Utente> ordina(List<Utente> tuttiUtenti) {
	   
		    for(int i = 0; i < tuttiUtenti.size()-1; i++) {
		        int minimo = i; //Partiamo dall' i-esimo elemento
		        for(int j = i+1; j < tuttiUtenti.size(); j++) {
		            if(tuttiUtenti.get(minimo).getMedia() < tuttiUtenti.get(j).getMedia()) {
		                minimo = j;
		            }
		        }
		        if(minimo!=i) { 
		            Utente k = tuttiUtenti.get(minimo);
		            tuttiUtenti.set(minimo,tuttiUtenti.get(i));
		            tuttiUtenti.set(i,k);
		        }
		    }
		return tuttiUtenti;
	}


	private List<Utente> calcolaMedie(List<Utente> tuttiUtenti) {
		for(int i=0; i<tuttiUtenti.size(); i++)
			tuttiUtenti.get(i).setMedia(DBManager.getInstance().getMedia(tuttiUtenti.get(i).getEmail()));
		return tuttiUtenti;
	}


	public void setNullUtenteCorrente() {
			utenteCorrente=null;
	}

	public Video getVideo(String url) {
		return getVideoDAO().getVideo(url);
	}

	public ArrayList<Video> getVideoHome() {
		return getVideoDAO().getVideoHome();
	}
	


	
	
}
