package com.adrrriannn.word.searcher.service.searcher;

import com.adrrriannn.word.searcher.model.SearchResult;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class WordSearcherServiceImpl implements WordSearcherService {
    @Override
    public Map<String, Double> search(Map<String, Set<String>> source, List<String> words) {
        int wordsSize = words.size();

        return source.entrySet().parallelStream()
                .map(entry -> {
                    int foundWords = 0;
                    Set<String> sourceContent = entry.getValue();
                    for(String word : words) {
                        if(sourceContent.contains(word)) {
                            foundWords++;
                        }
                    }

                    Double foundWordsPercentage =  (((double)foundWords / wordsSize) * 100.0);
                    return new SearchResult(entry.getKey(), foundWordsPercentage);
                })
                .collect(Collectors.toMap(SearchResult::getFilename, SearchResult::getFoundWordsPercentage));
    }


}
