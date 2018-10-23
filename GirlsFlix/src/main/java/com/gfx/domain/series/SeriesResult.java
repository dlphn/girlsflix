package com.gfx.domain.series;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

public class SeriesResult {
	protected List<Document> documents;
	protected List<Integer> ids;
	
	public SeriesResult() {
	    this.documents = new ArrayList<Document>();
	    this.ids = new ArrayList<Integer>();
	}
	
	public SeriesResult(List<Document> documents, List<Integer> ids) {
	    this.documents = documents;
	    this.ids = ids;
	}

	public List<Document> getDocuments() {
		return documents;
	}

	public void setDocuments(List<Document> documents) {
		this.documents = documents;
	}

	public List<Integer> getIds() {
		return ids;
	}

	public void setSeriesIds(List<Integer> ids) {
		this.ids = ids;
	}
}
