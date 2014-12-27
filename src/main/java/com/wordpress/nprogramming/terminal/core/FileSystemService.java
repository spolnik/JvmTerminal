package com.wordpress.nprogramming.terminal.core;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.Files;
import java.nio.file.NotDirectoryException;
import java.nio.file.Path;

import static com.wordpress.nprogramming.terminal.utils.PathHelper.normalize;

public final class FileSystemService implements FileSystemContext {

    private final FileSystem fileSystem;
    private final Path homeDirectory;

    private Path workingDirectory;

    public FileSystemService(
            FileSystem fileSystem, String workingDirectory) {

        this.fileSystem = fileSystem;

        Path normalizedPath = normalize(fileSystem.getPath(workingDirectory));

        this.workingDirectory = normalizedPath;
        homeDirectory = normalizedPath;
    }

    @Override
    public Path workingDir() {
        return workingDirectory;
    }

    @Override
    public void changeWorkingDir(Path newPath)
            throws IOException {

        if (Files.exists(newPath)) {
            throwIfNotDirectory(newPath);
            this.workingDirectory = newPath;
        } else {
            throwFileNotFound(newPath);
        }
    }

    @Override
    public Path homeDir() {
        return homeDirectory;
    }

    @Override
    public Path asPath(String dir) {
        return normalize(this.fileSystem.getPath(workingDirectory.toString()).resolve(dir));
    }

    @Override
    public void createDirectory(String dirName)
            throws IOException {

        Path path = asPath(dirName);
        fileSystem.provider().createDirectory(path);
    }

    private void throwFileNotFound(
            Path workingDirectory)
            throws FileNotFoundException {

        String errorMessage =
                String.format("cd: %s: No such file or directory",
                        workingDirectory.toString());

        throw new FileNotFoundException(errorMessage);
    }

    private void throwIfNotDirectory(Path path)
            throws NotDirectoryException {

        if (Files.isDirectory(path)) {
            return;
        }

        String errorMessage =
                String.format("cd: %s: Not a directory",
                        path.toString());

        throw new NotDirectoryException(errorMessage);
    }
}
