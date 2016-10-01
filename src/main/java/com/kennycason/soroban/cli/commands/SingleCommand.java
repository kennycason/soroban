package com.kennycason.soroban.cli.commands;

import com.kennycason.soroban.Soroban;
import com.kennycason.soroban.dictionary.VariableDictionary;
import com.kennycason.soroban.parser.exception.ParserException;

/**
 * Created by kenny on 9/30/16.
 */
public class SingleCommand {
    public void execute(final String command) {
        try {
            switch (command) {
                case "memory":
                    System.out.println("Memory Dump [");
                    System.out.print(VariableDictionary.buildString());
                    System.out.println("]");
                    break;

                default:
                    System.out.println(Soroban.print(command));
                    break;
            }

        } catch (final ParserException e) {
            System.out.println(e);

        } catch (final RuntimeException e) {
            System.out.println("Encountered unknown error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
