package com.wordpress.nprogramming.terminal.command;

import com.wordpress.nprogramming.terminal.core.FileSystemContext;
import com.wordpress.nprogramming.terminal.core.LinuxCommand;
import com.wordpress.nprogramming.terminal.core.LinuxCommandName;

import java.io.IOException;
import java.nio.file.Files;

import static com.google.common.base.Preconditions.checkArgument;
import static com.wordpress.nprogramming.terminal.core.LinuxCommandName.asLinuxCommandName;

public final class Cp implements LinuxCommand {
    
    @Override
    public LinuxCommandName name() {
        return asLinuxCommandName("cp");
    }

    @Override
    public String execute(
            FileSystemContext context, String... args) 
            throws IOException {

        checkArgument(args != null && args.length > 1,
                "cp: missing operands");

        assert args != null;

        String sourceFileName = args[0];
        String destinationFileName = args[1];

        Files.copy(
                context.asPath(sourceFileName), 
                context.asPath(destinationFileName));
        
        return "";
    }
}
