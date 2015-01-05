package com.wordpress.nprogramming.terminal.core;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

public interface FileSystemContext {
    Path workingDir();

    void changeWorkingDir(Path workingDirectory)
            throws IOException;

    Path homeDir();

    Path asPath(String dir);

    void createDirectory(String dirName) throws IOException;

    void removeDirectory(String dirName) throws IOException;

    void moveFile(String source, String destination) throws IOException;

    void deleteFile(String fileName) throws IOException;

    void createFile(String fileName) throws IOException;

    Stream<Path> listFiles() throws IOException;

    Stream<Path> listFiles(String dirName) throws IOException;

    void copyFile(String sourceFileName, String destinationFileName) throws IOException;
}
