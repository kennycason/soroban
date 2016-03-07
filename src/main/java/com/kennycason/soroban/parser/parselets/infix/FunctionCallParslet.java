package com.kennycason.soroban.parser.parselets.infix;

import com.gs.collections.api.list.MutableList;
import com.gs.collections.impl.list.mutable.FastList;
import com.kennycason.soroban.lexer.token.Token;
import com.kennycason.soroban.lexer.token.TokenType;
import com.kennycason.soroban.parser.Precedence;
import com.kennycason.soroban.parser.PrattParser;
import com.kennycason.soroban.parser.expression.Expression;
import com.kennycason.soroban.parser.expression.FunctionCallExpression;

/**
 * Created by kenny on 3/3/16.
 *
 * handle functions of the syntax sin(x) or pow(base power)
 */
public class FunctionCallParslet implements InfixParselet {
    @Override
    public Expression parse(final PrattParser parser, final Expression left, final Token token) {
        final MutableList<Expression> parameters = new FastList<>();

        if (parser.peek().getType() == TokenType.RIGHT_PAREN) {
            new FunctionCallExpression(left, parameters);
        }
        while (parser.peek().getType() != TokenType.RIGHT_PAREN) {
            parameters.add(parser.parseExpression());
        }
        parser.next(); // consume right paren
        return new FunctionCallExpression(left, parameters);
    }

    @Override
    public int getPrecedence() {
        return Precedence.FUNCTION_CALL;
    }

}
