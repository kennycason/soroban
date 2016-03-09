package com.kennycason.soroban.parser.parselets.infix;

import com.kennycason.soroban.lexer.token.Token;
import com.kennycason.soroban.parser.PrattParser;
import com.kennycason.soroban.parser.Precedence;
import com.kennycason.soroban.parser.exception.ParserException;
import com.kennycason.soroban.parser.expression.Expression;
import com.kennycason.soroban.parser.expression.NumberExpression;
import com.kennycason.soroban.parser.expression.VariableAssignmentFunctionExpression;
import com.kennycason.soroban.parser.expression.VariableExpression;

/**
 * Created by kenny on 3/9/16.
 *
 * handle functions of the syntax a = 10
 */
public class VariableAssignmentFunctionParselet implements InfixParselet {

    @Override
    public Expression parse(final PrattParser parser, final Expression left, final Token token) {
        // is left-associative
        if (!(left instanceof VariableExpression)) {
            throw new ParserException("Left side of expression must be a variable. Found: " + left.getClass());
        }
        final Expression right = parser.parseExpression(Precedence.ASSIGNMENT);
        if (!(right instanceof NumberExpression)) {
            throw new ParserException("Right side of expression must be a number (for now). Found: " + right.getClass().getSimpleName());
        }
        return new VariableAssignmentFunctionExpression((VariableExpression) left, token, (NumberExpression) right);
    }

    @Override
    public int getPrecedence() {
        return Precedence.ASSIGNMENT;
    }

}
