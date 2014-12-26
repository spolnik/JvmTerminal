package com.wordpress.nprogramming.terminal.acceptance;

import com.wordpress.nprogramming.terminal.TerminalService;
import com.wordpress.nprogramming.terminal.builders.FileSystemBuilder;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.Path;

import static com.wordpress.nprogramming.terminal.acceptance.support.BehaviouralTestEmbedder.aBehaviouralTestRunner;
import static org.assertj.core.api.Assertions.assertThat;

public class MkdirCommandBehaviour {

    private FileSystem fileSystem;
    private TerminalService terminal;
    private String dirName;

    @Test
    public void mkdirCommandAcceptanceTests() throws Exception {
        aBehaviouralTestRunner()
                .usingStepsFrom(this)
                .withStory("mkdir_command_creates_new_directory.story")
                .run();
    }

    @Given("a terminal service with working directory set to $path")
    public void givenATerminalServiceWithWorkingDirectorySetTo(String path) throws IOException {
        fileSystem = FileSystemBuilder.create()
                .withWorkingDirectorySetTo(path)
                .build();

        recreateDirectory(path);
        terminal = new TerminalService(fileSystem);
    }

    @When("I run mkdir $dirName command")
    public void whenIRunMkdirCommandWithDirNameEqualsTo(String dirName) throws IOException {
        this.dirName = dirName;
        terminal.processLinuxCommand("mkdir " + dirName);
    }

    @Then("changing directory to newly created one should success")
    public void thenChangingDirectoryToNewlyCreatedOneShouldSuccess() throws IOException {
        terminal.processLinuxCommand("cd " + dirName);
    }

    @Then("pwd command should return string equals to $path")
    public void thenPwdCommandShouldReturnStringEqualsTo(String path) throws IOException {
        assertThat(terminal.processLinuxCommand("pwd")).isEqualTo(path);
    }

    private void recreateDirectory(String dirName) throws IOException {
        Path path = fileSystem.getPath(dirName).toAbsolutePath().normalize();
        fileSystem.provider().deleteIfExists(path);
        fileSystem.provider().createDirectory(path);
    }
}
