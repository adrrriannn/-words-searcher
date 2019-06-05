package com.adrrriannn.word.searcher.model;

public class SearchResult {
    private String filename;
    private Double foundWordsPercentage;

    public SearchResult(String filename, Double foundWordsPercentage) {
        this.filename = filename;
        this.foundWordsPercentage = foundWordsPercentage;
    }

    public String getFilename() {
        return filename;
    }

    public Double getFoundWordsPercentage() {
        return foundWordsPercentage;
    }

}
