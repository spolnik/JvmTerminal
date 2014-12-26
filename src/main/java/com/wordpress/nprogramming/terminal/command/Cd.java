package com.wordpress.nprogramming.terminal.command;

import com.wordpress.nprogramming.terminal.core.TerminalContext;
import com.wordpress.nprogramming.terminal.core.LinuxCommandHandler;

import java.nio.file.Path;

public class Cd implements LinuxCommandHandler {

    @Override
    public boolean canHandle(String linuxCommand) {
        return linuxCommand.equals("cd");
    }

    @Override
    public String process(TerminalContext context, String... args) {
        String directoryName = args[0];

        String workingDirectory =
                directoryName.startsWith("\\")
                        ? directoryName
                        : buildNewWorkingDirectoryPath(context, directoryName);

        context.changeWorkingDirectory(workingDirectory);
        return "";
    }

    private String buildNewWorkingDirectoryPath(TerminalContext context, String path) {
        Path workingDirectoryPath =
                context.getFileSystem().getPath(context.getWorkingDirectory());

        Path resolvedPath =
                workingDirectoryPath.resolve(path).normalize();

        return resolvedPath.toAbsolutePath().toString();
    }
}
