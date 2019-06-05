package com.adrrriannn.word.searcher.service.indexer;

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
    public Map<String, Set<String>> indexDirectory(String directoryPath) throws IOException {
        try (Stream<Path> paths = Files.walk(Paths.get(directoryPath))) {
            return paths
                .filter(Files::isRegularFile)
                .collect(Collectors.toMap(
                        path -> path.toFile().getName(),
                        this::getFileWords
                ));
        }
    }

    private Set<String> getFileWords(Path path) {
        Set<String> fileWords = new HashSet<>();

        try (BufferedReader br = Files.newBufferedReader(path)) {

            String line;
            while ((line = br.readLine()) != null) {
                String[] words = line.split(" ");
                fileWords.addAll(Arrays.asList(words));
            }

        } catch (IOException e) {
            System.err.format("IOException: %s%n", e);
        }

        return fileWords;
    }
}
