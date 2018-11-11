package com.gfx.domain.series;

import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Component;

/**
 * Genre Object with its Getters and Setters
 * We have Static values of the different genres.
 */
@Component
public class Genre {
	private static List<JSONObject> genres;

	public static List<JSONObject> getGenres() {
		return genres;
	}

	public static void setGenres(List<JSONObject> genres) {
		Genre.genres = genres;
	}
}
