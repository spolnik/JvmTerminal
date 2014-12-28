package com.wordpress.nprogramming.terminal.acceptance;

import com.wordpress.nprogramming.terminal.TerminalServiceDriver;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.junit.Test;

import java.io.IOException;

import static com.wordpress.nprogramming.terminal.acceptance.support.BehaviouralTestEmbedder.aBehaviouralTestRunner;
import static org.assertj.core.api.Assertions.assertThat;

public class MkDirCommandBehaviour {

    private TerminalServiceDriver driver;
    private String dirName;

    @Test
    public void mkDirCommandAcceptanceTests() throws Exception {
        aBehaviouralTestRunner()
                .usingStepsFrom(this)
                .withStory("mkdir_command_creates_new_directory.story")
                .run();
    }

    @Given("a terminal service with working directory set to $path")
    public void givenATerminalServiceWithWorkingDirectorySetTo(
            String path) {

        driver = new TerminalServiceDriver(path);
    }

    @When("I run mkdir $dirName command")
    public void whenIRunMkdirCommandWithDirNameEqualsTo(
            String aDirName)
            throws IOException {

        dirName = aDirName;
        driver.processLinuxCommand("mkdir " + aDirName);
    }

    @Then("changing directory to newly created one should success")
    public void thenChangingDirectoryToNewlyCreatedOneShouldSuccess()
            throws IOException {

        driver.processLinuxCommand("cd " + dirName);
    }

    @Then("pwd command should return string equal to $path")
    public void thenPwdCommandShouldReturnStringEqualTo(
            String path)
            throws IOException {

        assertThat(driver.processLinuxCommand("pwd"))
                .isEqualTo(path);
    }
}
