package com.wordpress.nprogramming.terminal.unit;

import com.wordpress.nprogramming.terminal.builders.FileSystemBuilder;
import com.wordpress.nprogramming.terminal.command.Ls;
import com.wordpress.nprogramming.terminal.core.FileSystemContext;
import com.wordpress.nprogramming.terminal.core.FileSystemService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class LsCommandSpec {

    private static final String WORKING_DIR = "/home/path";
    
    @Test
    public void returnsWorkingDirectoryContentIfNoArgumentsPassed() 
            throws Exception {

        FileSystemContext fileSystemContext = fileSystemContext();
        
        String content = new Ls().execute(fileSystemContext);
        
        assertThat(content).isEqualTo("dirOne one three two");
    }

    @Test
    public void returnsGivenDirectoryContent() 
            throws Exception {

        FileSystemContext fileSystemContext = fileSystemContext();

        String content = new Ls().execute(fileSystemContext, "dirOne");

        assertThat(content).isEqualTo("five four");
    }

    @Test(expected = NoSuchFileException.class)
    public void throwsIllegalArgumentIfNonExistingDirectoryPassed() 
            throws Exception {

        FileSystemContext fileSystemContext = fileSystemContext();

        new Ls().execute(fileSystemContext, "nonExisting");
    }
    
    private FileSystemContext fileSystemContext() throws IOException {

        FileSystem fileSystem = FileSystemBuilder.create()
                .withWorkingDirectorySetTo(WORKING_DIR)
                .build();

        prepareFileSystem(fileSystem);
        
        return new FileSystemService(fileSystem, WORKING_DIR);
    }

    private void prepareFileSystem(FileSystem fileSystem) 
            throws IOException {
        
        createFile(fileSystem, "one");
        createFile(fileSystem, "two");
        createFile(fileSystem, "three");

        createDirectory(fileSystem, "dirOne");
        createFile(fileSystem, "dirOne/four");
        createFile(fileSystem, "dirOne/five");
    }

    private void createDirectory(FileSystem fileSystem, String dirName) 
            throws IOException {
        
        Path path = fileSystem.getPath(WORKING_DIR, dirName);
        fileSystem.provider().createDirectory(path);
    }

    private void createFile(FileSystem fileSystem, String fileName) 
            throws IOException {
        
        Path path = fileSystem.getPath(WORKING_DIR, fileName);
        Files.createFile(path);
    }
}
