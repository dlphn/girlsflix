package com.gfx.domain.series;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

public class SeasonResult {
	protected List<Document> documents;
	protected List<SeasonPair> season;
	
	public SeasonResult() {
	    this.documents = new ArrayList<Document>();
	    this.season = new ArrayList<SeasonPair>();
	}
	
	public SeasonResult(List<Document> documents, List<SeasonPair> season) {
	    this.documents = documents;
	    this.season = season;
	}

	public List<Document> getDocuments() {
		return documents;
	}

	public void setDocuments(List<Document> documents) {
		this.documents = documents;
	}

	public List<SeasonPair> getSeason() {
		return season;
	}

	public void setSeason(List<SeasonPair> season) {
		this.season = season;
	}
}
