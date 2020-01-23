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
	public ArrayList<Integer> getVoti(Utente utenteCorrente2) {
		Connection connection = null;
		try {
			connection = DBManager.getInstance().getConnection();

			String insert = "select voto from voti where email=?";

			PreparedStatement statement = connection.prepareStatement(insert);
			statement.setString(1, utenteCorrente2.getEmail());

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

}