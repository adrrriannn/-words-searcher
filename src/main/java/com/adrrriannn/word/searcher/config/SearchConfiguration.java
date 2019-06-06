package com.adrrriannn.word.searcher.config;

public final class SearchConfiguration {

    private SearchConfiguration() {}

    private static SearchMode SEARCH_MODE = SearchMode.CASE_SENSITIVE;

    public static void setSearchMode(SearchMode searchMode) {
        SEARCH_MODE = searchMode;
    }

    public static SearchMode getSearchMode() {
        return SEARCH_MODE;
    }

    public static boolean isSearchModeCaseInsensitive() {
        return SearchMode.CASE_INSENSITIVE.equals(SEARCH_MODE);
    }
}
