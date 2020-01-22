package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Categoria;
import model.OpzioniRisposte;
import model.Utente;
import model.Video;

public class UtenteDAO_JDBC implements UtenteDAO{

	@Override
	public void save(Utente utente) {
		Connection connection = null;
		try {
			connection = DBManager.getInstance().getConnection();
			String insert = "insert into utenti(nome, cognome, email, password, amministratore) values (?,?,?,?,?)";
			PreparedStatement statement = connection.prepareStatement(insert);
			statement.setString(1, utente.getNome());
			statement.setString(2, utente.getCognome());
			statement.setString(3, utente.getEmail());
			statement.setString(4, utente.getPassword());
			statement.setBoolean(5, utente.getAmministratore());
			
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new RuntimeException(e.getMessage());
			}
		}
	}

	@Override
	public Utente findByPrimaryKey(String email, String password) {
		Connection connection = null;
		Utente utente = null;
		try {
			connection = DBManager.getInstance().getConnection();
			PreparedStatement statement;
			String query = "select * from utenti where email = ? AND password = ?";
			statement = connection.prepareStatement(query);
			statement.setString(1, email);
			statement.setString(2, password);
			ResultSet result = statement.executeQuery();
			if (result.next()) {
				utente = new Utente();
				utente.setNome(result.getString("nome"));
				utente.setCognome(result.getString("cognome"));
				utente.setEmail(result.getString("email"));
				utente.setPassword(result.getString("password"));
				utente.setAmministratore(result.getBoolean("amministratore"));
			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new RuntimeException(e.getMessage());
			}
		}	
		return utente;
	}

	@Override
	public ArrayList<Utente> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Utente utente) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Utente utente) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public boolean cercaPerEmail(String email) {
		Connection connection = null;
		try {
			connection = DBManager.getInstance().getConnection();
			PreparedStatement statement;
			String query = "select email from utenti where email = ?";
			statement = connection.prepareStatement(query);
			statement.setString(1, email);
			ResultSet result = statement.executeQuery();
			if (result.next()) {
				return true;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new RuntimeException(e.getMessage());
			}
		}	
		return false;
	}

}
