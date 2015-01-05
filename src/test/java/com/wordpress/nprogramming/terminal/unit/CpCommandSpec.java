package com.wordpress.nprogramming.terminal.unit;

import com.wordpress.nprogramming.terminal.command.Cp;
import com.wordpress.nprogramming.terminal.core.FileSystemContext;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class CpCommandSpec {
    
    @Mock
    private FileSystemContext fileSystemContext;

    @Test
    public void copiesFileToNewLocation() 
            throws Exception {
        
        new Cp().execute(fileSystemContext, "original", "new");
        
        verify(fileSystemContext).copyFile("original", "new");
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwsIllegalArgumentIfNotAllParametersArePassed() 
            throws Exception {

        new Cp().execute(fileSystemContext, "original");
    }
}
