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

import org.springframework.stereotype.Service;

/**
 * Methods to interact with the MySQL database that stores users
 */
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
				Class.forName("com.mysql.cj.jdbc.Driver");
				String url = "jdbc:mysql://" + Keys.mysqlHost + "/" + Keys.mysqlDb;
				url += "?allowPublicKeyRetrieval=true&useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
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
	
	private static void close() {
        try {
            if (statement != null) {
                statement.close();
            }
            if (preparedStatement != null) {
            	preparedStatement.close();
            }
            if (preparedStatement2 != null) {
            	preparedStatement2.close();
            }
            if (resultSet != null) {
            	resultSet.close();
            }
        } catch (Exception e) {
        	e.printStackTrace();
        }
    }
	
	/**
	 * Insert user into database after registration
	 * 
	 * @param newUser
	 * @return	true if the insert was successful, false if there was an issue
	 */
	public static boolean insertOne(Enjoyer newUser){
		connect();
		try {
			// login, pseudo, password, enabled, firstname, lastname, gender, favorites, notifications, affinities
			preparedStatement = connect
			        .prepareStatement("INSERT INTO users values (?, ?, ?, 1, ?, ?, ?, '[]', '[]', ?)");
			preparedStatement.setString(1, newUser.getLogin());
            preparedStatement.setString(2, newUser.getPseudo());
            preparedStatement.setString(3, newUser.getPassword());
            preparedStatement.setString(4, newUser.getFirstName() != null ? newUser.getFirstName() : "");
            preparedStatement.setString(5, newUser.getLastName() != null ? newUser.getLastName() : "");
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
	
	public static Boolean update(Enjoyer updatedUser) {
		connect();
		try {
			String query = "UPDATE users SET";
			//On utilise des \ au lieu de ' pour éviter les cas comme Grey's Anatomy
			query += " pseudo=\"" + updatedUser.getPseudo() + "\"";
			if (updatedUser.getPassword() != null) query += ", password=\"" + updatedUser.getPassword() + "\"";
			if (updatedUser.getFirstName() != null) query += ", firstname=\"" + updatedUser.getFirstName() + "\"";
			if (updatedUser.getLastName() != null) query += ", lastname=\"" + updatedUser.getLastName() + "\"";
			if (updatedUser.getGender() != null) query += ", gender=\"" + updatedUser.getGender().toString() + "\"";
			if (updatedUser.getAffinities() != null) query += ", affinities=\"" + updatedUser.getAffinities() + "\"";
			if (updatedUser.getFavorites() != null) query += ", favorites=\"" + updatedUser.getFavorites().toString() + "\"";
			if (updatedUser.getNotifications() != null) query += ", notifications=\"" + updatedUser.getNotifications() + "\"";
			query += " WHERE login=\"" + updatedUser.getLogin() + "\"";
			preparedStatement = connect
			        .prepareStatement(query);
            preparedStatement.executeUpdate();
            return true;
		} catch (SQLException e) {
			System.out.println("La requête n'a pas marché.");
			e.printStackTrace();
			return false;
		} finally {
			close();
		}
	}
	
	/**
	 * Retrieve user ingo from login and return the Enjoyer object
	 * 
	 * @param login
	 * @return	the Enjoyer object for the user
	 */
	public static Enjoyer getUser(String login) {
		connect();
		String query = "SELECT pseudo, password, firstname, lastname, gender, favorites, notifications, affinities, enabled FROM users WHERE login='" + login + "'";
		try {
			statement = connect.createStatement();
	        resultSet = statement.executeQuery(query);
	        if (resultSet.next()) { //we found a user
	            String pseudo = resultSet.getString("pseudo");
	            String firstname = resultSet.getString("firstname");
	            String lastname = resultSet.getString("lastname");
	            String password = resultSet.getString("password");
	            Gender gender = resultSet.getString("gender") != null ? Gender.valueOf(resultSet.getString("gender")) : Gender.valueOf("OTHER");
	            
	            // Lists are stored as String in db so we need to process it
	        	List<Integer> favorites = new ArrayList<Integer>();
	        	List<String> notifications = new ArrayList<String>();
	        	List<String> affinities = new ArrayList<String>();
	        	
	            String favoritesDB = resultSet.getString("favorites");
	            // Remove [] and white spaces
	            String favoritesStr = favoritesDB.replace("[", "").replace("]", "").replaceAll(" ", "");
	            if (favoritesStr != null && favoritesStr.length() > 0) {
	            	String[] favStrList = favoritesStr.split(",");
	            	for (String fav : favStrList) {
            			int favInt = Integer.valueOf(fav);
            			favorites.add(favInt);
	            	}	
	            }
	            
	            String notificationsDB = resultSet.getString("notifications");
	            String notificationsStr = notificationsDB.replace("[", "").replace("]", "");
	            if (notificationsStr != null && notificationsStr.length() > 0) {
	            	String[] notifStrList = notificationsStr.split(",");
	            	for (String notif : notifStrList) {
	            		notifications.add(notif.trim());
	            	}
	            }
	            
	            String affinitiesDB = resultSet.getString("affinities");
	            String affinitiesStr = affinitiesDB.replace("[", "").replace("]", "");
	            if (affinitiesStr != null && affinitiesStr.length() > 0) {
	            	String[] affStrList = affinitiesStr.split(",");
	            	for (String aff : affStrList) {
	            		affinities.add(aff.trim());
	            	}
	            }
	            
	            Boolean enabled = resultSet.getInt("enabled") == 1 ? true : false;
	            
	            Enjoyer user = new Enjoyer(login, pseudo, password, firstname, lastname, gender, affinities, favorites, notifications);
	            user.setEnabled(enabled);
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
