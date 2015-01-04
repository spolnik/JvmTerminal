package com.wordpress.nprogramming.terminal.unit;

import com.wordpress.nprogramming.terminal.command.Pwd;
import com.wordpress.nprogramming.terminal.core.FileSystemContext;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.nio.file.Path;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class PwdCommandSpec {

    private static final Path WORKING_DIR = Paths.get("/home/path");

    @Mock
    private FileSystemContext fileSystemContext;
    
    @Test
    public void printCorrectlyWorkingDirectory()
            throws Exception {

        given(fileSystemContext.workingDir()).willReturn(WORKING_DIR);

        String result = new Pwd().execute(fileSystemContext);

        assertThat(result).isEqualTo(WORKING_DIR.toString());
    }
}
