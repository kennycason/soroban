package com.kennycason.soroban.cli;

import com.kennycason.soroban.VariableDictionary;
import com.kennycason.soroban.eval.ExpressionEvaluator;
import com.kennycason.soroban.lexer.token.TokenStream;
import com.kennycason.soroban.lexer.tokenizer.CharacterStream;
import com.kennycason.soroban.lexer.tokenizer.ExpressionTokenizer;
import com.kennycason.soroban.parser.SorobanParser;
import com.kennycason.soroban.parser.exception.ParserException;
import com.kennycason.soroban.parser.expression.Expression;
import com.kennycason.soroban.print.ExpressionPrinter;

import java.util.Scanner;

/**
 * Created by kenny on 3/9/16.
 */
public class SorobanCli {
    final ExpressionPrinter expressionPrinter = new ExpressionPrinter();
    final ExpressionTokenizer expressionTokenizer = new ExpressionTokenizer();
    final ExpressionEvaluator expressionEvaluator = new ExpressionEvaluator();

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
                    final StringBuilder stringBuilder = new StringBuilder();
                    expressionPrinter.print(expressionEvaluator.evaluate(evaluate(input)), stringBuilder);
                    System.out.println(stringBuilder.toString());
                } catch (ParserException e) {
                    System.out.println(e);
                }
                break;
        }
        return true;
    }

    private Expression evaluate(final String expr) {
        return new SorobanParser(
                new TokenStream(
                        expressionTokenizer.tokenize(
                                new CharacterStream(expr))))
                                        .parseExpression();
    }

}
