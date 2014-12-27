package com.wordpress.nprogramming.terminal.unit;

import com.wordpress.nprogramming.terminal.builders.FileSystemBuilder;
import com.wordpress.nprogramming.terminal.command.Cd;
import com.wordpress.nprogramming.terminal.core.FileSystemContext;
import com.wordpress.nprogramming.terminal.core.TerminalContext;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.Path;
import java.nio.file.Paths;

import static com.wordpress.nprogramming.terminal.utils.PathHelper.deleteIfExistsAndThenCreateDirectory;
import static org.assertj.core.api.Assertions.assertThat;

public class CdCommandSpec {

    private static final Path WORKING_DIR = Paths.get("/home/path");

    @Test
    public void setWorkingDirectoryToAbsolutePathIfItIsPassed()
            throws Exception {

        FileSystemContext terminalContext = terminalContext();

        new Cd().execute(terminalContext, WORKING_DIR.toString());

        assertThat(terminalContext.workingDir().toString())
                .isEqualTo(WORKING_DIR.toString());
    }

    @Test
    public void setWorkingDirectoryToCompoundNameIfOnlyDirNameIsPassed()
            throws Exception {

        FileSystemContext terminalContext = terminalContext();

        new Cd().execute(terminalContext, "new_dir");

        assertThat(terminalContext.workingDir().toString())
                .isEqualTo(WORKING_DIR.toString() + "/new_dir");
    }

    @Test
    public void setWorkingDirectoryToParentIfDoubleDotsArePassed()
            throws Exception {

        FileSystemContext terminalContext = terminalContext();

        new Cd().execute(terminalContext, "..");

        assertThat(terminalContext.workingDir().toString())
                .isEqualTo("/home");
    }

    @Test
    public void setWorkingDirectoryToRootIfDoubleDotsArePassedManyTimes()
            throws Exception {

        FileSystemContext terminalContext = terminalContext();

        new Cd().execute(terminalContext, "../../../..");

        assertThat(terminalContext.workingDir().toString())
                .isEqualTo("/");
    }

    @Test
    public void setWorkingDirectoryToTheSameDirIfDotIsPassed()
            throws Exception {

        FileSystemContext terminalContext = terminalContext();

        new Cd().execute(terminalContext, ".");

        assertThat(terminalContext.workingDir().toString())
                .isEqualTo(WORKING_DIR.toString());
    }

    @Test
    public void setWorkingDirectoryToHomeDirIfNoArgumentsArePassed()
            throws Exception {

        FileSystemContext terminalContext = terminalContext();

        new Cd().execute(terminalContext);

        assertThat(terminalContext.workingDir().toString())
                .isEqualTo(WORKING_DIR.toString());
    }

    private FileSystemContext terminalContext() throws IOException {

        FileSystem fileSystem = FileSystemBuilder.create()
                .withWorkingDirectorySetTo(WORKING_DIR.toString())
                .build();

        deleteIfExistsAndThenCreateDirectory("new_dir").runOn(fileSystem);

        return new TerminalContext(fileSystem, WORKING_DIR);
    }
}
