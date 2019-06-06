package com.adrrriannn.word.searcher.config;

import com.adrrriannn.word.searcher.exception.InvalidSearchModeException;

import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Stream;

public enum SearchMode {

    CASE_SENSITIVE{
        @Override
        public Predicate<String> getPredicate(Set<String> sourceContent) {
            return sourceContent::contains;
        }
    },

    CASE_INSENSITIVE {
        @Override
        public Predicate<String> getPredicate(Set<String> sourceContent) {
            return word -> sourceContent.parallelStream()
                    .anyMatch(sourceWord -> sourceWord.equalsIgnoreCase(word));
        }
    };

    public abstract Predicate<String> getPredicate(Set<String> sourceContent);

    public static SearchMode fromString(String searchModeAsString) {
        return Stream.of(values())
            .filter(searchMode -> searchMode.name().equals(searchModeAsString))
            .findFirst().orElseThrow(InvalidSearchModeException::new);
    }
}
