package com.wordpress.nprogramming.terminal.unit;

import com.wordpress.nprogramming.terminal.command.MkDir;
import com.wordpress.nprogramming.terminal.core.FileSystemContext;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class MkDirCommandSpec {

    @Mock
    private FileSystemContext fileSystemContext;
    
    @Test
    public void shouldCreateNewDirectoryIfItDoesNotExist()
            throws Exception {

        new MkDir().execute(fileSystemContext, "new_dir");

        verify(fileSystemContext).createDirectory("new_dir");
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwsInvalidArgumentExceptionIfNoArgumentIsPassed()
            throws Exception {

        new MkDir().execute(fileSystemContext);
    }
}
