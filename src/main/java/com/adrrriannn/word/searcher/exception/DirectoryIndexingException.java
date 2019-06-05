package com.adrrriannn.word.searcher.exception;

public class DirectoryIndexingException extends RuntimeException {

    private static final String MESSAGE = "Error occurred when reading directory contents for path %s";

    public DirectoryIndexingException(String path) {
        super(String.format(MESSAGE, path));
    }
}
