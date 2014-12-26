package com.wordpress.nprogramming.terminal;

import com.google.common.jimfs.Configuration;
import com.google.common.jimfs.Jimfs;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.Files;

import static org.assertj.core.api.Assertions.assertThat;

public class TerminalPwdCommandSteps {

    private TerminalService terminal;
    private String result;
    private String workingDirectoryPath;
    private FileSystem fileSystem;

    @Given("working directory name equals to $path")
    public void givenWorkingDirectoryNameEqualsTo(String path) throws IOException {
        workingDirectoryPath = path;

        fileSystem = Jimfs.newFileSystem(Configuration.unix());
        Files.createDirectory(fileSystem.getPath(path));
    }

    @Given("a terminal service")
    public void aTerminalService() {
        terminal = new TerminalService(fileSystem, workingDirectoryPath);
    }

    @When("I run pwd command to print working directory")
    public void whenIRunPwdCommandToPrintWorkingDirectory() {
        result = terminal.pwd();
    }

    @Then("it should return string equal to $path")
    public void thenItShouldReturnPathEqualTohomejacek(String path) {
        assertThat(result).isEqualToIgnoringCase(path);
    }
}
