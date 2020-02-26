package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;

import model.Video;


class IdBroker {

	// Standard SQL (queste stringhe andrebbero scritte in un file di configurazione
	// private static final String query = "SELECT NEXT VALUE FOR SEQ_ID AS id";
	// postgresql
	private static final String query = "SELECT nextval('incrementovoti') AS id";

	public static Long getId(Connection connection) throws SQLException{
		Long id = null;
		PreparedStatement statement = connection.prepareStatement(query);
		ResultSet result = statement.executeQuery();
		result.next();
		id = result.getLong("id");
		return id;
	}
}