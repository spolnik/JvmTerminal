package com.wordpress.nprogramming.terminal.command;

import com.wordpress.nprogramming.terminal.core.LinuxCommandHandler;
import com.wordpress.nprogramming.terminal.core.TerminalContext;

import java.io.FileNotFoundException;
import java.nio.file.NotDirectoryException;
import java.nio.file.Path;

public class Cd implements LinuxCommandHandler {

    @Override
    public String commandName() {
        return "cd";
    }

    @Override
    public String process(TerminalContext context, String... args) throws FileNotFoundException, NotDirectoryException {
        if (args.length == 0) {
            context.changeWorkingDirectory(context.getHomeDir());
            return "";
        }

        String workingDirectory = getNewWorkingDir(context, args[0]);

        context.changeWorkingDirectory(workingDirectory);
        return "";
    }

    private String getNewWorkingDir(TerminalContext context, String directoryName) {
        return directoryName.startsWith(context.getFileSystem().getSeparator())
                ? directoryName
                : buildNewWorkingDirectoryPath(context, directoryName);
    }

    private String buildNewWorkingDirectoryPath(TerminalContext context, String path) {
        Path workingDirectoryPath =
                context.getFileSystem().getPath(context.getWorkingDirectory());

        Path resolvedPath =
                workingDirectoryPath.resolve(path).normalize();

        return resolvedPath.toAbsolutePath().toString();
    }
}
