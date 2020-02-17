package persistence;

import java.util.ArrayList;

import model.Utente;

public interface VotoDAO {

	public void setVoto(int i, String email);

	public ArrayList<Integer> getVoti(String email);
	
	public ArrayList<Integer>  getUltimiDieci(String email);

	public void deletePerEmail(String email);

	public void modificaEmail(String email, String email2);

	public ArrayList<Boolean> getVoti();


}
