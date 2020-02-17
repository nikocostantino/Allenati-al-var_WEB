package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Utente;

public class VotoDAO_JDBS implements VotoDAO {

	@Override
	public void setVoto(int i, String email) {		
		Connection connection = null;
		try {
			connection = DBManager.getInstance().getConnection();
			Long id = IdBroker.getId(connection);
			String insert = "insert into voti(id,voto,email) values (?,?,?)";

			PreparedStatement statement = connection.prepareStatement(insert);
			statement.setLong(1, id);
			statement.setLong(2, i);
			statement.setString(3, email);

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
	public ArrayList<Integer> getVoti(String email) {
		Connection connection = null;
		try {
			connection = DBManager.getInstance().getConnection();

			String insert = "select voto from voti where email=?";

			PreparedStatement statement = connection.prepareStatement(insert);
			statement.setString(1, email);

			ResultSet result = statement.executeQuery();
			ArrayList<Integer> a=new ArrayList<Integer>();
			while(result.next()) {
				a.add((int) result.getLong("voto"));
			}
			return a;
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
	public ArrayList<Integer> getUltimiDieci(String email) {
		Connection connection = null;
		try {
			connection = DBManager.getInstance().getConnection();

			String insert = "select voto from voti where email=?";

			PreparedStatement statement = connection.prepareStatement(insert);
			statement.setString(1, email);

			ResultSet result = statement.executeQuery();
			ArrayList<Integer> a=new ArrayList<Integer>();
			while(result.next()) {
				a.add((int) result.getLong("voto"));
			}
			if(a.size()<=10)
				return a;
			else
			{
				ArrayList<Integer> a2=new ArrayList<Integer>();
				for(int i=a.size()-10; i<a.size(); i++)
					a2.add(a.get(i));
				return a2;
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
	}

	@Override
	public void deletePerEmail(String email) {
		Connection connection = null;
		try {
			connection = DBManager.getInstance().getConnection();
			PreparedStatement statement;
			String query = "DELETE FROM voti WHERE email = ?";
			statement = connection.prepareStatement(query);
			statement.setString(1, email);
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} 		
	}

	@Override
	public void modificaEmail(String email, String email2) {
		Connection connection = null;
		try {
			connection = DBManager.getInstance().getConnection();

			String insert = "UPDATE voti SET email=? WHERE email=?";
			PreparedStatement statement = connection.prepareStatement(insert);

			statement.setString(1, email2);
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
	public ArrayList<Boolean> getVoti() {
		Connection connection = null;
		try {
			connection = DBManager.getInstance().getConnection();

			String insert = "select risposta_utente from esiti";

			PreparedStatement statement = connection.prepareStatement(insert);
			ResultSet result = statement.executeQuery();
			ArrayList<Boolean> a=new ArrayList<Boolean>();
			while(result.next()) {
				a.add((boolean) result.getBoolean("risposta_utente"));
			}
			return a;
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

}