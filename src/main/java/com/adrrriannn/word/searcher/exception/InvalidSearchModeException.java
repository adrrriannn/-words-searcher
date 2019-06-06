package com.adrrriannn.word.searcher.exception;

public class InvalidSearchModeException extends RuntimeException {

    private static final String MESSAGE = "Invalid search mode. Value must be either CASE_SENSITIVE or CASE_INSENSITIVE";

    public InvalidSearchModeException() {
        super(MESSAGE);
    }
}
