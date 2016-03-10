package com.kennycason.soroban.cli;

import com.kennycason.soroban.Soroban;
import com.kennycason.soroban.dictionary.VariableDictionary;
import com.kennycason.soroban.parser.exception.ParserException;

import java.util.Scanner;

/**
 * Created by kenny on 3/9/16.
 */
public class SorobanCli {

    public static final void main(String[] args) {
        new SorobanCli().run();
    }

    public void run() {
        boolean running = true;
        while (running) {
            System.out.print("> ");
            final Scanner scanner = new Scanner(System.in);
            running = processInput(scanner.nextLine());
        }
    }

    private boolean processInput(final String input) {
        switch (input) {
            case "exit":
                System.out.println("Good Bye");
                return false;

            case "memory":
                System.out.println(VariableDictionary.buildString());
                break;

            default:
                try {
                    System.out.println(Soroban.print(input));
                } catch (ParserException e) {
                    System.out.println(e);
                }
                break;
        }
        return true;
    }

}
