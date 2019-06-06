package com.adrrriannn.word.searcher.launcher;

import com.adrrriannn.word.searcher.config.SearchConfiguration;
import com.adrrriannn.word.searcher.config.SearchMode;
import com.adrrriannn.word.searcher.service.indexer.IndexerService;
import com.adrrriannn.word.searcher.service.searcher.WordSearcherService;
import com.adrrriannn.word.searcher.util.Constants;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.*;

public class ApplicationLauncher {

    private final IndexerService indexerService;
    private final WordSearcherService wordSearcherService;
    private final InputStream inputStream;
    private final PrintStream printStream;

    public ApplicationLauncher(IndexerService indexerService, WordSearcherService wordSearcherService,
                               InputStream inputStream, PrintStream printStream) {
        this.indexerService = indexerService;
        this.wordSearcherService = wordSearcherService;
        this.inputStream = inputStream;
        this.printStream = printStream;
    }

    public void launch(String[] args) {

        if (args.length == 0) {
            throw new IllegalArgumentException("No directory given to index");
        }
        String indexableDirectory​ = args[0];
        if(args.length > 1) {
            SearchMode searchMode = SearchMode.fromString(args[1]);
            SearchConfiguration.setSearchMode(searchMode);
        }

        final Map<String, Set<String>> indexedFiles = indexerService.indexDirectory(indexableDirectory​);

        printStream.println(String.format(Constants.FILES_READ_MESSAGE, indexedFiles.size(), indexableDirectory​));
        Scanner keyboard​ = new Scanner(inputStream);

        while (true) {
            printStream.print(Constants.SEARCH_LABEL);
            final String input = keyboard​.nextLine();

            ApplicationCommand applicationCommand = ApplicationCommand.fromInstruction(input);
            if(ApplicationCommand.QUIT.equals(applicationCommand)) {
                break;
            }
            List<String> keyboardInputAsWords = Arrays.asList(input.split(" "));
            SearchMode searchMode = SearchConfiguration.getSearchMode();

            Map<String, Double> result = wordSearcherService.search(indexedFiles, keyboardInputAsWords, searchMode);

            result.forEach((filename, percentage) -> printStream.println(String.format(Constants.FILE_SCORE_MESSAGE, filename, percentage)));
        }
    }

}
