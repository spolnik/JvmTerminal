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
import static com.wordpress.nprogramming.terminal.utils.PathHelper.normalize;
import static org.assertj.core.api.Assertions.assertThat;

public class CdCommandBehaviour {

    private TerminalService terminal;
    private FileSystem fileSystem;

    @Test
    public void cdCommandAcceptanceTests() throws Exception {
        aBehaviouralTestRunner()
                .usingStepsFrom(this)
                .withStory("cd_command_change_current_directory.story")
                .run();
    }

    @Given("a terminal service with working directory set initially to $path")
    public void givenTerminalWhichHasWorkingDirectorySetTo(String path)
            throws IOException {

        fileSystem = FileSystemBuilder.create()
                .withWorkingDirectorySetTo(path)
                .build();

        terminal = new TerminalService(fileSystem);
    }

    @When("I run cd $dirName command")
    public void whenIRunCdCommandWithDirectoryNameAs(
            String dirName)
            throws IOException {

        deleteIfExistsAndThenCreateDirectory(dirName).runOn(fileSystem);
        terminal.processLinuxCommand("cd " + dirName);
    }

    @Then("pwd command should return string equal to $path")
    public void thenPwdCommandShouldReturnStringEqualTo(
            String path)
            throws IOException {

        assertThat(terminal.processLinuxCommand("pwd"))
                .isEqualToIgnoringCase(path);
    }

    private static RunOn deleteIfExistsAndThenCreateDirectory(
            String dirName)
            throws IOException {

        return fileSystem -> {

            Path path = normalize(fileSystem.getPath(dirName));

            fileSystem.provider().deleteIfExists(path);
            fileSystem.provider().createDirectory(path);
        };
    }

    private interface RunOn {
        void runOn(FileSystem fileSystem) throws IOException;
    }
}
