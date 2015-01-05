package com.wordpress.nprogramming.terminal.unit;

import com.wordpress.nprogramming.terminal.command.RmDir;
import com.wordpress.nprogramming.terminal.core.FileSystemContext;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class RmDirCommandSpec {

    @Mock
    private FileSystemContext fileSystemContext;

    @Test
    public void removesEmptyDirectory() throws Exception {

        new RmDir().execute(fileSystemContext, "empty_dir");
        
        verify(fileSystemContext).removeDirectory("empty_dir");
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwsIllegalArgumentIfNoParametersArePassed() 
            throws Exception {

        new RmDir().execute(fileSystemContext);
    }
}
