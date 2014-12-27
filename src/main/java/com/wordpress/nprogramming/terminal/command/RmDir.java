package com.wordpress.nprogramming.terminal.command;

import com.wordpress.nprogramming.terminal.core.FileSystemContext;
import com.wordpress.nprogramming.terminal.core.LinuxCommand;
import com.wordpress.nprogramming.terminal.core.LinuxCommandName;

import java.io.IOException;
import java.nio.file.DirectoryNotEmptyException;

import static com.google.common.base.Preconditions.checkArgument;
import static com.wordpress.nprogramming.terminal.core.LinuxCommandName.asLinuxCommandName;

public final class RmDir implements LinuxCommand {

    @Override
    public LinuxCommandName name() {
        return asLinuxCommandName("rmdir");
    }

    @Override
    public String execute(FileSystemContext context, String... args)
            throws IOException {

        checkArgument(args != null && args.length > 0,
                "rmdir: missing operand");

        assert args != null;
        String dirName = args[0];

        try {
            context.removeDirectory(dirName);
        } catch (DirectoryNotEmptyException e) {
            return throwDirectoryNotEmptyException(dirName);
        }

        return "";
    }

    private String throwDirectoryNotEmptyException(String dirName)
            throws DirectoryNotEmptyException {

        String errorMessage =
                String.format(
                        "rmdir: failed to remove ‘%s’: Directory not empty",
                        dirName);

        throw new DirectoryNotEmptyException(errorMessage);
    }
}
