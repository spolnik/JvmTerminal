package com.wordpress.nprogramming.terminal.acceptance;

import com.wordpress.nprogramming.terminal.TerminalServiceDriver;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.junit.Test;

import java.io.IOException;

import static com.wordpress.nprogramming.terminal.acceptance.support.BehaviouralTestEmbedder.aBehaviouralTestRunner;
import static org.assertj.core.api.Assertions.assertThat;

public class RmDirCommandBehaviour {

    private TerminalServiceDriver driver;

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

        driver = new TerminalServiceDriver(path);
    }

    @Given("newly created $dir directory")
    public void givenNewlyCreatedDirectoryWithName(String dir)
            throws IOException {

        driver.processLinuxCommand("mkdir " + dir);
    }

    @When("I run rmdir $dir command")
    public void whenIRunRmdirCommandWithArgument(String dir)
            throws IOException {

        driver.processLinuxCommand("rmdir " + dir);
    }

    @Then("I am able to confirm $path directory is removed")
    public void thenIAmAbleToConfirmThatDirectoryIsRemoved(
            String path) {

        assertThat(driver.fileExists(path)).isFalse();
    }
}
