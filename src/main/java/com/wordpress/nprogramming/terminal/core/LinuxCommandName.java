package com.wordpress.nprogramming.terminal.core;

public final class LinuxCommandName {
    private final String name;

    public LinuxCommandName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    public static LinuxCommandName aLinuxCommandName(String name) {
        return new LinuxCommandName(name);
    }
}
