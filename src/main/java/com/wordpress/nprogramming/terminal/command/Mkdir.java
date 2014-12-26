package com.wordpress.nprogramming.terminal.command;

import com.wordpress.nprogramming.terminal.core.LinuxCommandHandler;
import com.wordpress.nprogramming.terminal.core.TerminalContext;

import java.io.IOException;
import java.nio.file.Path;

public class Mkdir implements LinuxCommandHandler {
    @Override
    public String commandName() {
        return "mkdir";
    }

    @Override
    public String process(TerminalContext context, String... args) throws IOException {
        String dirName = args[0];
        Path path = context.getFileSystem().getPath(dirName).toAbsolutePath().normalize();

        context.getFileSystem().provider().createDirectory(path);
        return "";
    }
}
