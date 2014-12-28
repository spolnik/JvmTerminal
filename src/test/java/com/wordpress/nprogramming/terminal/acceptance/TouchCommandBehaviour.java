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

public class TouchCommandBehaviour {

    private TerminalService terminal;
    private FileSystem fileSystem;

    @Test
    public void touchCommandAcceptanceTests() throws Exception {
        aBehaviouralTestRunner()
                .usingStepsFrom(this)
                .withStory("touch_command_creates_new_empty_file.story")
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

    @When("I run touch $fileName command")
    public void whenIRunTouchCommandWithArgumentSetTo(String fileName)
            throws IOException {

        terminal.processLinuxCommand("touch " + fileName);
    }

    @Then("I am able to see that $fullPath file is created")
    public void thenIAmAbleToSeeThatFileIsCreatedWithName(
            String fullPath) {

        Path filePath = fileSystem.getPath(fullPath);
        assertThat(Files.exists(filePath)).isTrue();
    }
}
