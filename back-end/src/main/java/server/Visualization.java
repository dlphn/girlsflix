package server;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Visualization extends JFrame{
	protected List<Serie> listSeries;
	private Enjoyer enjoyerLogged;
	private JPanel container = new JPanel();
	private JPanel centerContainer = new JPanel();
	private JPanel topContainer = new JPanel();
	private JButton displayFavoritesButton = new JButton("Afficher Favoris");
	
	public Visualization(List<Serie> list) {
		this.listSeries = list;
	}
	public Visualization() {}
	
	public Visualization(Enjoyer enjoyer) {
		enjoyerLogged = enjoyer;
	}
	
	public void showSeries() {
		this.setTitle("Girlflix - Bienvenue " + enjoyerLogged.getPseudo());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setSize(1000, 1000);
		centerContainer.setLayout(new GridLayout(5,5));
		displayFavoritesButton.addActionListener(new DisplayFavoritesListener());
		topContainer.add(displayFavoritesButton);
		container.setLayout(new BorderLayout());
		container.add(topContainer, BorderLayout.NORTH);		
		
		try {
			for (Serie s:listSeries) {
				JPanel blocSerie = new JPanel();
				blocSerie.setBorder(BorderFactory.createLineBorder(Color.BLACK));
				blocSerie.setLayout(new GridLayout(3,1));
				
				JLabel etiquetteSerie = new JLabel("Nom de la série");
				JLabel etiquetteIdSerie = new JLabel("Id de la série");
				etiquetteSerie.setText(s.getTitle());
				etiquetteIdSerie.setText(Integer.toString(s.getId()));
				blocSerie.add(etiquetteSerie);
				blocSerie.add(etiquetteIdSerie);
				
				AddToFavoritesButton addToFavorite = new AddToFavoritesButton("Ajouter aux favoris", s.getId());
				addToFavorite.addActionListener(new AddToFavoriteListener());
				blocSerie.add(addToFavorite);
				centerContainer.add(blocSerie);
				
				
			} 
		} catch (NullPointerException e) {
			System.out.println("size is :   No Series for the moment.");}
		
		container.add(centerContainer, BorderLayout.CENTER);
		this.setContentPane(container);
		this.setVisible(true);
	}
	
	public List<Serie> getListSeries() {
		return listSeries;
	}
	
	public void setListSeries(List<Serie> listSeries) {
		this.listSeries = listSeries;
	}

	
	public void update(SerieFactory f) {
		this.listSeries = f.getList();
	}


	public Enjoyer getEnjoyerLogged() {
		return enjoyerLogged;
	}
	public void setEnjoyerLogged(Enjoyer enjoyerLogged) {
		this.enjoyerLogged = enjoyerLogged;
	}


	public class AddToFavoriteListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			AddToFavoritesButton atfb = (AddToFavoritesButton) e.getSource();
			enjoyerLogged.addToFavorites(atfb.getSerieId());
		}
	}
	
	public class DisplayFavoritesListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			Iterator it1 = enjoyerLogged.getFavoriteSeriesId().iterator();
			System.out.println("Liste des favoris de "  + enjoyerLogged.getPseudo() + " : \n");
			while (it1.hasNext()) {
				System.out.println(it1.next() + "\n");
			}
			System.out.println("****************");
		}
	}
	
	
}
