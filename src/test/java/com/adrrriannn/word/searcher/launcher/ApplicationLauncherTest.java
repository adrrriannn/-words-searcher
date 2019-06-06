package com.adrrriannn.word.searcher.launcher;

import com.adrrriannn.word.searcher.service.indexer.IndexerService;
import com.adrrriannn.word.searcher.service.searcher.WordSearcherService;
import com.adrrriannn.word.searcher.util.Constants;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ApplicationLauncherTest {

    private static final String FILENAME_1 = "file_1";
    private static final String FILENAME_2 = "file_2";
    private static final String FILENAME_3 = "file_3";

    private static final Map<String, Set<String>> INDEXED_FILES = new HashMap<>();
    static {
        INDEXED_FILES.put(FILENAME_1, Stream.of("Hello", "world", "I'm", "here").collect(Collectors.toSet()));
        INDEXED_FILES.put(FILENAME_2, Stream.of("Wonderful", "world").collect(Collectors.toSet()));
        INDEXED_FILES.put(FILENAME_3, Stream.of("Words", "are", "not", "here").collect(Collectors.toSet()));
    }

    private static final Map<String, Double> SEARCH_RESULTS = new HashMap<>();
    static {
        SEARCH_RESULTS.put(FILENAME_1, 25.0);
        SEARCH_RESULTS.put(FILENAME_2, 50.0);
        SEARCH_RESULTS.put(FILENAME_3, 100.0);
    }

    @Mock
    private IndexerService indexerService;

    @Mock
    private WordSearcherService wordSearcherService;

    @Mock
    private PrintStream printStream;

    private ApplicationLauncher applicationLauncher;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        doReturn(INDEXED_FILES).when(indexerService).indexDirectory(any());
        doReturn(SEARCH_RESULTS).when(wordSearcherService).search(any(), any(), any());
    }

    @Test
    public void launch() {
        String[] args = {"someDir/anotherDir/"};
        InputStream inputStream = new ByteArrayInputStream("Hello world \n:quit".getBytes());

        applicationLauncher = new ApplicationLauncher(indexerService, wordSearcherService, inputStream, printStream);
        applicationLauncher.launch(args);

        verify(printStream).println(INDEXED_FILES.size() + " files read in directory " + args[0]);
        verify(printStream, times(2)).print(Constants.SEARCH_LABEL);
        verify(indexerService).indexDirectory(any());
        verify(wordSearcherService).search(any(), any(), any());
        SEARCH_RESULTS.entrySet().stream()
                .peek(entry -> verify(printStream).println(entry.getKey() + " " + entry.getValue() + "%"));
    }


    @Test
    public void launchAndQuit() {
        String[] args = {"someDir/anotherDir/"};
        InputStream inputStream = new ByteArrayInputStream(":quit".getBytes());

        applicationLauncher = new ApplicationLauncher(indexerService, wordSearcherService, inputStream, printStream);
        applicationLauncher.launch(args);

        verify(printStream).println(INDEXED_FILES.size() + " files read in directory " + args[0]);
        verify(printStream, times(1)).print(Constants.SEARCH_LABEL);
        verify(indexerService).indexDirectory(any());
        verify(wordSearcherService, never()).search(any(), any(), any());
    }

    @Test(expected = IllegalArgumentException.class)
    public void launchInvalidArguments() {
        applicationLauncher = new ApplicationLauncher(indexerService, wordSearcherService, mock(InputStream.class), printStream);
        String[] args = {};
        applicationLauncher.launch(args);
    }
}


