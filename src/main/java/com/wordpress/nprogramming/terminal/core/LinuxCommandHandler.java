package com.wordpress.nprogramming.terminal.core;

public interface LinuxCommandHandler {
    boolean canHandle(String linuxCommand);
    String process(TerminalContext context, String... args);
}
