package com.gfx.domain.users;

public abstract class User {
	private String login = null; // [Deprecated][Impossible pour instanciation depuis formulaire]le User ne pourra plus changer son login une fois choisi
	private String pseudo = null;
	private String password = null;
	private String firstName = null;
	private String lastName = null;
	
	public User() {
	}
	
	public User(String login) {
		this.login = login;
	}
	
	public User(String login, String pseudo, String password) {
		super();
		this.login = login;
		this.pseudo = pseudo;
		this.password = password;
	}
	
	public User(String login, String pseudo, String password, String firstName) {
		super();
		this.login = login;
		this.pseudo = pseudo;
		this.password = password;
		this.firstName = firstName;
	}
	
	public User(String login, String pseudo, String password, String firstName, String lastName) {
		super();
		this.login = login;
		this.pseudo = pseudo;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	public void login() {
		
	}
	
	public void logout() {
		
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

	public String getLogin() {
		return login;
	}
	
	public void setLogin(String login) {
		this.login = login;
	}
	
	/*public String toString() {
		String result = "";
		result += "Login : " + this.login + "\n";
		result += "Pseudo : " + this.pseudo + "\n";
		result += "First Name : " + this.firstName + "\n";
		result += "Last Name : " + this.lastName + "\n";
		return result;
	}*/


}
