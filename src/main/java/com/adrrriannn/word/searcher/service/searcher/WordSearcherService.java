package com.adrrriannn.word.searcher.service.searcher;

import com.adrrriannn.word.searcher.config.SearchMode;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface WordSearcherService {
    Map<String, Double> search(Map<String, Set<String>> source, List<String> words, SearchMode searchMode);
}
