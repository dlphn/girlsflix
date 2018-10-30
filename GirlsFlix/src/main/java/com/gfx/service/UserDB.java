package com.gfx.service;

import com.gfx.domain.series.TypeSerie;
import com.gfx.domain.users.Enjoyer;

import com.gfx.domain.users.Gender;
import com.gfx.domain.users.User;
import com.gfx.helper.Keys;

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
	
	private static ResultSet resultSet = null;
    
	public static void connect() {
		Keys keys = new Keys();
		
		String url = "jdbc:mysql://" + keys.getMysqlHost() + "/" + keys.getMysqlDb() + "?allowPublicKeyRetrieval=true&useSSL=false";
		if (connect == null) {
			try {
				connect = DriverManager.getConnection(url, keys.getMysqlUser(), keys.getMysqlPwd());
			} catch(SQLException e) {
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

        }
    }
	
	public static void readDatabase() {
		String query = "SELECT * FROM users";
		try {
			statement = connect.createStatement();
	        resultSet = statement.executeQuery(query);
	        writeResultSet(resultSet);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
		}
	}
	
	public static void insertOne(User newUser) {
		try {
			preparedStatement = connect
			        .prepareStatement("INSERT INTO users values (?, ?, ?, ?, ?, ?, null)");
			preparedStatement.setString(1, newUser.getLogin());
            preparedStatement.setString(2, newUser.getPseudo());
            preparedStatement.setString(3, newUser.getPassword() != null ? newUser.getPassword() : null);
            preparedStatement.setString(4, newUser.getFirstName() != null ? newUser.getFirstName() : null);
            preparedStatement.setString(5, newUser.getLastName() != null ? newUser.getLastName() : null);
            preparedStatement.setString(6, newUser.getGender() != null ? newUser.getGender().toString() : null);
            //preparedStatement.setString(7, newUser.getAffinities().toString());
            preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
	}
	
	public static void update(User updatedUser) {
		try {
			String query = "UPDATE users SET";
			query += " pseudo='" + updatedUser.getPseudo() + "'";
			if (updatedUser.getFirstName() != null) query += ", firstname='" + updatedUser.getFirstName() + "'";
			if (updatedUser.getLastName() != null) query += ", lastname='" + updatedUser.getLastName() + "'";
			if (updatedUser.getGender() != null) query += ", gender='" + updatedUser.getGender().toString() + "'";
			query += " WHERE login='" + updatedUser.getLogin() + "'";
			System.out.println(query);
			preparedStatement = connect
			        .prepareStatement(query);
            preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
		}
	}

	public static void updateFav(String login, List<TypeSerie> favorites) {
		String favStr = favorites.stream()
      		  .map(String::valueOf)
      		  .collect(Collectors.joining(", "));
		try {
			preparedStatement = connect
			        .prepareStatement("UPDATE users SET favorites=? WHERE login=?");
            preparedStatement.setString(1, favStr);
            preparedStatement.setString(2, login);
            preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
		}
	}
	
	public static void updatePwd(String login, String newPwd) {
		try {
			preparedStatement = connect
			        .prepareStatement("UPDATE users SET password=? WHERE login=?");
            preparedStatement.setString(1, newPwd);
            preparedStatement.setString(2, login);
            preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
		}
	}
	
	public static User checkPwd(String login, String pwd) {
		String query = "SELECT pseudo, firstname, lastname, gender, favorites FROM users WHERE login='" + login + "' AND password='" + pwd + "'";
		try {
			statement = connect.createStatement();
	        resultSet = statement.executeQuery(query);
	        if (resultSet.next()) {
	        	String[] strList = new String[0];
	        	List<Integer> favorites = new ArrayList<Integer>();
	            String pseudo = resultSet.getString("pseudo");
	            String firstname = resultSet.getString("firstname");
	            String lastname = resultSet.getString("lastname");
	            Gender gender = Gender.valueOf(resultSet.getString("gender")); 
	            String favoritesStr = resultSet.getString("favorites");
	            if (favoritesStr != null) {
	            	strList = resultSet.getString("favorites").split(",");
	            	for(String s : strList) favorites.add(Integer.valueOf(s));
	            }
	            User user = new Enjoyer(login, pseudo, pwd, firstname, lastname, gender, favorites);
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
	 * Checks if the login does not already exist in the database.
	 * 
	 * @param login
	 * @return true if the login can be used for an account creation, else false
	 */
	public static Boolean checkLoginNotUsed(String login) {
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
