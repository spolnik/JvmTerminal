package com.wordpress.nprogramming.terminal.command;

import com.wordpress.nprogramming.terminal.core.FileSystemContext;
import com.wordpress.nprogramming.terminal.core.LinuxCommand;
import com.wordpress.nprogramming.terminal.core.LinuxCommandName;

import java.io.IOException;
import java.nio.file.Files;

import static com.google.common.base.Preconditions.checkArgument;
import static com.wordpress.nprogramming.terminal.core.LinuxCommandName.asLinuxCommandName;

public class Mv implements LinuxCommand {
    
    @Override
    public LinuxCommandName name() {
        return asLinuxCommandName("mv");
    }

    @Override
    public String execute(
            FileSystemContext context, String... args) 
            throws IOException {

        checkArgument(args != null && args.length > 1,
                "mv: missing operands");

        assert args != null;
        String source = args[0];
        String destination = args[1];

        Files.move(
                context.asPath(source),
                context.asPath(destination)
        );
        
        return "";
    }
}
