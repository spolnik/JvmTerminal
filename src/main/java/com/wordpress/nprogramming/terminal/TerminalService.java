package com.wordpress.nprogramming.terminal;

import com.wordpress.nprogramming.terminal.command.Cd;
import com.wordpress.nprogramming.terminal.command.MkDir;
import com.wordpress.nprogramming.terminal.command.Pwd;
import com.wordpress.nprogramming.terminal.core.FileSystemContext;
import com.wordpress.nprogramming.terminal.core.LinuxCommand;
import com.wordpress.nprogramming.terminal.core.TerminalContext;

import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static com.wordpress.nprogramming.terminal.utils.PathHelper.normalize;

public class TerminalService {

    private final List<LinuxCommand> linuxCommands = new ArrayList<>();
    private final FileSystemContext terminalContext;

    public TerminalService() {
        this(FileSystems.getDefault());
    }

    public TerminalService(FileSystem aFileSystem) {
        String workingDirectory =
                workingDir(aFileSystem);

        terminalContext =
                new TerminalContext(aFileSystem, workingDirectory);

        linuxCommands.addAll(
                Arrays.asList(
                        new Pwd(),
                        new Cd(),
                        new MkDir()));
    }

    private String workingDir(FileSystem aFileSystem) {
        return normalize(aFileSystem.getPath("."));
    }

    public String processLinuxCommand(
            String rawCommand)
            throws IOException {

        String[] linuxCommandParts =
                rawCommand.split(" ");
        Optional<LinuxCommand> linuxCommand =
                linuxCommand(linuxCommandParts[0]);

        if (linuxCommand.isPresent()) {
            return linuxCommand.get().execute(
                    terminalContext,
                    linuxCommandArguments(linuxCommandParts));
        }

        return "Invalid Command!";
    }

    private Optional<LinuxCommand> linuxCommand(
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
