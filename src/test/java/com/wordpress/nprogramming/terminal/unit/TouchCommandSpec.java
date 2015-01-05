package com.wordpress.nprogramming.terminal.unit;

import com.wordpress.nprogramming.terminal.command.Touch;
import com.wordpress.nprogramming.terminal.core.FileSystemContext;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class TouchCommandSpec {

    @Mock
    private FileSystemContext fileSystemContext;

    @Test
    public void createsNewFile() throws Exception {

        String fileName = "new_file";
        
        new Touch().execute(fileSystemContext, fileName);
        
        verify(fileSystemContext).createFile(fileName);
    }
}
