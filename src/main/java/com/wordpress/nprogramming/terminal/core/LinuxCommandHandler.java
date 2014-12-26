package com.wordpress.nprogramming.terminal.core;

import com.google.common.base.Strings;

public interface LinuxCommandHandler {
    String commandName();
    String process(TerminalContext context, String... args);

    default boolean canHandle(String linuxCommand) {
        return !Strings.isNullOrEmpty(linuxCommand) && linuxCommand.equals(commandName());
    }
}
