package com.wordpress.nprogramming.terminal.acceptance;

import com.wordpress.nprogramming.terminal.TerminalService;
import com.wordpress.nprogramming.terminal.builders.FileSystemBuilder;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.junit.Test;

import java.io.IOException;

import static com.wordpress.nprogramming.terminal.acceptance.support.BehaviouralTestEmbedder.aBehaviouralTestRunner;
import static org.assertj.core.api.Assertions.assertThat;

public class PwdCommandBehaviour {

    private TerminalService terminal;
    private String result;

    @Test
    public void pwdCommandAcceptanceTests() throws Exception {
        aBehaviouralTestRunner()
                .usingStepsFrom(this)
                .withStory("pwd_command_display_current_directory_path.story")
                .run();
    }

    @Given("a terminal service with working directory set to $path")
    public void givenWorkingDirectoryNameSetTo(
            String path)
            throws IOException {

        terminal = new TerminalService(
                FileSystemBuilder.create()
                        .withWorkingDirectorySetTo(path)
                        .build());
    }

    @When("I run pwd command to print working directory")
    public void whenIRunPwdCommandToPrintWorkingDirectory()
            throws IOException {

        result = terminal.processLinuxCommand("pwd");
    }

    @Then("it should return string equal to $path")
    public void thenItShouldReturnPathEqualTo(
            String path) {

        assertThat(result)
                .isEqualToIgnoringCase(path);
    }
}
