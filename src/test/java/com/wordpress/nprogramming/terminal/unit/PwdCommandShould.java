package com.wordpress.nprogramming.terminal.unit;

import com.wordpress.nprogramming.terminal.command.Pwd;
import com.wordpress.nprogramming.terminal.core.FileSystemContext;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PwdCommandShould {

    private static final String WORKING_DIR = "/home/path";

    @Test
    public void handlePwdCommand() throws Exception {
        boolean canHandle = new Pwd().canHandle("pwd");
        assertThat(canHandle).isTrue();
    }

    @Test
    public void beCaseSensitiveRegardingHandlingPwdCommand() {
        boolean canHandle = new Pwd().canHandle("PWD");
        assertThat(canHandle).isFalse();
    }

    @Test
    public void notHandleOtherCommands() throws Exception {
        boolean canHandle = new Pwd().canHandle("cd");
        assertThat(canHandle).isFalse();
    }

    @Test
    public void beNullProof() throws Exception {
        boolean canHandle = new Pwd().canHandle(null);
        assertThat(canHandle).isFalse();
    }

    @Test
    public void printCorrectlyWorkingDirectory()
            throws Exception {

        FileSystemContext terminalContext = mock(FileSystemContext.class);

        when(terminalContext.workingDir())
                .thenReturn(WORKING_DIR);

        String result = new Pwd().execute(terminalContext);

        assertThat(result).isEqualTo(WORKING_DIR);
    }
}
