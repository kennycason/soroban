package com.kennycason.soroban.parser.parselets;

import com.kennycason.soroban.lexer.token.NumberToken;
import com.kennycason.soroban.lexer.token.NumberToken.Base;
import com.kennycason.soroban.lexer.token.Token;
import com.kennycason.soroban.number.BigRational;
import com.kennycason.soroban.parser.PrattParser;
import com.kennycason.soroban.parser.expression.Expression;
import com.kennycason.soroban.parser.expression.NumberExpression;
import com.kennycason.soroban.parser.parselets.prefix.PrefixParselet;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * Created by kenny on 3/3/16.
 */
public class NumberParselet implements PrefixParselet {
    @Override
    public Expression parse(final PrattParser parser, final Token token) {
        final NumberToken numberToken = (NumberToken) token;
        if (numberToken.isDecimal()) {
            return new NumberExpression(evaluateDecimal(numberToken));
        }

        return new NumberExpression(evaluateInteger(numberToken));
    }

    private BigRational evaluateDecimal(final NumberToken numberToken) {
        if (numberToken.getBase() == Base.TEN) {
            return new BigRational(new BigDecimal(numberToken.getValue()));
        }
        throw new IllegalStateException("Only base 10 numbers can contain floating precision: " + numberToken.getBase());
    }

    private static BigRational evaluateInteger(final NumberToken numberToken) {
        if (numberToken.getBase() == Base.TEN) {
            return new BigRational(new BigInteger(numberToken.getValue()));
        }
        if (numberToken.getBase() == Base.HEXADECIMAL) {
            return new BigRational(new BigInteger(numberToken.getValue(), 16));
        }
        if (numberToken.getBase() == Base.BINARY) {
            return new BigRational(new BigInteger(numberToken.getValue(), 2));
        }
        throw new IllegalStateException("Unrecognized number base: " + numberToken.getBase());
    }
}
