package com.wordpress.nprogramming.terminal;

import java.nio.file.FileSystem;

public class TerminalService {
    private final FileSystem fileSystem;
    private final String workingDirectory;

    public TerminalService(FileSystem aFileSystem, String aWorkingDirectory) {
        fileSystem = aFileSystem;
        workingDirectory = aWorkingDirectory;
    }

    public String pwd() {
        return workingDirectory;
    }
}
