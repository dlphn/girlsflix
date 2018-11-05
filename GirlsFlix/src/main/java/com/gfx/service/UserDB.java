package com.gfx.service;

import com.gfx.domain.users.Enjoyer;

import com.gfx.domain.users.Gender;
import com.gfx.Keys;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

@Service
public class UserDB {
	private static Connection connect = null;
	private static Statement statement = null;
	private static PreparedStatement preparedStatement = null;
	private static PreparedStatement preparedStatement2 = null;
	
	private static ResultSet resultSet = null;
    
	public static void connect() {
		if (connect == null) {
			try {
				Class.forName("com.mysql.jdbc.Driver");
				String url = "jdbc:mysql://" + Keys.mysqlHost + "/" + Keys.mysqlDb + "?allowPublicKeyRetrieval=true&useSSL=false";
				connect = DriverManager.getConnection(url, Keys.mysqlUser, Keys.mysqlPwd);
			} catch (ClassNotFoundException e1) {
				System.out.println("Connexion non reconnue");
				e1.printStackTrace();
			} catch(SQLException e) {
				System.out.println("Impossible de se connecter à la BDD");
				e.printStackTrace();
			}
		}
	}
	
	private static void writeResultSet(ResultSet resultSet) throws SQLException {
        while (resultSet.next()) {
        	String[] favorites = new String[0];
            String login = resultSet.getString("login");
            String pseudo = resultSet.getString("pseudo");
            String firstname = resultSet.getString("firstname");
            String lastname = resultSet.getString("lastname");
            String gender = resultSet.getString("gender");
            String favoritesStr = resultSet.getString("favorites");
            if (favoritesStr != null) {
            	favorites = resultSet.getString("favorites").split(",");
            }
            System.out.println("login: " + login);
            System.out.println("pseudo: " + pseudo);
            System.out.println("firstname: " + firstname);
            System.out.println("lastname: " + lastname);
            System.out.println("gender: " + gender);
            System.out.println("favorites: " + Arrays.toString(favorites));
        }
    }
	
	private static void close() {
        try {
            if (statement != null) {
                statement.close();
            }
            if (preparedStatement != null) {
            	preparedStatement.close();
            }
            if (resultSet != null) {
            	resultSet.close();
            }
        } catch (Exception e) {
        	e.printStackTrace();
        }
    }
	
	public static void readDatabase() {
		connect();
		String query = "SELECT * FROM users";
		try {
			statement = connect.createStatement();
	        resultSet = statement.executeQuery(query);
	        writeResultSet(resultSet);
		} catch (SQLException e) {
			System.out.println("Impossible de lire la BDD users.");
			e.printStackTrace();
		} finally {
			close();
		}
	}
	
	public static boolean insertOne(Enjoyer newUser){
		// Fonction pour insérer un utilisateur dans la BDD après son inscription
		connect();
		try {
			// login, pseudo, password, enabled, firstname, lastname, gender, favorites, notifications, affinities
			preparedStatement = connect
			        .prepareStatement("INSERT INTO users values (?, ?, ?, 1, ?, ?, ?, null, null, ?)");
			preparedStatement.setString(1, newUser.getLogin());
            preparedStatement.setString(2, newUser.getPseudo());
            preparedStatement.setString(3, newUser.getPassword() != null ? newUser.getPassword() : null);
            preparedStatement.setString(4, newUser.getFirstName() != null ? newUser.getFirstName() : null);
            preparedStatement.setString(5, newUser.getLastName() != null ? newUser.getLastName() : null);
            preparedStatement.setString(6, newUser.getGender() != null ? newUser.getGender().toString() : null);
            preparedStatement.setString(7, newUser.getAffinities() != null ? newUser.getAffinities().toString() : null);
            preparedStatement.executeUpdate();
            preparedStatement2 = connect
 			        .prepareStatement("INSERT INTO user_roles (login, role) VALUES (?, 'ROLE_USER')");
 			preparedStatement2.setString(1, newUser.getLogin());
            preparedStatement2.executeUpdate();
            return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			close();
		}
	}
	
	public static void update(Enjoyer updatedUser) {
		connect();
		try {
			String query = "UPDATE users SET";
			query += " pseudo= '"+ updatedUser.getPseudo()+"'";
			if (updatedUser.getPassword() != null) query += ", password='" + updatedUser.getPassword()+"'" ;
			if (updatedUser.getFirstName() != null) query += ", firstname='" + updatedUser.getFirstName() +"'";
			if (updatedUser.getLastName() != null) query += ", lastname='" + updatedUser.getLastName()+"'" ;
			if (updatedUser.getGender() != null) query += ", gender='" + updatedUser.getGender().toString()+"'" ;
			if (updatedUser.getAffinities() != null) query += ", affinities='" + updatedUser.getAffinities() +"'";
			if (updatedUser.getFavorites() != null) query += ", favorites='" + updatedUser.getFavorites().toString()+"'";
			if (updatedUser.getNotifications() != null) query += ", notifications='" + updatedUser.getNotifications()+"'";
			query += " WHERE login='" + updatedUser.getLogin()+"'";
			System.out.println(query);
			preparedStatement = connect
			        .prepareStatement(query);
            preparedStatement.executeUpdate();
		} catch (SQLException e) {
			System.out.println("la requête n'a pas marché.");
			e.printStackTrace();
		} finally {
			close();
		}
	}
	
	public static Enjoyer getUser(String login) {
		connect();
		String query = "SELECT pseudo, password, firstname, lastname, gender, favorites, notifications, affinities FROM users WHERE login='" + login + "'";
		try {
			statement = connect.createStatement();
	        resultSet = statement.executeQuery(query);
	        if (resultSet.next()) {
	        	List<Integer> favorites = new ArrayList<Integer>();
	        	String[] notifStrList = new String[0];
	        	List<String> notifications = new ArrayList<String>();
	        	String[] affStrList = new String[0];
	        	List<String> affinities = new ArrayList<String>();
	            String pseudo = resultSet.getString("pseudo");
	            String firstname = resultSet.getString("firstname");
	            String lastname = resultSet.getString("lastname");
	            String password = resultSet.getString("password");
	            Gender gender = resultSet.getString("gender") != null ? Gender.valueOf(resultSet.getString("gender")) : Gender.valueOf("OTHER"); 
	            // Processing pour bien récupérer les champs List depuis la BDD String
	            String favoritesDB = resultSet.getString("favorites");
	            String favoritesStr = favoritesDB.replace("[", "").replace("]", "").replace(" ", "");  // Traitement du String pour enlever les [] et espaces
	            if (favoritesStr != null) {
	            	String [] favStrList = favoritesStr.split(",");
	            	for(String fav : favStrList) {
	            		try {// On traite le cas où une exception est levée lors de la conversion en Integer
	            			int favInt = Integer.valueOf(fav);
	            			favorites.add(favInt);
	            		}catch(NumberFormatException e) {
	            			System.out.println("L'utilisateur" + login +" n'a actuellement aucune série favorite ");
	            		}
	            	}	
	            }
	            String notificationsDB = resultSet.getString("notifications");
	            String notifications_modif = notificationsDB.replace("[", "");
	            String notificationsStr = notifications_modif.replace("]", "");
	            if (notificationsStr != null) {
	            	notifStrList = notificationsStr.split(",");
	            	for(String notif : notifStrList) notifications.add(notif);
	            }
	            
	            String affinitiesDB = resultSet.getString("affinities");
	            String affinities_modif = affinitiesDB.replace("[", "");
	            String affinitiesStr = affinities_modif.replace("]", "");
	            if (affinitiesStr != null) {
	            	affStrList = affinitiesStr.split(",");
	            	for(String aff : affStrList) affinities.add(aff);
	            }
	            
	            Enjoyer user = new Enjoyer(login, pseudo, password, firstname, lastname, gender, affinities, favorites, notifications);
	            return user;
	        }
		} catch (SQLException e) {
			System.out.println("Connexion MySQL interrompue");
			e.printStackTrace();
		} finally {
			close();
		}
		return null;
	}
	
	public static Enjoyer checkPwd(String login, String pwd) {
		connect();
		String query = "SELECT pseudo, firstname, lastname, gender, favorites, notifications, affinities FROM users WHERE login='" + login + "' AND password='" + pwd + "'";
		try {
			statement = connect.createStatement();
	        resultSet = statement.executeQuery(query);
	        if (resultSet.next()) {
	        	
	        	List<Integer> favorites = new ArrayList<Integer>();
	        	String[] notifStrList = new String[0];
	        	List<String> notifications = new ArrayList<String>();
	        	String[] affStrList = new String[0];
	        	List<String> affinities = new ArrayList<String>();
	            String pseudo = resultSet.getString("pseudo");
	            String firstname = resultSet.getString("firstname");
	            String lastname = resultSet.getString("lastname");
	            Gender gender = resultSet.getString("gender") != null ? Gender.valueOf(resultSet.getString("gender")) : Gender.valueOf("OTHER"); 
	          
	            String favoritesDB = resultSet.getString("favorites");
	            String favoritesStr = favoritesDB.replace("[", "").replace("]", "").replace(" ", "");  // Traitement du String pour enlever les [] et espaces
	            if (favoritesStr != null) {
	            	String [] favStrList = favoritesStr.split(",");
	            	for(String fav : favStrList) {
	            		try {// On traite le cas où une exception est levée lors de la conversion en Integer
	            			int favInt = Integer.valueOf(fav);
	            			favorites.add(favInt);
	            		}catch(NumberFormatException e) {
	            			System.out.println("L'utilisateur" + login +" n'a actuellement aucune série favorite ");
	            		}
	            	}	
	            }
	            String notificationsDB = resultSet.getString("notifications");
	            String notifications_modif = notificationsDB.replace("[", "");
	            String notificationsStr = notifications_modif.replace("]", "");
	            if (notificationsStr != null) {
	            	notifStrList = notificationsStr.split(",");
	            	for(String notif : notifStrList) notifications.add(notif);
	            }
	            
	            String affinitiesDB = resultSet.getString("affinities");
	            String affinities_modif = affinitiesDB.replace("[", "");
	            String affinitiesStr = affinities_modif.replace("]", "");
	            if (affinitiesStr != null) {
	            	affStrList = affinitiesStr.split(",");
	            	for(String aff : affStrList) affinities.add(aff);
	            }
	            Enjoyer user = new Enjoyer(login, pseudo, pwd, firstname, lastname, gender, affinities, favorites, notifications);
	            return user;
	        }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
		}
		return null;
	}
	
	/**
	 * Check if the login does not already exist in the database.
	 * 
	 * @param login
	 * @return true if the login can be used for an account creation, else false
	 */
	public static Boolean checkLoginNotUsed(String login) {
		connect();
		String query = "SELECT pseudo FROM users WHERE login='" + login + "'";
		try {
			statement = connect.createStatement();
	        resultSet = statement.executeQuery(query);
	        return !resultSet.next();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
		}
		return false;
	}
	
}
