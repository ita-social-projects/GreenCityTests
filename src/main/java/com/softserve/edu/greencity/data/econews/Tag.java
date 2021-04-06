package com.softserve.edu.greencity.data.econews;

public enum Tag {

	NEWS("News"),
	NEWS_RU("События"),
	EVENTS("Events"),
	EVENTS_RU("Инициативы"),
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
