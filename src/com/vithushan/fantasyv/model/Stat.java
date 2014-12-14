package com.vithushan.fantasyv.model;

import java.util.HashMap;
import java.util.Map;

import org.codehaus.jackson.annotate.JsonAnyGetter;
import org.codehaus.jackson.annotate.JsonAnySetter;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonPropertyOrder;

@JsonPropertyOrder({ "stat" })
public class Stat {

	@JsonProperty("stat")
	private Stat_ stat;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	/**
	 * 
	 * @return The stat
	 */
	@JsonProperty("stat")
	public Stat_ getStat() {
		return stat;
	}

	/**
	 * 
	 * @param stat
	 *            The stat
	 */
	@JsonProperty("stat")
	public void setStat(Stat_ stat) {
		this.stat = stat;
	}

	public Stat withStat(Stat_ stat) {
		this.stat = stat;
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

	public Stat withAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
		return this;
	}

}