package com.wordpress.nprogramming.terminal.acceptance;

import com.wordpress.nprogramming.terminal.TerminalServiceDriver;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;

import static com.wordpress.nprogramming.terminal.acceptance.support.BehaviouralTestEmbedder.aBehaviouralTestRunner;
import static org.assertj.core.api.Assertions.assertThat;

public class LsCommandBehaviour {

    private TerminalServiceDriver driver;
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

        driver = new TerminalServiceDriver(path);
    }

    @Given("new directories created with names $names")
    public void givenNewDirectoriesCreatedWithNames(String names)
            throws IOException {

        for(String name : Arrays.asList(names.split(" "))) {
            driver.processLinuxCommand("mkdir " + name);
        }
    }

    @When("I run ls command")
    public void whenIRunLsCommand()
            throws IOException {

        result = driver.processLinuxCommand("ls");
    }

    @When("I run ls $dir command")
    public void whenIRunLsCommandWithDirSetTo(String dir)
            throws IOException {

        result = driver.processLinuxCommand("ls " + dir);
    }

    @Then("it displays output as $output")
    public void thenItDisplaysAllNewlyCreatedDirectoriesAs(String output) {
        assertThat(result).isEqualTo(output);
    }
}
