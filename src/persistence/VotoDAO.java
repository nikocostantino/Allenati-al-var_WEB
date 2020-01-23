package persistence;

import java.util.ArrayList;

import model.Utente;

public interface VotoDAO {

	public void setVoto(int i, String email);

	public ArrayList<Integer> getVoti(Utente utenteCorrente2);


}
