package com.kennycason.soroban.parser;

import com.kennycason.soroban.lexer.token.Associativity;
import com.kennycason.soroban.lexer.token.TokenStream;
import com.kennycason.soroban.lexer.token.TokenType;
import com.kennycason.soroban.parser.parselets.ParenthesisGroupParselet;
import com.kennycason.soroban.parser.parselets.NumberParselet;
import com.kennycason.soroban.parser.parselets.VariableParselet;
import com.kennycason.soroban.parser.parselets.infix.FunctionCallParslet;
import com.kennycason.soroban.parser.parselets.infix.InfixBinaryFunctionParselet;
import com.kennycason.soroban.parser.parselets.prefix.PostfixUnaryFunctionParselet;
import com.kennycason.soroban.parser.parselets.prefix.PrefixUnaryFunctionParslet;

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

        // prefix operators
        register(TokenType.PLUS, new PrefixUnaryFunctionParslet(Precedence.PREFIX));
        register(TokenType.MINUS, new PrefixUnaryFunctionParslet(Precedence.PREFIX));
        register(TokenType.EXCLAMATION, new PrefixUnaryFunctionParslet(Precedence.PREFIX));

        // postfix operators
        register(TokenType.EXCLAMATION, new PostfixUnaryFunctionParselet(Precedence.POSTFIX));

        // infix binary operators
        register(TokenType.PLUS, new InfixBinaryFunctionParselet(Precedence.PLUS, Associativity.LEFT));
        register(TokenType.MINUS, new InfixBinaryFunctionParselet(Precedence.PLUS, Associativity.LEFT));
        register(TokenType.MULTIPLY, new InfixBinaryFunctionParselet(Precedence.MULTIPLY, Associativity.LEFT));
        register(TokenType.DIVIDE, new InfixBinaryFunctionParselet(Precedence.MULTIPLY, Associativity.LEFT));
        register(TokenType.EXPONENT, new InfixBinaryFunctionParselet(Precedence.EXPONENT, Associativity.RIGHT));
    }

}
