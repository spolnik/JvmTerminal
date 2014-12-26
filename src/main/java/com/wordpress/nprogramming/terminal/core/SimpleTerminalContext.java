package com.wordpress.nprogramming.terminal.core;

import java.nio.file.FileSystem;

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
    public void changeWorkingDirectory(String workingDirectory) {
        this.workingDirectory = workingDirectory;
    }

    @Override
    public FileSystem getFileSystem() {
        return fileSystem;
    }

    @Override
    public String getHomeDir() {
        return homeDirectory;
    }
}
