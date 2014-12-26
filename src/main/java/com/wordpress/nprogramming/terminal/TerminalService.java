package com.wordpress.nprogramming.terminal;

import com.wordpress.nprogramming.terminal.command.Cd;
import com.wordpress.nprogramming.terminal.core.LinuxCommandHandler;
import com.wordpress.nprogramming.terminal.command.Pwd;
import com.wordpress.nprogramming.terminal.core.SimpleTerminalContext;
import com.wordpress.nprogramming.terminal.core.TerminalContext;

import java.io.FileNotFoundException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.NotDirectoryException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class TerminalService {

    private final List<LinuxCommandHandler> linuxCommandHandlers = new ArrayList<>();
    private final TerminalContext terminalContext;

    public TerminalService() {
        this(FileSystems.getDefault());
    }

    public TerminalService(FileSystem aFileSystem) {
        String workingDirectory = aFileSystem.getPath(".").toAbsolutePath().normalize().toString();

        terminalContext = new SimpleTerminalContext(aFileSystem, workingDirectory);

        linuxCommandHandlers.add(new Pwd());
        linuxCommandHandlers.add(new Cd());
    }

      public String processLinuxCommand(String linuxCommand) {

        String[] linuxCommandParts = linuxCommand.split(" ");
          Optional<LinuxCommandHandler> linuxCommandHandler = getLinuxCommandHandler(linuxCommandParts[0]);

          if (linuxCommandHandler.isPresent()) {
              try {
                  return linuxCommandHandler.get().process(
                          terminalContext,
                          extractLinuxCommandArguments(linuxCommandParts));
              } catch (FileNotFoundException | NotDirectoryException e) {
                  return e.getMessage();
              }
          }

        return "Invalid Command!";
    }

    private Optional<LinuxCommandHandler> getLinuxCommandHandler(String linuxCommandPart) {
        return linuxCommandHandlers.stream()
                .filter(handler -> handler.canHandle(linuxCommandPart))
                .findFirst();
    }

    private String[] extractLinuxCommandArguments(String[] linuxCommandParts) {
        return Arrays.copyOfRange(linuxCommandParts, 1, linuxCommandParts.length);
    }
}
