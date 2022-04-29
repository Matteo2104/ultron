package it.prova.brain.language;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Set;

import it.prova.brain.language.connection.MyConnection;

public class Parole {
	
	
	public static void findAllVerbi() {
		try(Connection conn = MyConnection.getConnection();
		         Statement stmt = conn.createStatement();
		         ResultSet rs = stmt.executeQuery("select * from verbi");) {
		         // Extract data from result set
		         while (rs.next()) {
		            // Retrieve by column name
		            System.out.println(rs.getString("verbo"));
		         }
		      } catch (SQLException e) {
		         e.printStackTrace();
		      } 
	}	
	
	public static void insert(String word) {
		try(Connection conn = MyConnection.getConnection();
			PreparedStatement preparedStatement = conn.prepareStatement("insert into verbi (verbo) values (?)")) {

            preparedStatement.setString(1, word.toLowerCase());

            preparedStatement.executeUpdate();

            // rows affected
            //System.out.println(row); //1
		} catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	
	public static void insertParola(String word) {
		try(Connection conn = MyConnection.getConnection();
			PreparedStatement preparedStatement = conn.prepareStatement("insert into parole (parola) values (?)")) {

            preparedStatement.setString(1, word.toLowerCase());

            preparedStatement.executeUpdate();

            // rows affected
            //System.out.println(row); //1
		} catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	
	public static void insertConiugazione(String word, int codice) {
		try(Connection conn = MyConnection.getConnection();
			PreparedStatement preparedStatement = conn.prepareStatement("insert into coniugazione (coniugazione, id_verbo) values (?, ?);")) {

            preparedStatement.setString(1, word.toLowerCase());
            preparedStatement.setInt(2, codice);

            preparedStatement.executeUpdate();

            // rows affected
            //System.out.println(row); //1
		} catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
		
	public static boolean findByParola(String parola) {
		String parolaTrovata = null;
		try (Connection conn = MyConnection.getConnection();
				PreparedStatement ps = conn.prepareStatement("select verbo from verbi where verbo = ?")) {

			ps.setString(1, parola.toLowerCase());

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				// Retrieve by column name
				parolaTrovata = rs.getString("verbo");
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}	
	
	public static int findCodiceByParola(String parola) {
		int id = 0;
		try (Connection conn = MyConnection.getConnection();
				PreparedStatement ps = conn.prepareStatement("select id from parole where parola = ?")) {

			ps.setString(1, parola.toLowerCase());

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				// Retrieve by column name
				id = rs.getInt("id");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return id;
	}	
	/*
	public static int findCodiceByVerbo(String parola) {
		int id = 0;
		try (Connection conn = MyConnection.getConnection();
				PreparedStatement ps = conn.prepareStatement("select id from verbi where verbo = ?")) {

			ps.setString(1, parola.toLowerCase());

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				// Retrieve by column name
				id = rs.getInt("id");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return id;
	}	
	*/
	
	public static int findCodiceByVerbo(String parola) {
		int id = 0;
		try (Connection conn = MyConnection.getConnection();
				PreparedStatement ps = conn.prepareStatement("select * from coniugazione c inner join verbi v on c.id_verbo = v.id where coniugazione = ?;")) {

			ps.setString(1, parola.toLowerCase());

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				// Retrieve by column name
				id = rs.getInt("id_verbo");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return id;
	}
	
	public static int findVerbo(String parola) {
		int id = 0;
		try (Connection conn = MyConnection.getConnection();
				PreparedStatement ps = conn.prepareStatement("select * from verbi where verbo = ?")) {

			ps.setString(1, parola.toLowerCase());

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				// Retrieve by column name
				id = rs.getInt("id");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return id;
	}
			
			
	public static void insertParole(Set<String> parole) {
		if (parole.size() == 0)
			throw new RuntimeException("Input non valido");

		String statement = "insert into verbi (verbo) values ";

		for (String parola : parole) {
			statement += "('" + parola + "'),";
		}
		if (statement.charAt(statement.length() - 1) == ',') {
			statement = statement.substring(0, statement.length() - 1);
		}
		statement += ";";

		System.out.println(statement);
		
		try (Connection conn = MyConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(statement);) {

			ps.executeUpdate();

			// rows affected
			// System.out.println(row); //1
		} catch (SQLException e) {
			System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
