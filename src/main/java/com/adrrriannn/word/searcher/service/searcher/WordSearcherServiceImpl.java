package com.adrrriannn.word.searcher.service.searcher;

import com.adrrriannn.word.searcher.config.SearchConfiguration;
import com.adrrriannn.word.searcher.config.SearchMode;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class WordSearcherServiceImpl implements WordSearcherService {
    @Override
    public Map<String, Double> search(Map<String, Set<String>> source, List<String> words, SearchMode searchMode) {
        int wordsSize = words.size();

        return source.entrySet().parallelStream()
                .collect(
                    Collectors.toMap(
                        Map.Entry::getKey,
                        fileEntry -> {
                            Set<String> fileContent = fileEntry.getValue();

                            int foundWords = words.parallelStream()
                                .filter(searchMode.getPredicate(fileContent))
                                .mapToInt(word -> 1)
                                .sum();

                            return (((double)foundWords / wordsSize) * 100.0);
                        }
                    )
                );
    }

}
