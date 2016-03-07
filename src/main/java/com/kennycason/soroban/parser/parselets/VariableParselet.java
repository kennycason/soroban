package com.kennycason.soroban.parser.parselets;

import com.kennycason.soroban.lexer.token.Token;
import com.kennycason.soroban.parser.PrattParser;
import com.kennycason.soroban.parser.expression.Expression;
import com.kennycason.soroban.parser.expression.VariableExpression;
import com.kennycason.soroban.parser.parselets.prefix.PrefixParselet;

/**
 * Created by kenny on 3/3/16.
 */
public class VariableParselet implements PrefixParselet {
    @Override
    public Expression parse(final PrattParser parser, final Token token) {
        return new VariableExpression(token);
    }
}
