package com.wordpress.nprogramming.terminal;

import com.google.common.base.Strings;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.file.Path;
import java.nio.file.Paths;

public final class TerminalApp {

    private final TerminalService terminal = new TerminalService();

    public static void main(String[] args) throws IOException {
        TerminalApp terminalApp = new TerminalApp();
        terminalApp.run();
    }

    private void run() throws IOException {
        final BufferedReader reader =
                new BufferedReader(new InputStreamReader(System.in));

        prompt();
        String rawCommand = reader.readLine();

        while (!isExitCommand(rawCommand)) {
            processLinuxCommand(rawCommand);
            prompt();
            rawCommand = reader.readLine();
        }

        System.out.print("exit ...");
    }

    private boolean isExitCommand(String rawCommand) {

        return Strings.isNullOrEmpty(rawCommand)
                || rawCommand.equals("exit");
    }

    private void processLinuxCommand(String rawCommand) {
        final String result;

        try {
            result = terminal.processLinuxCommand(rawCommand);

            if (!Strings.isNullOrEmpty(result)) {
                System.out.println(result);
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private void prompt() {

        System.out.printf("[%s@%s %s]$ ",
                System.getProperty("user.name"),
                machineName(),
                currentDir());
    }

    private String machineName() {
        try {
            return InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
            return "Unknown";
        }
    }

    private String currentDir() {
        try {
            String workingDir =
                    terminal.processLinuxCommand("pwd");

            Path path = Paths.get(workingDir);

            if (path.getRoot().equals(path))
                return path.toString();

            return path.getFileName().toString();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return "";
    }
}
