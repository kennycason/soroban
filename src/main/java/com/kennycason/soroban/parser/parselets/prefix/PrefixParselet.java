package com.kennycason.soroban.parser.parselets.prefix;

import com.kennycason.soroban.lexer.token.Token;
import com.kennycason.soroban.parser.PrattParser;
import com.kennycason.soroban.parser.expression.Expression;

/**
 * Created by kenny on 3/3/16.
 */
public interface PrefixParselet {
    Expression parse(PrattParser parser, Token token);
}
