package com.gfx.domain.users;

import java.util.ArrayList;
import java.util.List;

import com.gfx.domain.series.TypeSerie;

public abstract class User {
	private String login; // [Deprecated][Impossible pour instanciation depuis formulaire]le User ne pourra plus changer son login une fois choisi
	private String pseudo;
	private String password;
	private String firstName;
	private String lastName;
	private Gender gender;
	private List<TypeSerie> affinities = new ArrayList<TypeSerie>();
	
	public User() {
	}
	
	public User(String login, String pseudo, String password, String firstName, String lastName, Gender gender) {
		super();
		this.login = login;
		this.pseudo = pseudo;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
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

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public List<TypeSerie> getAffinities() {
		return affinities;
	}

	public void setAffinities(List<TypeSerie> affinities) {
		this.affinities = affinities;
	}

	public String getLogin() {
		return login;
	}
	
	public void setLogin(String login) {
		this.login = login;
	}


}
