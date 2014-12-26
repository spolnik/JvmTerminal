package com.wordpress.nprogramming.terminal.core;

import java.io.FileNotFoundException;
import java.nio.file.FileSystem;
import java.nio.file.Files;
import java.nio.file.NotDirectoryException;
import java.nio.file.Path;

public final class SimpleTerminalContext implements TerminalContext {
    private final FileSystem fileSystem;
    private String workingDirectory;
    private final String homeDirectory;

    public SimpleTerminalContext(FileSystem fileSystem, String workingDirectory) {
        this.fileSystem = fileSystem;
        this.workingDirectory = workingDirectory;
        homeDirectory = workingDirectory;
    }

    @Override
    public String getWorkingDirectory() {
        return workingDirectory;
    }

    @Override
    public void changeWorkingDirectory(String workingDirectory) throws FileNotFoundException, NotDirectoryException {
        Path path = this.fileSystem.getPath(workingDirectory);
        if (Files.exists(path)) {
            throwIfNotDirectory(workingDirectory, path);
            this.workingDirectory = workingDirectory;
        } else {
            throwFileNotFound(workingDirectory);
        }
    }

    @Override
    public FileSystem getFileSystem() {
        return fileSystem;
    }

    @Override
    public String getHomeDir() {
        return homeDirectory;
    }

    private void throwFileNotFound(String workingDirectory) throws FileNotFoundException {
        String errorMessage =
                String.format("cd: %s: No such file or directory", workingDirectory);

        throw new FileNotFoundException(errorMessage);
    }

    private void throwIfNotDirectory(String workingDirectory, Path path) throws NotDirectoryException {
        if (Files.isDirectory(path)) {
            return;
        }

        String errorMessage =
                String.format("cd: %s: Not a directory", workingDirectory);

        throw new NotDirectoryException(errorMessage);
    }
}
