package com.wordpress.nprogramming.terminal.core;

import java.nio.file.FileSystem;

public interface TerminalContext {
    String getWorkingDirectory();

    void changeWorkingDirectory(String workingDirectory);

    FileSystem getFileSystem();

    String getHomeDir();
}
