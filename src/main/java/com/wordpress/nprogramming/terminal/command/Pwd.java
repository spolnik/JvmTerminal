package com.wordpress.nprogramming.terminal.command;

import com.wordpress.nprogramming.terminal.core.LinuxCommand;
import com.wordpress.nprogramming.terminal.core.FileSystemContext;
import com.wordpress.nprogramming.terminal.core.LinuxCommandName;

import static com.wordpress.nprogramming.terminal.core.LinuxCommandName.aLinuxCommandName;

public final class Pwd implements LinuxCommand {

    @Override
    public LinuxCommandName name() {
        return aLinuxCommandName("pwd");
    }

    @Override
    public String execute(
            FileSystemContext context, String... args) {

        return context.workingDir();
    }
}
