package com.wordpress.nprogramming.terminal.unit;

import com.wordpress.nprogramming.terminal.command.Pwd;
import com.wordpress.nprogramming.terminal.core.FileSystemContext;
import org.junit.Test;

import java.nio.file.Path;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PwdCommandSpec {

    private static final Path WORKING_DIR = Paths.get("/home/path");

    @Test
    public void printCorrectlyWorkingDirectory()
            throws Exception {

        FileSystemContext terminalContext = mock(FileSystemContext.class);

        when(terminalContext.workingDir())
                .thenReturn(WORKING_DIR);

        String result = new Pwd().execute(terminalContext);

        assertThat(result).isEqualTo(WORKING_DIR.toString());
    }
}
