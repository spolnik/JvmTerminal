package com.wordpress.nprogramming.terminal.unit;

import com.wordpress.nprogramming.terminal.builders.FileSystemBuilder;
import com.wordpress.nprogramming.terminal.command.Cd;
import com.wordpress.nprogramming.terminal.core.FileSystemContext;
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
    public void setWorkingDirectoryToAbsolutePathIfItIsPassed()
            throws Exception {

        FileSystemContext terminalContext = terminalContextMock();

        new Cd().execute(terminalContext, WORKING_DIR);

        verify(terminalContext).changeWorkingDir(WORKING_DIR);
    }

    @Test
    public void setWorkingDirectoryToCompoundNameIfOnlyDirNameIsPassed()
            throws Exception {

        FileSystemContext terminalContext = terminalContextMock();

        new Cd().execute(terminalContext, "new_dir");

        verify(terminalContext).changeWorkingDir(WORKING_DIR + "/new_dir");
    }

    @Test
    public void setWorkingDirectoryToParentIfDoubleDotsArePassed()
            throws Exception {

        FileSystemContext terminalContext = terminalContextMock();

        new Cd().execute(terminalContext, "..");

        verify(terminalContext).changeWorkingDir("/home");
    }

    @Test
    public void setWorkingDirectoryToRootIfDoubleDotsArePassedManyTimes()
            throws Exception {

        FileSystemContext terminalContext = terminalContextMock();

        new Cd().execute(terminalContext, "../../../..");

        verify(terminalContext).changeWorkingDir("/");
    }

    @Test
    public void setWorkingDirectoryToTheSameDirIfDotIsPassed()
            throws Exception {

        FileSystemContext terminalContext = terminalContextMock();

        new Cd().execute(terminalContext, ".");

        verify(terminalContext).changeWorkingDir(WORKING_DIR);
    }

    @Test
    public void setWorkingDirectoryToHomeDirIfNoArgumentsArePassed()
            throws Exception {

        FileSystemContext terminalContext = terminalContextMock();
        when(terminalContext.homeDir()).thenReturn(WORKING_DIR);

        new Cd().execute(terminalContext);

        verify(terminalContext).changeWorkingDir(WORKING_DIR);
    }

    private FileSystemContext terminalContextMock() {
        FileSystemContext terminalContext = mock(FileSystemContext.class);

        when(terminalContext.workingDir())
                .thenReturn(WORKING_DIR);

        when(terminalContext.fileSystem())
                .thenReturn(FileSystemBuilder.create()
                        .withWorkingDirectorySetTo(WORKING_DIR)
                        .build());

        return terminalContext;
    }
}
