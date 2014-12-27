package com.wordpress.nprogramming.terminal.acceptance;

import com.wordpress.nprogramming.terminal.TerminalService;
import com.wordpress.nprogramming.terminal.builders.FileSystemBuilder;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.Files;
import java.nio.file.Path;

import static com.wordpress.nprogramming.terminal.acceptance.support.BehaviouralTestEmbedder.aBehaviouralTestRunner;
import static org.assertj.core.api.Assertions.assertThat;

public class RmDirCommandBehaviour {

    private TerminalService terminal;
    private FileSystem fileSystem;

    @Test
    public void rmDirCommandAcceptanceTests()
            throws Exception {

        aBehaviouralTestRunner()
                .usingStepsFrom(this)
                .withStory("rmdir_command_removes_empty_directory.story")
                .run();
    }

    @Given("a terminal service with working directory set to $path")
    public void givenATerminalServiceWithWorkingDirectorySetTo(
            String path) {

        fileSystem = FileSystemBuilder.create()
                .withWorkingDirectorySetTo(path)
                .build();

        terminal = new TerminalService(fileSystem);
    }

    @Given("newly created $dir directory")
    public void givenNewlyCreatedDirectoryWithName(String dir)
            throws IOException {

        terminal.processLinuxCommand("mkdir " + dir);
    }

    @When("I run rmdir $dir command")
    public void whenIRunRmdirCommandWithArgument(String dir)
            throws IOException {

        terminal.processLinuxCommand("rmdir " + dir);
    }

    @Then("I am able to confirm $path directory is removed")
    public void thenIAmAbleToConfirmThatDirectoryIsRemoved(
            String path) {

        Path removedDirPath = fileSystem.getPath(path);

        assertThat(Files.exists(removedDirPath)).isFalse();
    }
}
