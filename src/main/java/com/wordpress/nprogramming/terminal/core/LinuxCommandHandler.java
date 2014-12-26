package com.wordpress.nprogramming.terminal.core;

import com.google.common.base.Strings;

import java.io.IOException;

public interface LinuxCommandHandler {
    String commandName();
    String process(TerminalContext context, String... args) throws IOException;

    default boolean canHandle(String linuxCommand) {
        return !Strings.isNullOrEmpty(linuxCommand) && linuxCommand.equals(commandName());
    }
}
