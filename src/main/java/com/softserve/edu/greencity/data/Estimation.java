package com.softserve.edu.greencity.data;

public enum Estimation {
	SUPER("super"),
	NORMAL("normal"),
	BAD("bad");
	//
	private String name;

	private Estimation(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return name;
	}

}
