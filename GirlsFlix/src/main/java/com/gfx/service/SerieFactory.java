package com.gfx.service;

import java.util.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.gfx.domain.series.Serie;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SerieFactory {
	ArrayList<Serie> seriesList =  new ArrayList<Serie>(); // A changer plus tard en HashMap par exemple
	
	public SerieFactory() {
	}
	
	public List<Serie> getSeries(){
		String localPath = new File(".").getAbsolutePath();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		DateTimeFormatter pathFmt = DateTimeFormatter.ofPattern("_yyyy_MM_dd");
        JSONParser parser = new JSONParser();
        try {
        	JSONArray results = (JSONArray) parser.parse(new FileReader(localPath + "/src/main/resources/series"+ pathFmt.format(LocalDateTime.now())+".json"));
        	for (int i = 0; i < results.size(); i++){
                if (results.get(i) instanceof JSONObject){
                	JSONObject jsnObj = (JSONObject)results.get(i);
                	Serie s = new Serie(Integer.parseInt(jsnObj.get("id").toString()), jsnObj.get("title").toString(), LocalDate.parse( (CharSequence) jsnObj.get("date"), formatter ), jsnObj.get("summary").toString());
                	//Serie s = new Serie(jsnObj.get("title").toString(), LocalDate.parse( (CharSequence) jsnObj.get("date"), formatter ) , jsnObj.get("summary").toString());
                	seriesList.add(s);
                	//System.out.println(s.info());
                }
            }
        	//System.out.println(seriesList);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();}
        
		return seriesList;
	}
	
	public List<Serie> getList(){
		return seriesList;
	}
}
