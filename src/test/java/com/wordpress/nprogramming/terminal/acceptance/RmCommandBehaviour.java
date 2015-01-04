package com.wordpress.nprogramming.terminal.acceptance;

import com.wordpress.nprogramming.terminal.TerminalServiceDriver;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.junit.Test;

import java.io.IOException;

import static com.wordpress.nprogramming.terminal.acceptance.support.BehaviouralTestEmbedder.aBehaviouralTestRunner;
import static org.assertj.core.api.Assertions.assertThat;

public class RmCommandBehaviour {

    private TerminalServiceDriver driver;

    @Test
    public void rmCommandAcceptanceTests() throws Exception {
        aBehaviouralTestRunner()
                .usingStepsFrom(this)
                .withStory("rm_command_removes_files.story")
                .run();
    }

    @Given("a terminal service with working directory set to $path")
    public void givenATerminalServiceWithWorkingDirectorySetTo(
            String path) {
        
        driver = new TerminalServiceDriver(path);
    }

    @Given("newly created file $fileName")
    public void givenNewlyCreatedFile(String fileName) 
            throws IOException {
        
        driver.processLinuxCommand("touch " + fileName);
    }

    @When("I run rm $files command")
    public void whenIRunRmCommand(String files) 
            throws IOException {
        
        driver.processLinuxCommand("rm " + files);
    }

    @Then("file $fileName is removed")
    public void thenFileIsRemoved(String fileName) {
        
        assertThat(driver.fileExists(fileName)).isFalse();
    }
}
