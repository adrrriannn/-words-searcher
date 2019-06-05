package com.adrrriannn.word.searcher;

import com.adrrriannn.word.searcher.service.indexer.IndexerService;
import com.adrrriannn.word.searcher.service.indexer.IndexerServiceImpl;
import com.adrrriannn.word.searcher.service.searcher.WordSearcherService;
import com.adrrriannn.word.searcher.service.searcher.WordSearcherServiceImpl;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Main {

    private static Map<String, Set<String>> INDEXED_FILES;
    private static final IndexerService INDEXER_SERVICE = new IndexerServiceImpl();
    private static final WordSearcherService WORD_SEARCHER_SERVICE = new WordSearcherServiceImpl();

    public static void main(String[] args) throws IOException {

//        if (args.length == 0) {
//            throw new IllegalArgumentException("No directory given to index");
//        }


        final String indexableDirectory​ = "src/test/resources/example/";
        INDEXED_FILES = INDEXER_SERVICE.indexDirectory(indexableDirectory​);
        System.out.println(String.format("%d files read in directory %s", INDEXED_FILES.size(), indexableDirectory​));

        Scanner keyboard​ = new Scanner(System.in);

        while (true) {
            System.out.print("search> ");
            final String line = keyboard​.nextLine();
            if(":quit".equals(line)) {
                System.exit(0);
            }

            Map<String, Double> result = WORD_SEARCHER_SERVICE.search(INDEXED_FILES, Arrays.asList(line.split(" ")));
            result.forEach((filename, percentage) -> System.out.println(filename + " " + percentage + "%"));
        }
    }

}
