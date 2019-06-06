package com.adrrriannn.word.searcher.service.searcher;

import com.adrrriannn.word.searcher.config.SearchMode;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class WordSearcherServiceImplTest {

    private static final String FILENAME_1 = "FILE_1";
    private static final String FILENAME_2 = "FILE_2";
    private static final String FILENAME_3 = "FILE_3";

    private static final Map<String, Set<String>> INDEXED_FILES = new HashMap<>();
    static {
        INDEXED_FILES.put(FILENAME_1, Stream.of("Hello", "world", "I'm", "here").collect(Collectors.toSet()));
        INDEXED_FILES.put(FILENAME_2, Stream.of("Wonderful", "world").collect(Collectors.toSet()));
        INDEXED_FILES.put(FILENAME_3, Stream.of("Words", "are", "not", "here").collect(Collectors.toSet()));
    }

    private static WordSearcherService wordSearcherService = new WordSearcherServiceImpl();

    @Test
    public void searchCaseSensitive() {

        List<String> wordsTarget = Arrays.asList("Hello", "world");

        Map<String, Double> results = wordSearcherService.search(INDEXED_FILES, wordsTarget, SearchMode.CASE_SENSITIVE);

        Double file1Result = results.get(FILENAME_1);
        assertNotNull(file1Result);
        assertEquals(new Double(100), file1Result);

        Double file2Result = results.get(FILENAME_2);
        assertNotNull(file2Result);
        assertEquals(new Double(50), file2Result);

        Double file3Result = results.get(FILENAME_3);
        assertNotNull(file3Result);
        assertEquals(new Double(0), file3Result);
    }

    @Test
    public void searchCaseSensitiveNotFoundWords() {

        List<String> wordsTarget = Arrays.asList("hello", "World");

        Map<String, Double> results = wordSearcherService.search(INDEXED_FILES, wordsTarget, SearchMode.CASE_SENSITIVE);

        Double file1Result = results.get(FILENAME_1);
        assertNotNull(file1Result);
        assertEquals(new Double(0), file1Result);

        Double file2Result = results.get(FILENAME_2);
        assertNotNull(file2Result);
        assertEquals(new Double(0), file2Result);

        Double file3Result = results.get(FILENAME_3);
        assertNotNull(file3Result);
        assertEquals(new Double(0), file3Result);
    }

    @Test
    public void searchCaseInsensitive() {

        List<String> wordsTarget = Arrays.asList("hello", "world");

        Map<String, Double> results = wordSearcherService.search(INDEXED_FILES, wordsTarget, SearchMode.CASE_INSENSITIVE);

        Double file1Result = results.get(FILENAME_1);
        assertNotNull(file1Result);
        assertEquals(new Double(100), file1Result);

        Double file2Result = results.get(FILENAME_2);
        assertNotNull(file2Result);
        assertEquals(new Double(50), file2Result);

        Double file3Result = results.get(FILENAME_3);
        assertNotNull(file3Result);
        assertEquals(new Double(0), file3Result);
    }

    @Test
    public void searchWordsNotFound() {
        List<String> wordsTarget = Arrays.asList("awesome", "search");

        Map<String, Double> results = wordSearcherService.search(INDEXED_FILES, wordsTarget, SearchMode.CASE_SENSITIVE);

        Double file1Result = results.get(FILENAME_1);
        assertNotNull(file1Result);
        assertEquals(new Double(0), file1Result);

        Double file2Result = results.get(FILENAME_2);
        assertNotNull(file2Result);
        assertEquals(new Double(0), file2Result);

        Double file3Result = results.get(FILENAME_3);
        assertNotNull(file3Result);
        assertEquals(new Double(0), file3Result);
    }
}
