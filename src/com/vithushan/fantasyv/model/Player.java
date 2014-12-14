package com.vithushan.fantasyv.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.annotate.JsonAnyGetter;
import org.codehaus.jackson.annotate.JsonAnySetter;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;

public class Player {

	@JsonProperty("name")
	private String name;
	@JsonProperty("stats")
	private List<Stat> stats = new ArrayList<Stat>();
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	/**
	 * 
	 * @return The name
	 */
	@JsonProperty("name")
	public String getName() {
		return name;
	}

	/**
	 * 
	 * @param name
	 *            The name
	 */
	@JsonProperty("name")
	public void setName(String name) {
		this.name = name;
	}

	public Player withName(String name) {
		this.name = name;
		return this;
	}

	/**
	 * 
	 * @return The stats
	 */
	@JsonProperty("stats")
	public List<Stat> getStats() {
		return stats;
	}

	/**
	 * 
	 * @param stats
	 *            The stats
	 */
	@JsonProperty("stats")
	public void setStats(List<Stat> stats) {
		this.stats = stats;
	}

	public Player withStats(List<Stat> stats) {
		this.stats = stats;
		return this;
	}

	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

	public Player withAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
		return this;
	}

}