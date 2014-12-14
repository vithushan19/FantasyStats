package com.vithushan.fantasyv.model;

import java.util.HashMap;
import java.util.Map;

import org.codehaus.jackson.annotate.JsonAnyGetter;
import org.codehaus.jackson.annotate.JsonAnySetter;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;

public class Stat_ {

	@JsonProperty("stat_id")
	private String statId;
	@JsonProperty("value")
	private String value;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	/**
	 * 
	 * @return The statId
	 */
	@JsonProperty("stat_id")
	public String getStatId() {
		return statId;
	}

	/**
	 * 
	 * @param statId
	 *            The stat_id
	 */
	@JsonProperty("stat_id")
	public void setStatId(String statId) {
		this.statId = statId;
	}

	public Stat_ withStatId(String statId) {
		this.statId = statId;
		return this;
	}

	/**
	 * 
	 * @return The value
	 */
	@JsonProperty("value")
	public String getValue() {
		return value;
	}

	/**
	 * 
	 * @param value
	 *            The value
	 */
	@JsonProperty("value")
	public void setValue(String value) {
		this.value = value;
	}

	public Stat_ withValue(String value) {
		this.value = value;
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

	public Stat_ withAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
		return this;
	}

}