package com.wordpress.nprogramming.terminal.command;

import com.wordpress.nprogramming.terminal.core.FileSystemContext;
import com.wordpress.nprogramming.terminal.core.LinuxCommand;
import com.wordpress.nprogramming.terminal.core.LinuxCommandName;

import java.io.IOException;

import static com.google.common.base.Preconditions.checkArgument;
import static com.wordpress.nprogramming.terminal.core.LinuxCommandName.asLinuxCommandName;

public class Rm implements LinuxCommand {
    
    @Override
    public LinuxCommandName name() {
        return asLinuxCommandName("rm");
    }

    @Override
    public String execute(
            FileSystemContext context, String... args) 
            throws IOException {

        checkArgument(args != null && args.length > 0,
                "rm: missing operand");

        assert args != null;
        
        for (String fileName : args) {
            context.deleteFile(fileName);
        }
        
        return "";
    }
}
