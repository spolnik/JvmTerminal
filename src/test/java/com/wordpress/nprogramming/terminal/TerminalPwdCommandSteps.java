package com.wordpress.nprogramming.terminal;

import com.google.common.jimfs.Configuration;
import com.google.common.jimfs.Jimfs;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

import java.io.IOException;
import java.nio.file.FileSystem;

import static org.assertj.core.api.Assertions.assertThat;

public class TerminalPwdCommandSteps {

    private TerminalService terminal;
    private String result;

    @Given("a terminal service with working directory set to $path")
    public void givenWorkingDirectoryNameSetTo(String path) throws IOException {
        FileSystem fileSystem =
                Jimfs.newFileSystem(
                        Configuration.unix().toBuilder()
                                .setWorkingDirectory(path).build());

        terminal = new TerminalService(fileSystem);
    }

    @When("I run pwd command to print working directory")
    public void whenIRunPwdCommandToPrintWorkingDirectory() {
        result = terminal.pwd();
    }

    @Then("it should return string equals to $path")
    public void thenItShouldReturnPathEqualsTohomejacek(String path) {
        assertThat(result).isEqualToIgnoringCase(path);
    }
}
