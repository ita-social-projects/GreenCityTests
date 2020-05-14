package com.softserve.edu.greencity.ui.data.econews;

import java.util.*;

/**
 * NewsData class for creating and searching News
 */
public class NewsData {

    private String title;
    private List<Tag> tags;
    private String source;
    private String content;
    private String filePath;

    /**
     * Constructor.
     * @param tags
     * @param title
     * @param content
     */
    public NewsData(List<Tag> tags, String title, String content) {
        this.title = title;
        this.tags = tags;
        this.content = content;
        this.source = "";
        this.filePath = "";
    }

    /**
     * Constructor.
     * @param tags
     * @param title
     * @param content
     * @param filePath
     * @param source
     */
    public NewsData(String title, List<Tag> tags, String source, String content, String filePath) {
        this.title = title;
        this.tags = tags;
        this.source = source;
        this.content = content;
        this.filePath = filePath;
    }

    public String getFilePath() {
        return filePath;
    }
    
    public String getTitle() {
        return title;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public String getSource() {
        return source;
    }

    public String getContent() {
        return content;
    }

    /**
     * Method to convert list of Tags to sorted list of Strings
     * @return List<String>
     */
    public List<String> getTagsName() {
    	List<String> list = new ArrayList<>();
    	for(Tag current : getTags()) {
    		list.add(current.toString().toLowerCase());
    	}
    	Collections.sort(list);
		return list;
    }

    @Override
	public String toString() {
		return "NewsData [title=" + title + ", tags=" + tags.toString() + ", content=" + content + "]";
	}
}
