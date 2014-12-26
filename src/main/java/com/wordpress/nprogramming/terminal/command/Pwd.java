package com.wordpress.nprogramming.terminal.command;

import com.wordpress.nprogramming.terminal.core.LinuxCommandHandler;
import com.wordpress.nprogramming.terminal.core.TerminalContext;

public final class Pwd implements LinuxCommandHandler {

    @Override
    public String commandName() {
        return "pwd";
    }

    @Override
    public String process(TerminalContext context, String... args) {
        return context.getWorkingDirectory();
    }
}
