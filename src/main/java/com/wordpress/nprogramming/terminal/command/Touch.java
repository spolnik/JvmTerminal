package com.wordpress.nprogramming.terminal.command;

import com.wordpress.nprogramming.terminal.core.FileSystemContext;
import com.wordpress.nprogramming.terminal.core.LinuxCommand;
import com.wordpress.nprogramming.terminal.core.LinuxCommandName;

import java.io.IOException;

import static com.google.common.base.Preconditions.checkArgument;
import static com.wordpress.nprogramming.terminal.core.LinuxCommandName.asLinuxCommandName;

public class Touch implements LinuxCommand {

    @Override
    public LinuxCommandName name() {
        return asLinuxCommandName("touch");
    }

    @Override
    public String execute(FileSystemContext context, String... args)
            throws IOException {

        checkArgument(args != null && args.length > 0,
                "touch: missing operand");

        assert args != null;
        String fileName = args[0];

        context.createFile(fileName);

        return "";
    }
}
