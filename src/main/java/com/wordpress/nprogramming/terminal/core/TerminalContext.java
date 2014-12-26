package com.wordpress.nprogramming.terminal.core;

import java.io.FileNotFoundException;
import java.nio.file.FileSystem;
import java.nio.file.NotDirectoryException;

public interface TerminalContext {
    String getWorkingDirectory();

    void changeWorkingDirectory(String workingDirectory) throws FileNotFoundException, NotDirectoryException;

    FileSystem getFileSystem();

    String getHomeDir();
}
