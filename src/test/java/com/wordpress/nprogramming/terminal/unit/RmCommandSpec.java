package com.wordpress.nprogramming.terminal.unit;

import com.wordpress.nprogramming.terminal.command.Rm;
import com.wordpress.nprogramming.terminal.core.FileSystemContext;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class RmCommandSpec {

    @Mock
    private FileSystemContext fileSystemContext;

    @Test
    public void removesAllGivenFiles() throws Exception {

        new Rm().execute(fileSystemContext, "file_to_remove", "another_file_to_remove");
        
        verify(fileSystemContext).deleteFile("file_to_remove");
        verify(fileSystemContext).deleteFile("another_file_to_remove");
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwsIllegalArgumentIfNoArgumentsPassed() 
            throws Exception {

        new Rm().execute(fileSystemContext);
    }
}
