package com.gfx.domain.series;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

/**
 * Used in SerieService to handle results with series, seasons and list of seasons id for different series
 */
public class SeasonResult {
	protected List<Document> seriesDocs;
	protected List<Document> seasonsDocs;
	protected List<SeasonPair> seasons;
	
	public SeasonResult() {
	    this.seriesDocs = new ArrayList<Document>();
	    this.seasonsDocs = new ArrayList<Document>();
	    this.seasons = new ArrayList<SeasonPair>();
	}
	
	public SeasonResult(List<Document> seriesDocs, List<Document> seasonsDocs, List<SeasonPair> season) {
	    this.seriesDocs = seriesDocs;
	    this.seasonsDocs = seasonsDocs;
	    this.seasons = season;
	}
	
	public List<Document> getSeriesDocs() {
		return seriesDocs;
	}

	public void setSeriesDocs(List<Document> seriesDocs) {
		this.seriesDocs = seriesDocs;
	}

	public List<Document> getSeasonsDocs() {
		return seasonsDocs;
	}

	public void setSeasonsDocs(List<Document> seasonsDocs) {
		this.seasonsDocs = seasonsDocs;
	}

	public List<SeasonPair> getSeasons() {
		return seasons;
	}

	public void setSeasons(List<SeasonPair> seasons) {
		this.seasons = seasons;
	}
}
