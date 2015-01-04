package com.wordpress.nprogramming.terminal;

import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;
import com.wordpress.nprogramming.terminal.command.*;
import com.wordpress.nprogramming.terminal.core.LinuxCommand;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public final class TerminalAppModule extends AbstractModule {

    @Override

    protected void configure() {
        bind(new TypeLiteral<List<LinuxCommand>>() {
        }).toInstance(linuxCommands());
    }

    private List<LinuxCommand> linuxCommands() {
        List<LinuxCommand> linuxCommands = new ArrayList<>();

        linuxCommands.addAll(
                Arrays.asList(
                        new Cd(),
                        new Cp(),
                        new Ls(),
                        new MkDir(),
                        new Pwd(),
                        new RmDir(),
                        new Touch()));

        return Collections.unmodifiableList(linuxCommands);
    }
}
