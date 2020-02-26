package persistence;

import java.util.ArrayList;
import java.util.List;

import javafx.util.Pair;
import model.Video;

public interface VideoDAO {
	
	public void save(Video video);  // Create
	public Video findByPrimaryKey(String id);     // Retrieve
	public ArrayList<Video> findAll();       
	public void update(Video video); //Update
	public void delete(String url); //Delete	
	public boolean esisteVideo(String urlNuovo);
	public boolean esisteNome(String nomeNuovo);
	public boolean esisteNomeModifica(String modificaNome, String url);
	public String getRispostaCorretta(String url);
	public int updateVisualizzazioni(String url);
	public int getVideoInseriti();
	public String getLinkVideoCorretto();
	public String getLinkVideoSbagliato();
	public String getNomeVideoCorretto(String linkVideoCorretto);
	public String getNomeVideoSbagliato(String linkVideoSbagliato);
	public ArrayList<String> getVideoCategorie();
	public ArrayList<String> getVideoDifficolta();
	public Video getVideo(String url);
	public ArrayList<Video> getVideoHome();

}
