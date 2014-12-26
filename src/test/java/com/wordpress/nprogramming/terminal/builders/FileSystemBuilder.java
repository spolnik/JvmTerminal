package com.wordpress.nprogramming.terminal.builders;

import com.google.common.jimfs.Configuration;
import com.google.common.jimfs.Jimfs;

import java.nio.file.FileSystem;

public final class FileSystemBuilder {

    private String workingDirectory;

    public static FileSystemBuilder create() {
        return new FileSystemBuilder();
    }

    public FileSystemBuilder withWorkingDirectorySetTo(
            String workingDirectory) {

        this.workingDirectory = workingDirectory;
        return this;
    }

    public FileSystem build() {
        return Jimfs.newFileSystem(
                Configuration.unix().toBuilder()
                        .setWorkingDirectory(workingDirectory).build());
    }
}
