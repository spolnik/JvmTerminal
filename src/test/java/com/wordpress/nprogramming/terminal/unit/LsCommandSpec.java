package com.wordpress.nprogramming.terminal.unit;

import com.wordpress.nprogramming.terminal.command.Ls;
import com.wordpress.nprogramming.terminal.core.FileSystemContext;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class LsCommandSpec {

    @Mock
    private FileSystemContext fileSystemContext;
    
    @Test
    public void returnsWorkingDirectoryContentIfNoArgumentsPassed() 
            throws Exception {

        Stream<Path> listedFiles = 
                Arrays.asList("one", "two", "three").stream()
                        .map(name -> Paths.get(name));
        
        given(fileSystemContext.listFiles()).willReturn(listedFiles);
        
        String content = new Ls().execute(fileSystemContext);
        
        assertThat(content).isEqualTo("one two three");
    }

    @Test
    public void returnsGivenDirectoryContent() 
            throws Exception {

        Stream<Path> listedFiles =
                Arrays.asList("four", "five").stream()
                        .map(name -> Paths.get(name));

        given(fileSystemContext.listFiles("dirOne")).willReturn(listedFiles);

        String content = new Ls().execute(fileSystemContext, "dirOne");

        assertThat(content).isEqualTo("four five");
    }
}
