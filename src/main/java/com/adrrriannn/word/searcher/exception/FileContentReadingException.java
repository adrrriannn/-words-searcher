package com.adrrriannn.word.searcher.exception;

public class FileContentReadingException extends RuntimeException {

    private static final String MESSAGE = "Error occurred reading content of file: %s";

    public FileContentReadingException(String filename) {
        super(String.format(MESSAGE, filename));
    }
}
