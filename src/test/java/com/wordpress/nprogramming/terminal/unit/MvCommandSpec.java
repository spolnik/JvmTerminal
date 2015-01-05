package com.wordpress.nprogramming.terminal.unit;

import com.wordpress.nprogramming.terminal.command.Mv;
import com.wordpress.nprogramming.terminal.core.FileSystemContext;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class MvCommandSpec {

    @Mock
    private FileSystemContext fileSystemContext;
    
    @Test
    public void renamesFileInTheSameLocation() 
            throws Exception {

        String source = "new_file";
        String destination = "renamed_file";
        
        new Mv().execute(fileSystemContext, source, destination);

        verify(fileSystemContext).moveFile(source, destination);
    }

    @Test
    public void movesFileInDifferentLocations() 
            throws Exception {

        String source = "new_file";
        String destination = "new_dir/moved_file";

        new Mv().execute(fileSystemContext, source, destination);

        verify(fileSystemContext).moveFile(source, destination);
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwsIllegalArgumentIfLessThanTwoParametersArePassed() 
            throws Exception {
        
        new Mv().execute(fileSystemContext, "any");
    }
}
