package com.kennycason.soroban.cli.commands;

/**
 * Created by kenny on 9/30/16.
 */
public class HelpCommand {
    private static final VersionCommand VERSION_COMMAND = new VersionCommand();

    public void execute() {
        VERSION_COMMAND.execute();
        System.out.println("<expression>\t\tExecute an expression and print result");
        System.out.println("<file.soro>\t\tExecute a file of expressions");
        System.out.println("-i/--interactive\tRun in interactive mode");
        System.out.println("-h/--help\t\t\tPrint current help guide");
        System.out.println("-v/--version\t\tPrint version");
    }

}
