package com.wordpress.nprogramming.terminal.core;

import java.nio.file.FileSystem;

public final class TerminalContext {
    private final FileSystem fileSystem;
    private String workingDirectory;

    public TerminalContext(FileSystem fileSystem, String workingDirectory) {
        this.fileSystem = fileSystem;
        this.workingDirectory = workingDirectory;
    }

    public String getWorkingDirectory() {
        return workingDirectory;
    }

    public void changeWorkingDirectory(String workingDirectory) {
        this.workingDirectory = workingDirectory;
    }

    public FileSystem getFileSystem() {
        return fileSystem;
    }
}
