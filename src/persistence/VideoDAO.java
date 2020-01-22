package persistence;

import java.util.ArrayList;

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

}
