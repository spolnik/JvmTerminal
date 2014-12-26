package com.wordpress.nprogramming.terminal.command;

import com.wordpress.nprogramming.terminal.core.LinuxCommand;
import com.wordpress.nprogramming.terminal.core.FileSystemContext;
import com.wordpress.nprogramming.terminal.core.LinuxCommandName;

import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.Path;

import static com.wordpress.nprogramming.terminal.core.LinuxCommandName.aLinuxCommandName;

public class MkDir implements LinuxCommand {

    @Override
    public LinuxCommandName name() {
        return aLinuxCommandName("mkdir");
    }

    @Override
    public String execute(
            FileSystemContext context, String... args)
            throws IOException {

        String dirName = args[0];
        FileSystem fs = context.fileSystem();

        Path path = fs.getPath(dirName)
                .toAbsolutePath().normalize();

        fs.provider().createDirectory(path);
        return "";
    }
}
