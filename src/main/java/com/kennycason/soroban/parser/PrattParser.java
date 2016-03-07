package com.kennycason.soroban.parser;

import com.kennycason.soroban.lexer.exception.EndOfStreamException;
import com.kennycason.soroban.lexer.token.Token;
import com.kennycason.soroban.lexer.token.TokenStream;
import com.kennycason.soroban.lexer.token.TokenType;
import com.kennycason.soroban.parser.exception.ParserException;
import com.kennycason.soroban.parser.expression.Expression;
import com.kennycason.soroban.parser.parselets.infix.InfixParselet;
import com.kennycason.soroban.parser.parselets.prefix.PrefixParselet;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by kenny on 3/1/16.
 *
 * A parser that can handle prefix, postfix, infix, mixfix, precedence, left-right associativity in a single pass.
 */
public class PrattParser {

    private final Map<TokenType, PrefixParselet> prefixParselets = new HashMap<>();
    private final Map<TokenType, InfixParselet> infixParselets = new HashMap<>();

    private final TokenStream tokenStream;

    public PrattParser(final TokenStream tokenStream) {
        this.tokenStream = tokenStream;
    }

    public Expression parseExpression() {
        return parseExpression(0);
    }

    public Expression parseExpression(final int precedence) {
        if (!tokenStream.hasNext()) {
            throw new EndOfStreamException();
        }
        final Token function = tokenStream.next();

        if (!prefixParselets.containsKey(function.getType())) {
            throw new ParserException("Unable to find prefix parselet for: " + function.getType());
        }
        final PrefixParselet prefixParselet = prefixParselets.get(function.getType());
        Expression expression = prefixParselet.parse(this, function);

        while (tokenStream.hasNext()
                && precedence < getPrecedence(tokenStream.peek())) {
            final Token token = tokenStream.next();
            if (!infixParselets.containsKey(token.getType())) {
                throw new ParserException("Unable to find infix parselet for: " + function.getType());
            }
            final InfixParselet infixParselet = infixParselets.get(token.getType());
            expression = infixParselet.parse(this, expression, token);
        }

        return expression;
    }

    public Token next() {
        return tokenStream.next();
    }

    public Token peek() { return tokenStream.peek(); }

    public Integer getPrecedence(final Token token) {
        final InfixParselet infixParselet = infixParselets.get(token.getType());
        if (infixParselet == null) {
            return 0;
        }
        return infixParselet.getPrecedence();
    }

    public void register(final TokenType tokenType, final PrefixParselet prefixParselet) {
        prefixParselets.put(tokenType, prefixParselet);
    }

    public void register(final TokenType tokenType, final InfixParselet infixParselet) {
        infixParselets.put(tokenType, infixParselet);
    }

}
