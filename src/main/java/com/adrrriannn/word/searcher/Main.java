package com.adrrriannn.word.searcher;

import com.adrrriannn.word.searcher.launcher.ApplicationLauncher;
import com.adrrriannn.word.searcher.service.indexer.IndexerService;
import com.adrrriannn.word.searcher.service.indexer.IndexerServiceImpl;
import com.adrrriannn.word.searcher.service.searcher.WordSearcherService;
import com.adrrriannn.word.searcher.service.searcher.WordSearcherServiceImpl;

public class Main {

    public static void main(String[] args) {
        final IndexerService indexerService = new IndexerServiceImpl();
        final WordSearcherService wordSearcherService = new WordSearcherServiceImpl();
        final ApplicationLauncher applicationLauncher = new ApplicationLauncher(indexerService, wordSearcherService, System.in, System.out);

        applicationLauncher.launch(args);
    }

}
