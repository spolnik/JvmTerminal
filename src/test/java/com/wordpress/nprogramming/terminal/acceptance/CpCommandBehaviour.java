package com.wordpress.nprogramming.terminal.acceptance;

import com.wordpress.nprogramming.terminal.TerminalServiceDriver;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Pending;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.junit.Test;

import java.io.IOException;

import static com.wordpress.nprogramming.terminal.acceptance.support.BehaviouralTestEmbedder.aBehaviouralTestRunner;
import static org.assertj.core.api.Assertions.assertThat;

public class CpCommandBehaviour {

    private TerminalServiceDriver driver;

    @Test
    public void cpCommandAcceptanceTests() throws Exception {
        aBehaviouralTestRunner()
                .usingStepsFrom(this)
                .withStory("cp_command_copies_file_to_specified_location.story")
                .run();
    }

    @Given("a terminal service with working directory set initially to $path")
    public void givenATerminalServiceWithWorkingDirectorySetInitiallyTo(
            String path) {

        driver = new TerminalServiceDriver(path);
    }

    @Given("a file $fileName")
    public void givenAFile(String fileName) throws IOException {
        driver.processLinuxCommand("touch " + fileName);
    }

    @Given("a directory $directoryPath")
    public void givenADirectory(String directoryPath)
            throws IOException {
        
        driver.processLinuxCommand("mkdir " + directoryPath);
    }
    
    @When("I run cp $source $destination command")
    public void whenIRunCpCommand(String source, String destination) 
            throws IOException {
        
        driver.processLinuxCommand("cp " + source + " " + destination);
    }

    @Then("I am able to see that $fileName file exists")
    @Pending
    public void thenIAmAbleToSeeThatBothFilesExists(
            String fileName) {
        
        assertThat(driver.fileExists(fileName)).isTrue();
    }
}
