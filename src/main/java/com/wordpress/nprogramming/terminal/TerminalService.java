package com.wordpress.nprogramming.terminal;

import com.google.common.base.Strings;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;

public class TerminalService {
    private final FileSystem fileSystem;
    private String workingDirectory;

    public TerminalService() {
        this(FileSystems.getDefault());
    }

    public TerminalService(FileSystem aFileSystem) {
        fileSystem = aFileSystem;
        workingDirectory = aFileSystem.getPath(".").toAbsolutePath().normalize().toString();
    }

    public String pwd() {
        return workingDirectory;
    }

    public void cd(String directoryName) {
        workingDirectory =
                directoryName.startsWith("\\")
                        ? directoryName
                        : buildPath(directoryName);
    }

    private String buildPath(String path) {
        Path workingDirectoryPath = fileSystem.getPath(workingDirectory);
        Path resolvedPath = workingDirectoryPath.resolve(path).normalize();
        return resolvedPath.toAbsolutePath().toString();
    }

    public static void main(String[] args) throws IOException {
        final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        prompt();
        String rawCommand = reader.readLine();

        final TerminalService terminal = new TerminalService();

        while (!Strings.isNullOrEmpty(rawCommand)) {
            final String result = processCommand(terminal, rawCommand);

            if (!Strings.isNullOrEmpty(result))
                System.out.println(result);

            prompt();
            rawCommand = reader.readLine();
        }

        System.out.print("Console is closing... Press <enter> ...");
        reader.readLine();
    }

    private static String processCommand(TerminalService terminal, String rawCommand) {
        if (rawCommand.startsWith("pwd"))
            return terminal.pwd();
        else if (rawCommand.startsWith("cd")) {
            String[] args = rawCommand.split(" ");
            terminal.cd(args[1]);
        }

        return "";
    }

    private static void prompt() {
        System.out.print("> ");
    }
}
