package com.wordpress.nprogramming.terminal.core;

import java.io.IOException;
import java.nio.file.FileSystem;

public interface FileSystemContext {
    String workingDir();

    void changeWorkingDir(String workingDirectory)
            throws IOException;

    FileSystem fileSystem();
    String homeDir();
}
