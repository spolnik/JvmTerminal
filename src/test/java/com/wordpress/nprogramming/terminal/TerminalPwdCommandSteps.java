package com.wordpress.nprogramming.terminal;

import com.wordpress.nprogramming.terminal.builders.FileSystemBuilder;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

public class TerminalPwdCommandSteps {

    private TerminalService terminal;
    private String result;

    @Given("a terminal service with working directory set to $path")
    public void givenWorkingDirectoryNameSetTo(String path) throws IOException {
        terminal = new TerminalService(
                FileSystemBuilder.create()
                        .withWorkingDirectorySetTo(path)
                        .build());
    }

    @When("I run pwd command to print working directory")
    public void whenIRunPwdCommandToPrintWorkingDirectory() {
        result = terminal.processLinuxCommand("pwd");
    }

    @Then("it should return string equals to $path")
    public void thenItShouldReturnPathEqualsTohomejacek(String path) {
        assertThat(result).isEqualToIgnoringCase(path);
    }
}
