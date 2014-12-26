package com.wordpress.nprogramming.terminal;

import com.wordpress.nprogramming.terminal.builders.FileSystemBuilder;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

import static org.assertj.core.api.Assertions.assertThat;

public class TerminalCdCommandSteps {

    private TerminalService terminal;

    @Given("a terminal service with working directory set initially to $path")
    public void givenTerminalWhichHasWorkingDirectorySetTo(String path) {
        terminal =
                new TerminalService(
                        FileSystemBuilder.create()
                                .withWorkingDirectorySetTo(path)
                                .build());
    }

    @When("I run $rawCommand command")
    public void whenIRunCdCommandWithDirectoryNameAs(String rawCommand) {
        terminal.processLinuxCommand(rawCommand);
    }

    @Then("pwd command should return string equals to $path")
    public void thenPwdCommandShouldReturnStringEqualsTo(String path) {
        assertThat(terminal.processLinuxCommand("pwd")).isEqualToIgnoringCase(path);
    }
}
