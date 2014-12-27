package com.wordpress.nprogramming.terminal.core;

import java.io.IOException;
import java.nio.file.Path;

public interface FileSystemContext {
    Path workingDir();

    void changeWorkingDir(Path workingDirectory)
            throws IOException;

    Path homeDir();

    Path asPath(String dir);

    void createDirectory(String dirName) throws IOException;

    void removeDirectory(String dirName) throws IOException;
}
