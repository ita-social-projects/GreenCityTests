package com.softserve.edu.greencity.data;

public enum Languages {
	UKRAINIAN("Ua"),
	RUSSIAN("Ru"),
	ENGLISH("En");
	//
	private String name;

	private Languages(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return name;
	}

}
