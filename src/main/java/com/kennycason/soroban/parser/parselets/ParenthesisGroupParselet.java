package com.kennycason.soroban.parser.parselets;

import com.kennycason.soroban.lexer.token.Token;
import com.kennycason.soroban.lexer.token.TokenType;
import com.kennycason.soroban.parser.exception.ParserException;
import com.kennycason.soroban.parser.PrattParser;
import com.kennycason.soroban.parser.expression.Expression;
import com.kennycason.soroban.parser.parselets.prefix.PrefixParselet;

/**
 * Created by kenny on 3/3/16.
 */
public class ParenthesisGroupParselet implements PrefixParselet {

    @Override
    public Expression parse(final PrattParser parser, final Token token) {
        final Expression expression = parser.parseExpression();

        final Token next = parser.next();

        if (next.getType() != TokenType.RIGHT_PAREN) {
            throw new ParserException("Expected to find a ')', found " + next.getType());
        }

        return expression;
    }

}
