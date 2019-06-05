package com.adrrriannn.word.searcher.launcher;

import java.util.stream.Stream;

public enum ApplicationCommand {

    QUIT(":quit");

    private String instruction;

    ApplicationCommand(String instruction) {
        this.instruction = instruction;
    }

    public static ApplicationCommand fromInstruction(String instruction) {
        return Stream.of(values())
                .filter(applicationCommand -> applicationCommand.instruction.equals(instruction))
                .findFirst().orElse(null);
    }
}
