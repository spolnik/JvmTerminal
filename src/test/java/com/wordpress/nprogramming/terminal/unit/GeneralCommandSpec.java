package com.wordpress.nprogramming.terminal.unit;

import com.wordpress.nprogramming.terminal.core.FileSystemContext;
import com.wordpress.nprogramming.terminal.core.LinuxCommand;
import com.wordpress.nprogramming.terminal.core.LinuxCommandName;
import org.junit.Test;

import java.io.IOException;

import static com.wordpress.nprogramming.terminal.core.LinuxCommandName.asLinuxCommandName;
import static org.assertj.core.api.Assertions.assertThat;

public class GeneralCommandSpec {

    private final static String commandName = "command";

    @Test
    public void handleRightCommand() throws Exception {
        boolean canHandle =
                new GeneralCommandForTest()
                        .canHandle(commandName);

        assertThat(canHandle).isTrue();
    }

    @Test
    public void beCaseSensitiveRegardingHandlingRightCommand() {
        boolean canHandle =
                new GeneralCommandForTest()
                        .canHandle(commandName.toUpperCase());

        assertThat(canHandle).isFalse();
    }

    @Test
    public void notHandleOtherCommands() throws Exception {
        boolean canHandle =
                new GeneralCommandForTest()
                        .canHandle("pwd");

        assertThat(canHandle).isFalse();
    }

    @Test
    public void beNullProof() throws Exception {
        boolean canHandle =
                new GeneralCommandForTest()
                        .canHandle(null);

        assertThat(canHandle).isFalse();
    }

    public class GeneralCommandForTest implements LinuxCommand {

        @Override
        public LinuxCommandName name() {
            return asLinuxCommandName(commandName);
        }

        @Override
        public String execute(
                FileSystemContext context, String... args)
                throws IOException {

            return null;
        }
    }
}
