package com.kennycason.soroban.cli.commands;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 * Created by kenny on 9/30/16.
 */
public class FileReadCommand {
    private static final SingleCommand SINGLE_COMMAND = new SingleCommand();

    public boolean isFile(final String fileName) {
        return Files.isRegularFile(Paths.get(fileName));
    }

    public void execute(final String fileName) {
        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
            stream.forEach(SINGLE_COMMAND::execute);

        } catch (final IOException e) {
            System.out.println("Encountered error: " + e.getMessage());
            e.printStackTrace();
        }
    }

}
