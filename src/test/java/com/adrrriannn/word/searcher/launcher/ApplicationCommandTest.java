package com.adrrriannn.word.searcher.launcher;

import org.junit.Test;

import static org.junit.Assert.*;

public class ApplicationCommandTest {

    @Test
    public void fromInstructionQuit() {
        ApplicationCommand applicationCommand = ApplicationCommand.fromInstruction(":quit");
        assertEquals(ApplicationCommand.QUIT, applicationCommand);
    }

    @Test
    public void fromInstructionNotFound() {
        ApplicationCommand applicationCommand = ApplicationCommand.fromInstruction("invalidInstruction");
        assertNull(applicationCommand);
    }
}