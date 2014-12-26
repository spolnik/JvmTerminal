package com.wordpress.nprogramming.terminal.command;

import com.wordpress.nprogramming.terminal.core.LinuxCommand;
import com.wordpress.nprogramming.terminal.core.FileSystemContext;
import com.wordpress.nprogramming.terminal.core.LinuxCommandName;

import java.io.IOException;
import java.nio.file.Path;

import static com.wordpress.nprogramming.terminal.core.LinuxCommandName.aLinuxCommandName;
import static com.wordpress.nprogramming.terminal.utils.PathHelper.normalize;

public class Cd implements LinuxCommand {

    @Override
    public LinuxCommandName name() {
        return aLinuxCommandName("cd");
    }

    @Override
    public String execute(
            FileSystemContext context, String... args)
            throws IOException {

        if (args.length == 0) {
            context.changeWorkingDir(context.homeDir());
            return "";
        }

        String workingDirectory = newWorkingDir(context, args[0]);

        context.changeWorkingDir(workingDirectory);
        return "";
    }

    private String newWorkingDir(
            FileSystemContext context, String directoryName) {

        return directoryName.startsWith(context.fileSystem().getSeparator())
                ? directoryName
                : buildNewWorkingDirectoryPath(context, directoryName);
    }

    private String buildNewWorkingDirectoryPath(
            FileSystemContext context, String path) {

        Path workingDirectoryPath =
                context.fileSystem().getPath(context.workingDir());

        return normalize(workingDirectoryPath.resolve(path));
    }
}
