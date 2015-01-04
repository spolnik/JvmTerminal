package com.wordpress.nprogramming.terminal;

import com.wordpress.nprogramming.terminal.core.FileSystemContext;
import com.wordpress.nprogramming.terminal.core.FileSystemService;
import com.wordpress.nprogramming.terminal.core.LinuxCommand;

import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static com.wordpress.nprogramming.terminal.utils.PathHelper.normalize;

public class TerminalService {

    private final List<LinuxCommand> linuxCommands;
    private final FileSystemContext fileSystemContext;

    public TerminalService(List<LinuxCommand> linuxCommands) {
        this(FileSystems.getDefault(), linuxCommands);
    }

    public TerminalService(FileSystem aFileSystem, List<LinuxCommand> linuxCommands) {

        fileSystemContext =
                new FileSystemService(aFileSystem, workingDir(aFileSystem));

        this.linuxCommands = linuxCommands;
    }

    private String workingDir(FileSystem aFileSystem) {
        return normalize(aFileSystem.getPath(".")).toString();
    }

    public String processLinuxCommand(
            String rawCommand)
            throws IOException {

        String[] linuxCommandParts =
                rawCommand.split(" ");

        Optional<LinuxCommand> linuxCommand =
                findLinuxCommand(linuxCommandParts[0]);

        if (linuxCommand.isPresent()) {
            return linuxCommand.get().execute(
                    fileSystemContext,
                    linuxCommandArguments(linuxCommandParts));
        }

        return "Invalid Command!";
    }

    private Optional<LinuxCommand> findLinuxCommand(
            String linuxCommandName) {

        return linuxCommands.stream()
                .filter(command -> command.canHandle(linuxCommandName))
                .findFirst();
    }

    private String[] linuxCommandArguments(
            String[] linuxCommandParts) {

        return Arrays.copyOfRange(
                linuxCommandParts, 1, linuxCommandParts.length);
    }
}
