package com.gfx.domain.users;

import com.gfx.domain.series.Data;
import com.gfx.domain.series.Serie;

public class Admin extends User{
	
	public Admin(String login, String pseudo, String password, String firstName, String lastName) {
		super(login, pseudo, password, firstName, lastName);
	}
	
	public void disableEnjoyer(Enjoyer e) {
		e.setEnabled(false);
	}
	public void enabledEnjoyer(Enjoyer e) {
		e.setEnabled(true);
	}
	
	public void disableSerie(int id) {
		Serie s = Data.getById(id);
		s.setAllowed(false);
	}
	
	public void allowSerie(int id) {
		Serie s = Data.getById(id);
		s.setAllowed(true);
	}

}
