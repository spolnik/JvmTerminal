package com.wordpress.nprogramming.terminal.core;

import java.io.IOException;
import java.nio.file.Path;

public interface FileSystemContext {
    Path workingDir();

    void changeWorkingDir(String workingDirectory)
            throws IOException;

    String homeDir();

    void createDirectory(String dirName) throws IOException;

    String getSeparator();
}
