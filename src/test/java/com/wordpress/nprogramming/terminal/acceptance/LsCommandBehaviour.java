package com.wordpress.nprogramming.terminal.acceptance;

import com.wordpress.nprogramming.terminal.TerminalService;
import com.wordpress.nprogramming.terminal.builders.FileSystemBuilder;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.FileSystem;
import java.util.Arrays;

import static com.wordpress.nprogramming.terminal.acceptance.support.BehaviouralTestEmbedder.aBehaviouralTestRunner;
import static org.assertj.core.api.Assertions.assertThat;

public class LsCommandBehaviour {

    private TerminalService terminal;
    private String result;

    @Test
    public void lsCommandAcceptanceTests() throws Exception {
        aBehaviouralTestRunner()
                .usingStepsFrom(this)
                .withStory("ls_command_displays_current_directory_content.story")
                .run();
    }

    @Given("a terminal service with working directory set to $path")
    public void givenATerminalServiceWithWorkingDirectorySetTo(
            String path) {

        final FileSystem fileSystem = FileSystemBuilder.create()
                .withWorkingDirectorySetTo(path)
                .build();

        terminal = new TerminalService(fileSystem);
    }

    @Given("new three new directories created with names $names")
    public void givenNewThreeNewDirectoriesCreatedWithNames(String names)
            throws IOException {

        for(String name : Arrays.asList(names.split(" "))) {
            terminal.processLinuxCommand("mkdir " + name);
        }
    }

    @When("I run ls command")
    public void whenIRunLsCommand()
            throws IOException {

        result = terminal.processLinuxCommand("ls");
    }

    @Then("it displays all newly created directories as $output")
    public void thenItDisplaysAllNewlyCreatedDirectoriesAs(String output) {
        assertThat(result).isEqualTo(output);
    }
}
