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
		Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/AllenatiALVAR_WEB", "postgres", "Simone_10");
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
		return utenti;
	}

	public void eliminaVideo(String url)
	{
		getVideoDAO().delete(url);
	}

	public ArrayList<Video> getPiuVisti() {
		
		try {
			
			int totaleVisualizzazioni = 0;
			piu_visti.clear();
			ArrayList<Video> video_nel_db = getVideo();
			
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
		getVideoDAO().update(v);
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


	
	
}
