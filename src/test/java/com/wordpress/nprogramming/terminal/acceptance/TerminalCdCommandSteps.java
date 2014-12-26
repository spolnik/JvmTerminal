package com.wordpress.nprogramming.terminal.acceptance;

import com.wordpress.nprogramming.terminal.TerminalService;
import com.wordpress.nprogramming.terminal.builders.FileSystemBuilder;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.Path;

import static org.assertj.core.api.Assertions.assertThat;

public class TerminalCdCommandSteps {

    private TerminalService terminal;
    private FileSystem fileSystem;

    @Given("a terminal service with working directory set initially to $path")
    public void givenTerminalWhichHasWorkingDirectorySetTo(String path) throws IOException {
        fileSystem = FileSystemBuilder.create()
                .withWorkingDirectorySetTo(path)
                .build();

        recreateDirectory(path);
        terminal = new TerminalService(fileSystem);
    }

    @When("I run cd $dirName command")
    public void whenIRunCdCommandWithDirectoryNameAs(String dirName) throws IOException {
        recreateDirectory(dirName);

        String response = terminal.processLinuxCommand("cd " + dirName);
        assertThat(response).isEmpty();
    }

    @Then("pwd command should return string equals to $path")
    public void thenPwdCommandShouldReturnStringEqualsTo(String path) {
        assertThat(terminal.processLinuxCommand("pwd")).isEqualToIgnoringCase(path);
    }

    private void recreateDirectory(String dirName) throws IOException {
        Path path = fileSystem.getPath(dirName).toAbsolutePath().normalize();
        fileSystem.provider().deleteIfExists(path);
        fileSystem.provider().createDirectory(path);
    }
}
