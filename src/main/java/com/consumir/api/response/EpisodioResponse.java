package com.consumir.api.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class EpisodioResponse {

	private String id;
	private String name;
	private String air_date;
	private String episode;
	private List<String>Characters;
	private String url;
}
