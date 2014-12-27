package com.wordpress.nprogramming.terminal.unit;

import com.wordpress.nprogramming.terminal.command.MkDir;
import com.wordpress.nprogramming.terminal.core.FileSystemContext;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class MkDirCommandSpec {

    @Test
    public void shouldCreateNewDirectoryIfItDoesNotExist()
            throws Exception {

        FileSystemContext fileSystemContext = mock(FileSystemContext.class);

        new MkDir().execute(fileSystemContext, "new_dir");

        verify(fileSystemContext).createDirectory("new_dir");
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwsInvalidArgumentExceptionIfNoArgumentIsPassed()
            throws Exception {

        FileSystemContext fileSystemContext = mock(FileSystemContext.class);

        new MkDir().execute(fileSystemContext);
    }
}
