package com.wordpress.nprogramming.terminal;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Key;
import com.wordpress.nprogramming.terminal.builders.FileSystemBuilder;
import com.wordpress.nprogramming.terminal.core.LinuxCommand;

import java.io.IOException;
import java.nio.file.*;
import java.util.Arrays;
import java.util.List;

import static com.wordpress.nprogramming.terminal.utils.PathHelper.normalize;

public final class TerminalServiceDriver {

    private final TerminalService terminal;
    private final FileSystem fileSystem;

    public TerminalServiceDriver() {
        this(FileSystems.getDefault());
    }

    public TerminalServiceDriver(String path) {
        
        this(FileSystemBuilder.create()
                        .withWorkingDirectorySetTo(path)
                        .build()
        );
    }
    
    private TerminalServiceDriver(FileSystem fileSystem) {
        
        Injector injector = Guice.createInjector(new TerminalAppModule());
        List<LinuxCommand> linuxCommands = 
                injector.getInstance(new Key<List<LinuxCommand>>() {});
        
        terminal = new TerminalService(fileSystem, linuxCommands);
        this.fileSystem = fileSystem;        
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
