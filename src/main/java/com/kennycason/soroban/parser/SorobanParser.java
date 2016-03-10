package com.kennycason.soroban.parser;

import com.kennycason.soroban.lexer.token.Associativity;
import com.kennycason.soroban.lexer.token.TokenStream;
import com.kennycason.soroban.lexer.token.TokenType;
import com.kennycason.soroban.parser.parselets.NumberParselet;
import com.kennycason.soroban.parser.parselets.ParenthesisGroupParselet;
import com.kennycason.soroban.parser.parselets.VariableParselet;
import com.kennycason.soroban.parser.parselets.infix.FunctionCallParslet;
import com.kennycason.soroban.parser.parselets.infix.InfixFunctionParselet;
import com.kennycason.soroban.parser.parselets.infix.VariableAssignmentFunctionParselet;
import com.kennycason.soroban.parser.parselets.postfix.UnaryPostfixFunctionParselet;
import com.kennycason.soroban.parser.parselets.prefix.UnaryPrefixFunctionParslet;

/**
 * Created by kenny on 3/3/16.
 */
public class SorobanParser extends PrattParser {

    public SorobanParser(final TokenStream tokenStream) {
        super(tokenStream);

        register(TokenType.STRING, new VariableParselet());
        register(TokenType.NUMBER, new NumberParselet());

        register(TokenType.LEFT_PAREN, new ParenthesisGroupParselet());
        register(TokenType.LEFT_PAREN, new FunctionCallParslet());
        register(TokenType.ASSIGNMENT, new VariableAssignmentFunctionParselet());

        // prefix operators
        register(TokenType.PLUS, new UnaryPrefixFunctionParslet(Precedence.PREFIX));
        register(TokenType.MINUS, new UnaryPrefixFunctionParslet(Precedence.PREFIX));
        register(TokenType.EXCLAMATION, new UnaryPrefixFunctionParslet(Precedence.PREFIX));

        // postfix operators
        register(TokenType.EXCLAMATION, new UnaryPostfixFunctionParselet(Precedence.POSTFIX));

        // infix binary operators
        register(TokenType.PLUS, new InfixFunctionParselet(Precedence.PLUS, Associativity.LEFT));
        register(TokenType.MINUS, new InfixFunctionParselet(Precedence.PLUS, Associativity.LEFT));
        register(TokenType.MULTIPLY, new InfixFunctionParselet(Precedence.MULTIPLY, Associativity.LEFT));
        register(TokenType.DIVIDE, new InfixFunctionParselet(Precedence.MULTIPLY, Associativity.LEFT));
        register(TokenType.EXPONENT, new InfixFunctionParselet(Precedence.EXPONENT, Associativity.RIGHT));
    }

}
