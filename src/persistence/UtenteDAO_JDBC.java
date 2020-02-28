package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
		Utente utente=null;
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
		Connection connection = null;
		try {
			connection = DBManager.getInstance().getConnection();
			PreparedStatement statement;
			String query = "DELETE FROM utenti WHERE email = ?";
			statement = connection.prepareStatement(query);
			statement.setString(1, utente.getEmail());
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}	finally {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new RuntimeException(e.getMessage());}
		} 
	}
	
	@Override
	public boolean cercaPerEmail(String email) {
		Connection connection = null;
		boolean b=false;
		try {
			connection = DBManager.getInstance().getConnection();
			PreparedStatement statement;
			String query = "select email from utenti where email = ?";
			statement = connection.prepareStatement(query);
			statement.setString(1, email);
			ResultSet result = statement.executeQuery();
			if (result.next()) {
				b= true;
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
		return b;
	}

	@Override
	public String getNomePerEmail(String email) {
		Connection connection = null;
		Utente utente = null;
		try {
			connection = DBManager.getInstance().getConnection();
			PreparedStatement statement;
			String query = "select * from utenti where email = ?";
			statement = connection.prepareStatement(query);
			statement.setString(1, email);
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
		return utente.getNome()+" "+utente.getCognome();
	}

	@Override
	public String getPasswordPerEmail(String email) {
		Connection connection = null;
		Utente utente = null;
		try {
			connection = DBManager.getInstance().getConnection();
			PreparedStatement statement;
			String query = "select * from utenti where email = ?";
			statement = connection.prepareStatement(query);
			statement.setString(1, email);
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
		return utente.getPassword();
	}

	@Override
	public void update(String emailVecchia, String nome, String cognome, String emailNuova) {
		Connection connection = null;
		try {
			connection = DBManager.getInstance().getConnection();

			String insert = "UPDATE utenti SET nome=?, cognome=?, email=? WHERE email=?";
			PreparedStatement statement = connection.prepareStatement(insert);

			statement.setString(1, nome);
			statement.setString(2, cognome);
			statement.setString(3, emailNuova);
			statement.setString(4, emailVecchia);			
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
	public void modificaPassword(String nuovaPassword) {
		Connection connection = null;
		try {
			connection = DBManager.getInstance().getConnection();

			String insert = "UPDATE utenti SET password=? WHERE email=?";
			PreparedStatement statement = connection.prepareStatement(insert);

			statement.setString(1, nuovaPassword);
			statement.setString(2, DBManager.getInstance().getUtenteCorrente().getEmail());
					
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
	public void inserisciRichiesta(String email) {
		Connection connection = null;
		try {
			connection = DBManager.getInstance().getConnection();
			String insert = "insert into richieste(email) values (?)";
			PreparedStatement statement = connection.prepareStatement(insert);
			statement.setString(1, email);
			
			
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
	public ArrayList<String> getRichieste() {
		Connection connection = null;
		ArrayList<String> lista = new ArrayList<String>();

		try {
			connection = DBManager.getInstance().getConnection();
			String query = "select * from richieste";
			PreparedStatement statement = connection.prepareStatement(query);			
			ResultSet result = statement.executeQuery();
			while(result.next())
			{
				lista.add(result.getString("email"));
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
		return lista;
	}

	@Override
	public void declinaAmministratore(String email) {
		Connection connection = null;
		try {
			connection = DBManager.getInstance().getConnection();
			PreparedStatement statement;
			String query = "DELETE FROM richieste WHERE email = ?";
			statement = connection.prepareStatement(query);
			statement.setString(1, email);
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}	finally {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new RuntimeException(e.getMessage());}
		} 
	}

	@Override
	public void accettaAmministratore(String email) {
		Connection connection = null;
		try {
			connection = DBManager.getInstance().getConnection();

			String insert = "UPDATE utenti SET amministratore=? WHERE email=?";
			PreparedStatement statement = connection.prepareStatement(insert);

			statement.setBoolean(1, true);
			statement.setString(2, email);
					
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
	public int getProveEffettuate(String email) {
		Connection connection = null;
		ArrayList<String> lista = new ArrayList<String>();

		try {
			connection = DBManager.getInstance().getConnection();
			String query = "select * from voti where email=?";
			PreparedStatement statement = connection.prepareStatement(query);	
			statement.setString(1, email);
			ResultSet result = statement.executeQuery();
			while(result.next())
			{
				lista.add(result.getString("id"));
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
		
		return lista.size();		
	}

	@Override
	public int getProveSuperate(String email) {
		Connection connection = null;
		ArrayList<String> lista = new ArrayList<String>();

		try {
			connection = DBManager.getInstance().getConnection();
			String query = "select * from voti where email=?";
			PreparedStatement statement = connection.prepareStatement(query);	
			statement.setString(1, email);
			ResultSet result = statement.executeQuery();
			while(result.next())
			{
				if(result.getInt("voto")>=6)
					lista.add(result.getString("id"));
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
		
		return lista.size();		
	}

	@Override
	public int getProveNonSuperate(String email) {
		Connection connection = null;
		ArrayList<String> lista = new ArrayList<String>();

		try {
			connection = DBManager.getInstance().getConnection();
			String query = "select * from voti where email=?";
			PreparedStatement statement = connection.prepareStatement(query);	
			statement.setString(1, email);
			ResultSet result = statement.executeQuery();
			while(result.next())
			{
				if(result.getInt("voto")<6)
					lista.add(result.getString("id"));
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
		
		return lista.size();		
	}

	@Override
	public double getMedia(String email) {
		Connection connection = null;
		double somma = 0;
		double cont = 0;
		try {
			connection = DBManager.getInstance().getConnection();
			String query = "select * from voti where email=?";
			PreparedStatement statement = connection.prepareStatement(query);	
			statement.setString(1, email);
			ResultSet result = statement.executeQuery();
			while(result.next())
			{
					somma += result.getInt("voto");
					cont++;
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
		if(somma==0)
			return 0;
		double media= somma/cont;
		double temp = Math.pow(10,2);
		return Math.rint(media * temp) / temp;
	}

	@Override
	public int getVotoPiuFrequente(String email) {
		Connection connection = null;
		ArrayList<Integer> lista = new ArrayList<Integer>();

		try {
			connection = DBManager.getInstance().getConnection();
			String query = "select * from voti where email=?";
			PreparedStatement statement = connection.prepareStatement(query);	
			statement.setString(1, email);
			ResultSet result = statement.executeQuery();
			while(result.next())
			{
					lista.add(result.getInt("voto"));
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
		if(lista.size()==0)
		{
			return -1;
		}
		int massimo = 0;
		int cont=0;
		int contMax=0;
		for(int i=0; i<=10; i++)
		{
			cont=0;
			for(int j=0; j<lista.size(); j++)
			{
				if(lista.get(j) == i)
					cont++;
			}
			if(cont>=contMax)
			{
				massimo=i;
				contMax=cont;
			}
			
		}
		return massimo;		
	}

	@Override
	public int getVotoMenoFrequente(String email) {
		Connection connection = null;
		ArrayList<Integer> lista = new ArrayList<Integer>();

		try {
			connection = DBManager.getInstance().getConnection();
			String query = "select * from voti where email=?";
			PreparedStatement statement = connection.prepareStatement(query);	
			statement.setString(1, email);
			ResultSet result = statement.executeQuery();
			while(result.next())
			{
					lista.add(result.getInt("voto"));
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
		if(lista.size()==0)
		{
			return -1;
		}
		int massimo = 0;
		int cont=0;
		int contMax=1000;
		for(int i=0; i<=10; i++)
		{
			cont=0;
			for(int j=0; j<lista.size(); j++)
			{
				if(lista.get(j) == i)
					cont++;
			}
			if(cont<contMax && cont>=1)
			{
				massimo=i;
				contMax=cont;
			}
			
		}
		return massimo;	
	}

	@Override
	public int getProveSuperate() {
		Connection connection = null;
		ArrayList<String> lista = new ArrayList<String>();

		try {
			connection = DBManager.getInstance().getConnection();
			String query = "select * from voti";
			PreparedStatement statement = connection.prepareStatement(query);	
			ResultSet result = statement.executeQuery();
			while(result.next())
			{
				if(result.getInt("voto")>5)
					lista.add(result.getString("email"));
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
		
		return lista.size();		
	}

	@Override
	public int getProveNonSuperate() {
		Connection connection = null;
		ArrayList<String> lista = new ArrayList<String>();

		try {
			connection = DBManager.getInstance().getConnection();
			String query = "select * from voti";
			PreparedStatement statement = connection.prepareStatement(query);	
			ResultSet result = statement.executeQuery();
			while(result.next())
			{
				if(result.getInt("voto")<6)
					lista.add(result.getString("email"));
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
		
		return lista.size();
	}

	@Override
	public double getVotoMedio() {
		Connection connection = null;
		double somma = 0;
		double cont = 0;
		try {
			connection = DBManager.getInstance().getConnection();
			String query = "select * from voti";
			PreparedStatement statement = connection.prepareStatement(query);	
			ResultSet result = statement.executeQuery();
			while(result.next())
			{
					somma += result.getInt("voto");
					cont++;
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
		double media=0;
		if(somma!=0 && cont!=0)
			media= somma/cont;
		double temp = Math.pow(10,2);
		return Math.rint(media * temp) / temp;
	}

	@Override
	public List<Utente> getTuttiUtenti() {
		Connection connection = null;
		List<Utente> utenti = new ArrayList<Utente>();
		try {
			connection = DBManager.getInstance().getConnection();
			PreparedStatement statement;
			String query = "select * from utenti";
			statement = connection.prepareStatement(query);
			
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				Utente utente = new Utente();
				utente.setNome(result.getString("nome"));
				utente.setCognome(result.getString("cognome"));
				utente.setEmail(result.getString("email"));
				utenti.add(utente);
			
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
		return utenti;
	}

}
