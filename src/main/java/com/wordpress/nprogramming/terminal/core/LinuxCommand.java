package com.wordpress.nprogramming.terminal.core;

import com.google.common.base.Strings;

import java.io.IOException;

public interface LinuxCommand {
    String name();
    String execute(FileSystemContext context, String... args)
            throws IOException;

    default boolean canHandle(String linuxCommand) {
        return !Strings.isNullOrEmpty(linuxCommand)
                && linuxCommand.equals(name());
    }
}
