package com.wordpress.nprogramming.terminal.core;

import com.google.common.base.Strings;

import java.io.FileNotFoundException;
import java.nio.file.NotDirectoryException;

public interface LinuxCommandHandler {
    String commandName();
    String process(TerminalContext context, String... args) throws FileNotFoundException, NotDirectoryException;

    default boolean canHandle(String linuxCommand) {
        return !Strings.isNullOrEmpty(linuxCommand) && linuxCommand.equals(commandName());
    }
}
