package com.wordpress.nprogramming.terminal.core;

public final class LinuxCommandName {
    
    private final String name;

    private LinuxCommandName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    public static LinuxCommandName asLinuxCommandName(String name) {
        return new LinuxCommandName(name);
    }
}
