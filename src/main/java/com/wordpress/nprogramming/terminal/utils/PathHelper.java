package com.wordpress.nprogramming.terminal.utils;

import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.Path;

public final class PathHelper {

    public static String normalize(Path path) {
        return path.toAbsolutePath().normalize().toString();
    }

    public static RunOn deleteIfExistsAndThenCreateDirectory(
            String dirName)
            throws IOException {

        return fileSystem -> {

            Path path = fileSystem.getPath(dirName)
                    .toAbsolutePath().normalize();

            fileSystem.provider().deleteIfExists(path);
            fileSystem.provider().createDirectory(path);
        };
    }

    public interface RunOn {
        void runOn(FileSystem fileSystem) throws IOException;
    }
}
