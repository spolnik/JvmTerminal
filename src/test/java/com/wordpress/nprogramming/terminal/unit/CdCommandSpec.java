package com.wordpress.nprogramming.terminal.unit;

import com.wordpress.nprogramming.terminal.builders.FileSystemBuilder;
import com.wordpress.nprogramming.terminal.command.Cd;
import com.wordpress.nprogramming.terminal.command.MkDir;
import com.wordpress.nprogramming.terminal.core.FileSystemContext;
import com.wordpress.nprogramming.terminal.core.FileSystemService;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.FileSystem;

import static org.assertj.core.api.Assertions.assertThat;

public class CdCommandSpec {

    private static final String WORKING_DIR = "/home/path";

    @Test
    public void setWorkingDirectoryToAbsolutePathIfItIsPassed()
            throws Exception {

        FileSystemContext context = fileSystemContext();

        new Cd().execute(context, WORKING_DIR);

        assertThat(context.workingDir().toString())
                .isEqualTo(WORKING_DIR);
    }

    @Test
    public void setWorkingDirectoryToCompoundNameIfOnlyDirNameIsPassed()
            throws Exception {

        FileSystemContext context = fileSystemContext();

        new MkDir().execute(context, "new_dir");
        new Cd().execute(context, "new_dir");

        assertThat(context.workingDir().toString())
                .isEqualTo(WORKING_DIR + "/new_dir");
    }

    @Test
    public void setWorkingDirectoryToParentIfDoubleDotsArePassed()
            throws Exception {

        FileSystemContext context = fileSystemContext();

        new Cd().execute(context, "..");

        assertThat(context.workingDir().toString())
                .isEqualTo("/home");
    }

    @Test
    public void setWorkingDirectoryToRootIfDoubleDotsArePassedManyTimes()
            throws Exception {

        FileSystemContext context = fileSystemContext();

        new Cd().execute(context, "../../../..");

        assertThat(context.workingDir().toString())
                .isEqualTo("/");
    }

    @Test
    public void setWorkingDirectoryToTheSameDirIfDotIsPassed()
            throws Exception {

        FileSystemContext context = fileSystemContext();

        new Cd().execute(context, ".");

        assertThat(context.workingDir().toString())
                .isEqualTo(WORKING_DIR);
    }

    @Test
    public void setWorkingDirectoryToHomeDirIfNoArgumentsArePassed()
            throws Exception {

        FileSystemContext context = fileSystemContext();

        new Cd().execute(context, "..");
        new Cd().execute(context);

        assertThat(context.workingDir().toString())
                .isEqualTo(WORKING_DIR);
    }

    private FileSystemContext fileSystemContext() throws IOException {

        FileSystem fileSystem = FileSystemBuilder.create()
                .withWorkingDirectorySetTo(WORKING_DIR)
                .build();

        return new FileSystemService(fileSystem, WORKING_DIR);
    }
}
