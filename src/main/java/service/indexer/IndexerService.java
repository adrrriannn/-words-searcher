package service.indexer;

import java.util.Map;
import java.util.Set;

public interface IndexerService {
    Map<String, Set<String>> indexDirectory(String directoryPath);
}
