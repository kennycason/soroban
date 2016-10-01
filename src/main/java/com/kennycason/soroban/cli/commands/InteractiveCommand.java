package com.kennycason.soroban.cli.commands;

import java.util.Scanner;

/**
 * Created by kenny on 9/30/16.
 */
public class InteractiveCommand {
    private static final SingleCommand EXECUTE_COMMAND = new SingleCommand();

    public void execute() {
        boolean running = true;
        while (running) {
            System.out.print("> ");
            final Scanner scanner = new Scanner(System.in);
            running = processInput(scanner.nextLine());
        }
    }

    private static boolean processInput(final String command) {
        switch (command) {
            case "exit":
                System.out.println("Good Bye");
                return false;

            default:
                EXECUTE_COMMAND.execute(command);
                break;
        }
        return true;
    }

}
