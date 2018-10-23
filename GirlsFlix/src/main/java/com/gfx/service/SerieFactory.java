package com.gfx.service;

import java.util.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

import com.gfx.domain.series.Serie;

import org.bson.Document;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class SerieFactory {
	ArrayList<Serie> seriesList =  new ArrayList<Serie>(); // A changer plus tard en HashMap par exemple
	
	public SerieFactory() {
	}
	
	public List<Serie> getSeries(){
        JSONParser parser = new JSONParser();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        SerieDB.connect();
        List<Document> documents = SerieDB.find("series");
        try {
        	for(Document doc: documents) {
        			JSONObject jsnObj = (JSONObject) parser.parse(doc.toJson());
        			Serie s = new Serie(Integer.parseInt(((JSONObject) jsnObj.get("id")).get("$numberLong").toString()), jsnObj.get("title").toString(), LocalDate.parse( (CharSequence) jsnObj.get("date"), formatter ), jsnObj.get("summary").toString());
        			seriesList.add(s);
                	//System.out.println(s.info());
                }
        	//System.out.println(seriesList);
		} catch (ParseException e) {
			e.printStackTrace();
		} 
		return seriesList;
	}
	
	public List<Serie> getList(){
		return seriesList;
	}
}