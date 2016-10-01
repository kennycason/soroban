package com.kennycason.soroban.cli;

import com.kennycason.soroban.cli.commands.*;

/**
 * Created by kenny on 3/9/16.
 */
public class SorobanCli {
    private static final SingleCommand SINGLE_COMMAND = new SingleCommand();
    private static final InteractiveCommand INTERACTIVE_COMMAND = new InteractiveCommand();
    private static final FileReadCommand FILE_READ_COMMAND = new FileReadCommand();
    private static final VersionCommand VERSION_COMMAND = new VersionCommand();
    private static final HelpCommand HELP_COMMAND = new HelpCommand();

    public static final void main(final String[] args) {
        if (args.length == 0) {
            HELP_COMMAND.execute();
        }
        switch (args[0].toLowerCase()) {
            case "-i":
            case "--interactive":
                INTERACTIVE_COMMAND.execute();
                break;

            case "-v":
            case "--version":
                VERSION_COMMAND.execute();
                break;

            case "-h":
            case "--help":
                HELP_COMMAND.execute();
                break;

            default:
                if (FILE_READ_COMMAND.isFile(args[0])) {
                    FILE_READ_COMMAND.execute(args[0]);
                } else {

                    SINGLE_COMMAND.execute(String.join(" ", args));
                }
        }
    }

}