package com.gfx.domain.series;

import java.time.LocalDate;
import com.gfx.domain.users.Enjoyer;

public class Notification {
	
	private static long count = 0;
	private long idNotif;
	private boolean isRead;
	private Enjoyer enjoyerToNotify;
	private int idSerie;
	private String serieTitle;
	private int nbSeason;
	private int nbEpisode;
	private LocalDate dateOnAir;
	
	public Notification(Enjoyer enjoyerToNotify, int idSerie, String serieTitle, int nbSeason, int nbEpisode, LocalDate dateOnAir) {
		count ++;
		idNotif = count;
		isRead = false;
		this.enjoyerToNotify = enjoyerToNotify;
		this.idSerie = idSerie;
		this.serieTitle = serieTitle;
		this.nbSeason = nbSeason;
		this.nbEpisode = nbEpisode;
		this.dateOnAir = dateOnAir;
	}
	
	/**
	 * This function is called when a notification is read by the enjoyer
	 */
	public void readNotify() {
		this.isRead = true;
	}
	
	public static long getCount() {
		return count;
	}

	public static void setCount(long count) {
		Notification.count = count;
	}

	public long getIdNotif() {
		return idNotif;
	}

	public void setIdNotif(long idNotif) {
		this.idNotif = idNotif;
	}

	public boolean isRead() {
		return isRead;
	}

	public void setRead(boolean isRead) {
		this.isRead = isRead;
	}

	public Enjoyer getEnjoyerToNotify() {
		return enjoyerToNotify;
	}

	public void setEnjoyerToNotify(Enjoyer enjoyerToNotify) {
		this.enjoyerToNotify = enjoyerToNotify;
	}

	

	public int getIdSerie() {
		return idSerie;
	}

	public void setIdSerie(int idSerie) {
		this.idSerie = idSerie;
	}

	public String getSerieTitle() {
		return serieTitle;
	}

	public void setSerieTitle(String serieTitle) {
		this.serieTitle = serieTitle;
	}

	public int getNbSeason() {
		return nbSeason;
	}

	public void setNbSeason(int nbSeason) {
		this.nbSeason = nbSeason;
	}

	public int getNbEpisode() {
		return nbEpisode;
	}

	public void setNbEpisode(int nbEpisode) {
		this.nbEpisode = nbEpisode;
	}

	public LocalDate getDateOnAir() {
		return dateOnAir;
	}

	public void setDateOnAir(LocalDate dateOnAir) {
		this.dateOnAir = dateOnAir;
	}

	

}
