package com.wordpress.nprogramming.terminal.acceptance;

import com.wordpress.nprogramming.terminal.TerminalServiceDriver;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.junit.Test;

import java.io.IOException;

import static com.wordpress.nprogramming.terminal.acceptance.support.BehaviouralTestEmbedder.aBehaviouralTestRunner;
import static org.assertj.core.api.Assertions.assertThat;

public class CdCommandBehaviour {

    private TerminalServiceDriver driver;

    @Test
    public void cdCommandAcceptanceTests() throws Exception {
        aBehaviouralTestRunner()
                .usingStepsFrom(this)
                .withStory("cd_command_change_current_directory.story")
                .run();
    }

    @Given("a terminal service with working directory set initially to $path")
    public void givenTerminalWhichHasWorkingDirectorySetTo(String path)
            throws IOException {

        driver = new TerminalServiceDriver(path);
    }

    @When("I run cd $dirName command")
    public void whenIRunCdCommandWithDirectoryNameAs(
            String dirName)
            throws IOException {

        driver.makeSureThatDirectoryExists(dirName);
        driver.processLinuxCommand("cd " + dirName);
    }

    @Then("pwd command should return string equal to $path")
    public void thenPwdCommandShouldReturnStringEqualTo(
            String path)
            throws IOException {

        assertThat(driver.processLinuxCommand("pwd"))
                .isEqualToIgnoringCase(path);
    }
}
