package com.wordpress.nprogramming.terminal.utils;

import java.nio.file.Path;

public final class PathHelper {

    public static Path normalize(Path path) {
        return path.toAbsolutePath().normalize();
    }
}
