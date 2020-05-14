package com.softserve.edu.greencity.ui.data.econews;

public enum Tag {

	NEWS("News"),
	EVENTS("Events"),
	COURCES("Courses"),
	INITIATIVES("Initiatives"),
	ADS("Ads");
	
	private String nameOfTag;
	
	private Tag(String nameOfTag) {
		this.nameOfTag = nameOfTag;
	}
	
	@Override
	public String toString() {
		return nameOfTag;
	}

}
