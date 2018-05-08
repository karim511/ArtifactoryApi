package com.ping.spring.controller;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class RundeckModel {

	private String name;
	private String value;

	@JsonIgnore
	private Date date;

	public RundeckModel(String name, String value, Date date) {
		this.name = name;
		this.value = value;
		this.date = date;
	}

	public RundeckModel() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}
