package service.indexer;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class IndexerServiceImplTest {

    private static final String DIRECTORY_PATH = "";

    private static final String FILENAME_1 = "FILE_1";
    private static final String FILENAME_2 = "FILE_2";
    private static final String FILENAME_3 = "FILE_3";

    private static final Map<String, Set<String>> INDEXED_FILES = new HashMap<>();
    static {
        INDEXED_FILES.put(FILENAME_1, Stream.of("Hello", "world", "I'm", "here").collect(Collectors.toSet()));
        INDEXED_FILES.put(FILENAME_2, Stream.of("Wonderful", "world").collect(Collectors.toSet()));
        INDEXED_FILES.put(FILENAME_3, Stream.of("Words", "are", "not", "here").collect(Collectors.toSet()));
    }

    private static final IndexerService indexerService = new IndexerServiceImpl();

    @Test
    public void indexDirectory() {
        Map<String, Set<String>> result = indexerService.indexDirectory(DIRECTORY_PATH);
        assertNotNull(result);

        Set<String> file1Content = result.get(FILENAME_1);
        assertEquals(INDEXED_FILES.get(FILENAME_1), file1Content);

        Set<String> file2Content = result.get(FILENAME_2);
        assertEquals(INDEXED_FILES.get(FILENAME_2), file2Content);

        Set<String> file3Content = result.get(FILENAME_3);
        assertEquals(INDEXED_FILES.get(FILENAME_3), file3Content);
    }
}
