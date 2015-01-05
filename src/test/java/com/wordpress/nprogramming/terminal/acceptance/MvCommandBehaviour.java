package com.wordpress.nprogramming.terminal.acceptance;

import com.wordpress.nprogramming.terminal.TerminalServiceDriver;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.junit.Test;

import java.io.IOException;

import static com.wordpress.nprogramming.terminal.acceptance.support.BehaviouralTestEmbedder.aBehaviouralTestRunner;
import static org.assertj.core.api.Assertions.assertThat;

public class MvCommandBehaviour {

    private TerminalServiceDriver driver;

    @Test
    public void mvCommandAcceptanceTests() throws Exception {
        aBehaviouralTestRunner()
                .usingStepsFrom(this)
                .withStory("mv_command_moves_or_renames_files.story")
                .run();
    }

    @Given("a terminal service with working directory set initially to $path")
    public void givenATerminalServiceWithWorkingDirectorySetInitiallyTo(
            String path) {

        driver = new TerminalServiceDriver(path);
    }

    @Given("a file $fileName")
    public void givenAFile(String fileName) 
            throws IOException {
        
        driver.processLinuxCommand("touch " + fileName);
    }

    @Given("a directory $dirName")
    public void givenADirectory(String dirName)
            throws IOException {

        driver.processLinuxCommand("mkdir " + dirName);
    }

    @When("I run mv $parameters command")
    public void whenIRunMvCommandWith(String parameters) 
            throws IOException {
        
        driver.processLinuxCommand("mv " + parameters);
    }

    @Then("I am able to see that $movedFile file does not exist")
    public void thenIAmAbleToSeeThatMovedFileDoesNotExist(String movedFile) {
        
        assertThat(driver.fileExists(movedFile)).isFalse();
    }

    @Then("I am able to see that $newFile file exists")
    public void thenIAmAbleToSeeThatNewFileExists(String newFile) {
        
        assertThat(driver.fileExists(newFile)).isTrue();
    }
}
