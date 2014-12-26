package com.wordpress.nprogramming.terminal.command;

import com.wordpress.nprogramming.terminal.core.TerminalContext;
import com.wordpress.nprogramming.terminal.core.LinuxCommandHandler;

public final class Pwd implements LinuxCommandHandler {

    @Override
    public boolean canHandle(String linuxCommand) {
        return linuxCommand.equals("pwd");
    }

    @Override
    public String process(TerminalContext context, String... args) {
        return context.getWorkingDirectory();
    }
}
