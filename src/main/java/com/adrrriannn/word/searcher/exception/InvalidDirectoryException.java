package com.adrrriannn.word.searcher.exception;

public class InvalidDirectoryException extends RuntimeException {

    private static final String MESSAGE = "Directory path can not be null";

    public InvalidDirectoryException(String message) {
        super(message);
    }
}
