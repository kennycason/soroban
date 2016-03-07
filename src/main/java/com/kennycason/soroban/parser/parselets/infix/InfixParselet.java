package com.kennycason.soroban.parser.parselets.infix;

import com.kennycason.soroban.lexer.token.Token;
import com.kennycason.soroban.parser.PrattParser;
import com.kennycason.soroban.parser.expression.Expression;

/**
 * Created by kenny on 3/3/16.
 */
public interface InfixParselet {
    Expression parse(PrattParser parser, Expression left,  Token token);
    int getPrecedence();
}
