package server;

import javax.swing.JButton;

public class AddToFavoritesButton extends JButton{
	
	private int serieId;
	private String name;
	
	public AddToFavoritesButton(String name, int serieId) {
		super(name);
		this.serieId = serieId;
	}

	public int getSerieId() {
		return serieId;
	}

	public void setSerieId(int serieId) {
		this.serieId = serieId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	

}
