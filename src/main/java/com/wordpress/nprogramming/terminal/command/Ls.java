package com.wordpress.nprogramming.terminal.command;

import com.google.common.base.Joiner;
import com.wordpress.nprogramming.terminal.core.FileSystemContext;
import com.wordpress.nprogramming.terminal.core.LinuxCommand;
import com.wordpress.nprogramming.terminal.core.LinuxCommandName;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

import static com.wordpress.nprogramming.terminal.core.LinuxCommandName.asLinuxCommandName;

public class Ls implements LinuxCommand {
    @Override
    public LinuxCommandName name() {
        return asLinuxCommandName("ls");
    }

    @Override
    public String execute(FileSystemContext context, String... args) throws IOException {

        Path dirPath = args.length == 0
                ? context.workingDir()
                : context.asPath(args[0]);

        List<String> content =
                Files.list(dirPath)
                        .map(path -> path.getFileName().toString())
                        .collect(Collectors.toList());

        return Joiner.on(" ").join(content);
    }
}
