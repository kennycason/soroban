package com.kennycason.soroban.parser.parselets.infix;

import com.kennycason.soroban.lexer.token.Token;
import com.kennycason.soroban.lexer.token.TokenType;
import com.kennycason.soroban.parser.PrattParser;
import com.kennycason.soroban.parser.Precedence;
import com.kennycason.soroban.parser.exception.ParserException;
import com.kennycason.soroban.parser.expression.Expression;
import com.kennycason.soroban.parser.expression.FunctionCallExpression;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kenny on 3/3/16.
 *
 * handle functions of the syntax sin(x) or pow(base, power)
 */
public class FunctionCallParslet implements InfixParselet {
    @Override
    public Expression parse(final PrattParser parser, final Expression left, final Token token) {
        final List<Expression> parameters = new ArrayList<>();

        if (parser.peek().getType() == TokenType.RIGHT_PAREN) {
            new FunctionCallExpression(left, parameters);
        }
        while (parser.peek().getType() != TokenType.RIGHT_PAREN) {
            parameters.add(parser.parseExpression());

            if (parser.peek().getType() == TokenType.COMMA) {
                parser.next(); // consume comma
            }
            else {
                if (parser.peek().getType() == TokenType.RIGHT_PAREN) { continue; }
                throw new ParserException("Function parameters must be separated by commas.");
            }
        }
        parser.next(); // consume right paren
        return new FunctionCallExpression(left, parameters);
    }

    @Override
    public int getPrecedence() {
        return Precedence.FUNCTION_CALL;
    }

}
