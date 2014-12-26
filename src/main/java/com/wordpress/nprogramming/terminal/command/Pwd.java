package com.wordpress.nprogramming.terminal.command;

import com.wordpress.nprogramming.terminal.core.LinuxCommand;
import com.wordpress.nprogramming.terminal.core.FileSystemContext;

public final class Pwd implements LinuxCommand {

    @Override
    public String name() {
        return "pwd";
    }

    @Override
    public String execute(
            FileSystemContext context, String... args) {

        return context.workingDir();
    }
}
