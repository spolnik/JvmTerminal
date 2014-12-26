package com.wordpress.nprogramming.terminal.unit;

import com.wordpress.nprogramming.terminal.builders.FileSystemBuilder;
import com.wordpress.nprogramming.terminal.command.Cd;
import com.wordpress.nprogramming.terminal.core.TerminalContext;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class CdCommandShould {

    private static final String WORKING_DIR = "/home/path";

    @Test
    public void handleCdCommand() throws Exception {
        boolean canHandle = new Cd().canHandle("cd");
        assertThat(canHandle).isTrue();
    }

    @Test
    public void beCaseSensitiveRegardingHandlingCdCommand() {
        boolean canHandle = new Cd().canHandle("CD");
        assertThat(canHandle).isFalse();
    }

    @Test
    public void notHandleOtherCommands() throws Exception {
        boolean canHandle = new Cd().canHandle("pwd");
        assertThat(canHandle).isFalse();
    }

    @Test
    public void beNullProof() throws Exception {
        boolean canHandle = new Cd().canHandle(null);
        assertThat(canHandle).isFalse();
    }

    @Test
    public void setWorkingDirectoryToAbsolutePathIfItIsPassed() throws Exception {
        TerminalContext terminalContext = getTerminalContextMockWithFileSystemSetup();

        new Cd().process(terminalContext, WORKING_DIR);

        verify(terminalContext).changeWorkingDirectory(WORKING_DIR);
    }

    @Test
    public void setWorkingDirectoryToCompoundNameIfOnlyDirNameIsPassed() throws Exception {
        TerminalContext terminalContext = getTerminalContextMockWithFileSystemSetup();

        new Cd().process(terminalContext, "new_dir");

        verify(terminalContext).changeWorkingDirectory(WORKING_DIR + "/new_dir");
    }

    @Test
    public void setWorkingDirectoryToParentIfDoubleDotsArePassed() throws Exception {
        TerminalContext terminalContext = getTerminalContextMockWithFileSystemSetup();

        new Cd().process(terminalContext, "..");

        verify(terminalContext).changeWorkingDirectory("/home");
    }

    @Test
    public void setWorkingDirectoryToRootIfDoubleDotsArePassedManyTimes() throws Exception {
        TerminalContext terminalContext = getTerminalContextMockWithFileSystemSetup();

        new Cd().process(terminalContext, "../../../..");

        verify(terminalContext).changeWorkingDirectory("/");
    }

    @Test
    public void setWorkingDirectoryToTheSameDirIfDotIsPassed() throws Exception {
        TerminalContext terminalContext = getTerminalContextMockWithFileSystemSetup();

        new Cd().process(terminalContext, ".");

        verify(terminalContext).changeWorkingDirectory(WORKING_DIR);
    }

    @Test
    public void setWorkingDirectoryToHomeDirIfNoArgumentsArePassed() throws Exception {
        TerminalContext terminalContext = getTerminalContextMockWithFileSystemSetup();
        when(terminalContext.getHomeDir()).thenReturn(WORKING_DIR);

        new Cd().process(terminalContext);

        verify(terminalContext).changeWorkingDirectory(WORKING_DIR);
    }

    private TerminalContext getTerminalContextMockWithFileSystemSetup() {
        TerminalContext terminalContext = mock(TerminalContext.class);

        when(terminalContext.getWorkingDirectory())
                .thenReturn(WORKING_DIR);

        when(terminalContext.getFileSystem())
                .thenReturn(FileSystemBuilder.create()
                        .withWorkingDirectorySetTo(WORKING_DIR)
                        .build());

        return terminalContext;
    }
}
