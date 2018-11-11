package com.gfx.domain.users;

/**
 * User Object with its Getters and Setters
 * User is the super-class of Enjoyer and Admin
 */
public abstract class User {
	private String login;
	private String pseudo;
	private String password;
	private String firstName;
	private String lastName;
	
	public User() {
		this.login = "";
		this.pseudo = "";
		this.password = "";
		this.firstName = "";
		this.lastName = "";
	}
	
	public User(String login, String pseudo, String password, String firstName, String lastName) {
		super();
		this.login = login;
		this.pseudo = pseudo;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	/**
	 * Getters and Setters
	 */

	public String getLogin() {
		return login;
	}
	
	public void setLogin(String login) {
		this.login = login;
	}

	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

}
