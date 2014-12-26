package com.wordpress.nprogramming.terminal.core;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.Files;
import java.nio.file.NotDirectoryException;
import java.nio.file.Path;

public final class TerminalContext implements FileSystemContext {

    private final FileSystem fileSystem;
    private final String homeDirectory;

    private String workingDirectory;

    public TerminalContext(
            FileSystem fileSystem, String workingDirectory) {

        this.fileSystem = fileSystem;
        this.workingDirectory = workingDirectory;

        homeDirectory = workingDirectory;
    }

    @Override
    public String workingDir() {
        return workingDirectory;
    }

    @Override
    public void changeWorkingDir(
            String newPath)
            throws IOException {

        Path path = this.fileSystem.getPath(newPath);

        if (Files.exists(path)) {
            throwIfNotDirectory(newPath, path);
            this.workingDirectory = newPath;
        } else {
            throwFileNotFound(newPath);
        }
    }

    @Override
    public FileSystem fileSystem() {
        return fileSystem;
    }

    @Override
    public String homeDir() {
        return homeDirectory;
    }

    private void throwFileNotFound(
            String workingDirectory)
            throws FileNotFoundException {

        String errorMessage =
                String.format("cd: %s: No such file or directory",
                        workingDirectory);

        throw new FileNotFoundException(errorMessage);
    }

    private void throwIfNotDirectory(
            String workingDirectory, Path path)
            throws NotDirectoryException {

        if (Files.isDirectory(path)) {
            return;
        }

        String errorMessage =
                String.format("cd: %s: Not a directory",
                        workingDirectory);

        throw new NotDirectoryException(errorMessage);
    }
}
