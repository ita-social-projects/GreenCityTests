package com.softserve.edu.greencity.data.econews;

public enum Tag {

	NEWS("News"),
	EVENTS("Events"),
	EDUCATION("Education"),
	INITIATIVES("Initiatives"),
	ADS("Ads");
	
	private String nameOfTag;
	
	Tag(String nameOfTag) {
		this.nameOfTag = nameOfTag;
	}
	
	@Override
	public String toString() {
		return nameOfTag;
	}

}
