package com.adrrriannn.word.searcher.service.indexer;

import com.adrrriannn.word.searcher.exception.DirectoryIndexingException;
import com.adrrriannn.word.searcher.exception.FileContentReadingException;
import com.adrrriannn.word.searcher.exception.InvalidDirectoryException;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class IndexerServiceImpl implements IndexerService {
    @Override
    public Map<String, Set<String>> indexDirectory(String directoryPath) {

        if(directoryPath == null || directoryPath.isEmpty()) {
            throw new InvalidDirectoryException(directoryPath);
        }

        try (Stream<Path> paths = Files.walk(Paths.get(directoryPath))) {
            Map<String, Set<String>> result = paths
                .filter(Files::isRegularFile)
                .collect(
                    Collectors.toMap(
                        path -> path.toFile().getName(),
                        this::getFileWords
                    )
                );

            return Collections.unmodifiableMap(result);
        } catch (IOException ex) {
            throw new DirectoryIndexingException(directoryPath);
        }
    }

    private Set<String> getFileWords(Path path) {
        Set<String> fileWords = new HashSet<>();

        try (BufferedReader br = Files.newBufferedReader(path)) {

            String line;
            while ((line = br.readLine()) != null) {
                line = line.replaceAll("[\\-\\+\\.\\^:,]","");
                String[] words = line.split(" ");
                fileWords.addAll(Arrays.asList(words));
            }

        } catch (IOException e) {
            throw new FileContentReadingException(path.toFile().getName());
        }

        return fileWords;
    }
}
