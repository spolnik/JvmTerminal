package com.wordpress.nprogramming.terminal.acceptance;

import com.wordpress.nprogramming.terminal.TerminalServiceDriver;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.junit.Test;

import java.io.IOException;

import static com.wordpress.nprogramming.terminal.acceptance.support.BehaviouralTestEmbedder.aBehaviouralTestRunner;
import static org.assertj.core.api.Assertions.assertThat;

public class TouchCommandBehaviour {

    private TerminalServiceDriver driver;

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

        driver = new TerminalServiceDriver(path);
    }

    @When("I run touch $fileName command")
    public void whenIRunTouchCommandWithArgumentSetTo(String fileName)
            throws IOException {

        driver.processLinuxCommand("touch " + fileName);
    }

    @Then("I am able to see that $fullPath file is created")
    public void thenIAmAbleToSeeThatFileIsCreated(
            String fullPath) {

        assertThat(driver.fileExists(fullPath)).isTrue();
    }
}
