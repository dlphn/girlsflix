package com.gfx.service;

import com.gfx.domain.users.User;
import com.gfx.helper.Keys;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class UserDB {
	private static Connection connect = null;
	private static Statement statement = null;
	private static PreparedStatement preparedStatement = null;
	private static ResultSet resultSet = null;
    
	public static void connect() {
		Keys keys = new Keys();
		String url = "jdbc:mysql://" + keys.getMysqlHost() + "/" + keys.getMysqlDb() + "?useSSL=false";
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
			        .prepareStatement("INSERT INTO users values (?, ?, ?, ? , ?, ?)");
			preparedStatement.setString(1, newUser.getLogin());
            preparedStatement.setString(2, newUser.getPseudo());
            preparedStatement.setString(3, newUser.getPassword());
            preparedStatement.setString(4, newUser.getFirstName());
            preparedStatement.setString(5, newUser.getLastName());
            preparedStatement.setString(6, newUser.getGender().toString());
            preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
		}
	}
	
	public static void update(User updatedUser) {
		try {
			preparedStatement = connect
			        .prepareStatement("UPDATE users SET pseudo=?, firstname=?, lastname=?, gender=? WHERE login=?");
			preparedStatement.setString(1, updatedUser.getPseudo());
            preparedStatement.setString(2, updatedUser.getFirstName());
            preparedStatement.setString(3, updatedUser.getLastName());
            preparedStatement.setString(4, updatedUser.getGender().toString());
            preparedStatement.setString(5, updatedUser.getLogin());
            preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
		}
	}

	public static void updateFav(String login, List<Integer> favorites) {
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
	
	public static Boolean checkPwd(String login, String pwd) {
		String query = "SELECT password FROM users WHERE login='" + login + "' AND password='" + pwd + "'";
		try {
			statement = connect.createStatement();
	        resultSet = statement.executeQuery(query);
	        return resultSet.next();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
		}
		return false;
	}
	
}
