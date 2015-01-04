package com.wordpress.nprogramming.terminal;

import com.google.common.base.Strings;
import com.google.inject.Guice;
import com.google.inject.Injector;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public final class TerminalApp {

    private final TerminalService terminal;
    private final Scanner scanner;

    public TerminalApp() {
        
        this.scanner = new Scanner(System.in);
        Injector injector = Guice.createInjector(new TerminalAppModule());

        terminal = injector.getInstance(TerminalService.class);
    }

    public static void main(String[] args) throws IOException {
        TerminalApp terminalApp = new TerminalApp();
        terminalApp.run();
    }

    private void run() throws IOException {

        prompt();
        String rawCommand = scanner.nextLine();

        while (!isExitCommand(rawCommand)) {
            processLinuxCommand(rawCommand);
            prompt();
            rawCommand = scanner.nextLine();
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
