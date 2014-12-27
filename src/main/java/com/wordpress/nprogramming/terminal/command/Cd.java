package com.wordpress.nprogramming.terminal.command;

import com.wordpress.nprogramming.terminal.core.FileSystemContext;
import com.wordpress.nprogramming.terminal.core.LinuxCommand;
import com.wordpress.nprogramming.terminal.core.LinuxCommandName;

import java.io.IOException;
import java.nio.file.Path;

import static com.wordpress.nprogramming.terminal.core.LinuxCommandName.asLinuxCommandName;

public class Cd implements LinuxCommand {

    @Override
    public LinuxCommandName name() {
        return asLinuxCommandName("cd");
    }

    @Override
    public String execute(
            FileSystemContext context, String... args)
            throws IOException {

        if (args.length == 0) {
            context.changeWorkingDir(context.homeDir());
        } else {
            Path workingDirectory = context.asPath(args[0]);
            context.changeWorkingDir(workingDirectory);
        }

        return "";
    }

}
