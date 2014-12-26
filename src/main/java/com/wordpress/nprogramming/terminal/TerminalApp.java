package com.wordpress.nprogramming.terminal;

import com.google.common.base.Strings;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Paths;

public final class TerminalApp {

    private final TerminalService terminal = new TerminalService();

    public static void main(String[] args) throws IOException {
        TerminalApp terminalApp = new TerminalApp();
        terminalApp.run();
    }

    private void run() throws IOException {
        final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        prompt();
        String rawCommand = reader.readLine();

        while (!Strings.isNullOrEmpty(rawCommand)) {
            process(rawCommand);
            prompt();
            rawCommand = reader.readLine();
        }

        System.out.print("Console is closing... Press <enter> ...");
        reader.readLine();
    }

    private void process(String rawCommand) {
        final String result = terminal.processLinuxCommand(rawCommand);

        if (!Strings.isNullOrEmpty(result))
            System.out.println(result);
    }

    private void prompt() {
        System.out.printf("[user@localhost %s]$ ", currentDir());
    }

    private String currentDir() {
        String workingDir = terminal.processLinuxCommand("pwd");
        return Paths.get(workingDir).getFileName().toString();
    }
}
