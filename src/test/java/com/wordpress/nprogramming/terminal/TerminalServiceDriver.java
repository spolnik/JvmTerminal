package com.wordpress.nprogramming.terminal;

import com.wordpress.nprogramming.terminal.builders.FileSystemBuilder;

import java.io.IOException;
import java.nio.file.*;
import java.util.Arrays;
import java.util.List;

import static com.wordpress.nprogramming.terminal.utils.PathHelper.normalize;

public final class TerminalServiceDriver {

    private final TerminalService terminal;
    private final FileSystem fileSystem;

    public TerminalServiceDriver() {
        fileSystem = FileSystems.getDefault();
        terminal = new TerminalService(fileSystem);
    }

    public TerminalServiceDriver(String path) {
        fileSystem = FileSystemBuilder.create()
                .withWorkingDirectorySetTo(path)
                .build();

        terminal = new TerminalService(fileSystem);
    }

    public String processLinuxCommand(String rawCommand)
            throws IOException {

        return terminal.processLinuxCommand(rawCommand);
    }

    public List<String> asList(String text)
            throws IOException {

        return Arrays.asList(text.split(" "));
    }

    public String directoryName(String fullPath)
            throws IOException {

        return Paths.get(fullPath)
                .getFileName().toString();
    }

    public boolean fileExists(String path) {
        Path removedDirPath = fileSystem.getPath(path);
        return Files.exists(removedDirPath);
    }

    public void makeSureThatDirectoryExists(String dirName)
            throws IOException {

        deleteIfExistsAndThenCreateDirectory(dirName)
                .runOn(fileSystem);
    }

    private static RunOn deleteIfExistsAndThenCreateDirectory(
            String dirName)
            throws IOException {

        return fileSystem -> {

            Path path = normalize(fileSystem.getPath(dirName));

            fileSystem.provider().deleteIfExists(path);
            fileSystem.provider().createDirectory(path);
        };
    }

    private interface RunOn {
        void runOn(FileSystem fileSystem) throws IOException;
    }
}
