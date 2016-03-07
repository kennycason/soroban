package com.kennycason.soroban.parser;

import com.gs.collections.api.map.MutableMap;
import com.gs.collections.impl.factory.Maps;
import com.kennycason.soroban.lexer.exception.EndOfStreamException;
import com.kennycason.soroban.lexer.token.Token;
import com.kennycason.soroban.lexer.token.TokenStream;
import com.kennycason.soroban.lexer.token.TokenType;
import com.kennycason.soroban.parser.exception.ParserException;
import com.kennycason.soroban.parser.expression.Expression;
import com.kennycason.soroban.parser.parselets.infix.InfixParselet;
import com.kennycason.soroban.parser.parselets.prefix.PrefixParselet;

/**
 * Created by kenny on 3/1/16.
 *
 * e.g.
 * pure infix -> postfix
 * (((1 + 2) * 3) + 6) / (2 + 3) ->
 *            (/ (+ (* (+ 1 2) 3) 6) (+ 2 3))
 *
 * e.g.
 * mixed infix/postfix -> postfix
 *
 * sin(60 + 30) / cos(45) ->
 *    (/ (sin (+ 60 30)) (cos 45))
 *
 * 1 + 1 -> (+ 1 1)
 * 1 + 2 * 4 -> (1 + (2 * 4)) -> (+ 1 (* 2 4))
 * sin(x) + cos(y) -> (+ (sin x) (cos y))
 * (+ 1 1) -> (add 1 1)
 *
 * note: that sin(x) is already basically postfix, we are just doing a minor rewrite
 * note: we must know the context of the "-" sign. is it minus? or negative?.
 * note: we must know the difference between arithmetic parenthesis and function parenthesis
 *
 *
 */
public class PrattParser {

    private final MutableMap<TokenType, PrefixParselet> prefixParselets = Maps.mutable.empty();
    private final MutableMap<TokenType, InfixParselet> infixParselets = Maps.mutable.empty();

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
