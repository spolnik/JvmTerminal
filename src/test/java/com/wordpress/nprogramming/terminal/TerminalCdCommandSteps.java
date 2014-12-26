package com.wordpress.nprogramming.terminal;

import com.wordpress.nprogramming.terminal.builders.FileSystemBuilder;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

import static org.assertj.core.api.Assertions.assertThat;

public class TerminalCdCommandSteps {

    private TerminalService terminal;

    @Given("a terminal service with working directory set to $path")
    public void givenTerminalWhichHasWorkingDirectorySetTo(String path) {
        terminal =
                new TerminalService(
                        FileSystemBuilder.create()
                                .withWorkingDirectorySetTo(path)
                                .build());
    }

    @When("I run cd command with $directoryName directory name as argument")
    public void whenIRunCdCommandWithGo_hereDirectoryNameAsArgument(String directoryName) {
        terminal.cd(directoryName);
    }

    @Then("pwd command should return string equals to $path")
    public void thenPwdCommandShouldReturnStringEqualsTo(String path) {
        assertThat(terminal.pwd()).isEqualToIgnoringCase(path);
    }
}
