package com.kennycason.soroban.eval;

import com.kennycason.soroban.FunctionDictionary;
import com.kennycason.soroban.eval.exception.EvaluationException;
import com.kennycason.soroban.function.binary.BinaryFunction;
import com.kennycason.soroban.function.poly.PolyFunction;
import com.kennycason.soroban.function.unary.UnaryFunction;
import com.kennycason.soroban.lexer.token.Token;
import com.kennycason.soroban.lexer.token.TokenType;
import com.kennycason.soroban.number.BigRational;
import com.kennycason.soroban.parser.expression.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by kenny on 3/3/16.
 *
 * partially or completely evaluate an expression.
 */
public class ExpressionEvaluator {

    private Map<String, BigRational> variables = new HashMap<>();

    public Expression evaluate(final Expression expression) {
        if (expression instanceof NumberExpression) {
            return expression;
        }
        else if (expression instanceof VariableExpression) {
            return evaluate((VariableExpression) expression);
        }
        else if (expression instanceof PrefixUnaryFunctionExpression) {
            return evaluate((PrefixUnaryFunctionExpression) expression);
        }
        else if (expression instanceof PostfixUnaryFunctionExpression) {
            return evaluate((PostfixUnaryFunctionExpression) expression);
        }
        else if (expression instanceof InfixFunctionExpression) {
            return evaluate((InfixFunctionExpression) expression);
        }
        else if (expression instanceof FunctionCallExpression) {
            return evaluate((FunctionCallExpression) expression);
        }
        throw new IllegalStateException("Not finished");
    }

    private Expression evaluate(final PrefixUnaryFunctionExpression expression) {
        final Expression evaulatedExpression = evaluate(expression.getExpression());

        if (evaulatedExpression instanceof NumberExpression) {
            return evaluatePrefixUnaryFunction(expression.getFunction(), (NumberExpression) evaulatedExpression);
        }
        // if we can't resolve to number, return unsolved expression
        return expression;
    }

    private Expression evaluate(final PostfixUnaryFunctionExpression expression) {
        final Expression evaulatedExpression = evaluate(expression.getExpression());

        if (evaulatedExpression instanceof NumberExpression) {
            return evaluatePostfixUnaryFunction(expression.getFunction(), (NumberExpression) evaulatedExpression);
        }
        // if we can't resolve to number, return unsolved expression
        return expression;
    }

    private Expression evaluate(final InfixFunctionExpression expression) {
        final Expression evaulatedLeftExpression = evaluate(expression.getLeft());
        final Expression evaulatedRightExpression = evaluate(expression.getRight());

        final boolean leftIsNumberExpression = evaulatedLeftExpression instanceof NumberExpression;
        final boolean rightIsNumberExpression = evaulatedRightExpression instanceof NumberExpression;

        if (leftIsNumberExpression && rightIsNumberExpression) {
            return evaluateBinaryInfixFunction(expression.getFunction(), (NumberExpression) evaulatedLeftExpression, (NumberExpression) evaulatedRightExpression);
        }
        // if we can't resolve to number, return unsolved expression
        return new InfixFunctionExpression(evaulatedLeftExpression, expression.getFunction(), evaulatedRightExpression);
    }

    private Expression evaluate(final FunctionCallExpression expression) {
        // a minor transform so that we can reuse other evaluate functions
        final Token function = new Token(((VariableExpression) expression.getFunction()).getValue(), TokenType.STRING);
        if (expression.getExpressions().size() == 1) {
            return evaluate(new PrefixUnaryFunctionExpression(expression.getExpressions().get(0), function));
        }
        if (expression.getExpressions().size() == 2) {
            return evaluate(new InfixFunctionExpression(expression.getExpressions().get(0), function, expression.getExpressions().get(1)));
        }

        // evaluate all the function parameters
        final List<Expression> evaluatedExpressions =
                expression.getExpressions()
                          .stream()
                          .map(expr -> evaluate(expr))
                          .collect(Collectors.toList());

        // if any parameter fails to resolve to a numeric value the best we can do is return the partially solved function
        for (final Expression evaluatedExpression : evaluatedExpressions) {
            if (!(evaluatedExpression instanceof NumberExpression)) {
                return new FunctionCallExpression(expression.getFunction(), evaluatedExpressions);
            }
        }
        return evaluatePolyFunction(function, evaluatedExpressions);
    }

    private Expression evaluate(final VariableExpression expression) {
        if (variables.containsKey(expression.getValue())) {
            return new NumberExpression(variables.get(expression.getValue()));
        }
        return expression;
    }

    private Expression evaluatePolyFunction(final Token function, final List<Expression> parameters) {
        final PolyFunction polyFunction = FunctionDictionary.POLY_FUNCTIONS.get(function.getValue());
        if (polyFunction == null) {
            throw new EvaluationException("Function [" + function.getValue() + "] does not exist");
        }
        return new NumberExpression(polyFunction.apply(
                parameters.stream()
                        .map(p -> ((NumberExpression) p).getValue())
                        .collect(Collectors.toList())));
    }

    private NumberExpression evaluateBinaryInfixFunction(final Token function, final NumberExpression left, final NumberExpression right) {
        final BinaryFunction binaryFunction = FunctionDictionary.BINARY_FUNCTIONS.get(function.getValue());
        if (binaryFunction == null) {
            throw new EvaluationException("Function [" + function.getValue() + "] does not exist");
        }
        return new NumberExpression(binaryFunction.apply(left.getValue(), right.getValue()));
    }

    private NumberExpression evaluatePostfixUnaryFunction(final Token function, final NumberExpression numberExpression) {
        final UnaryFunction unaryFunction = FunctionDictionary.UNARY_POSTFIX_FUNCTIONS.get(function.getValue());
        if (unaryFunction == null) {
            throw new EvaluationException("Function [" + function.getValue() + "] does not exist");
        }
        return new NumberExpression(unaryFunction.apply(numberExpression.getValue()));
    }

    private NumberExpression evaluatePrefixUnaryFunction(final Token function, final NumberExpression numberExpression) {
        final UnaryFunction unaryFunction = FunctionDictionary.UNARY_PREFIX_FUNCTIONS.get(function.getValue());
        if (unaryFunction == null) {
            throw new EvaluationException("Prefix function [" + function.getValue() + "] does not exist");
        }
        return new NumberExpression(unaryFunction.apply(numberExpression.getValue()));
    }

    public void clearVariables() {
        variables.clear();
    }

    public void register(final String variable, final BigRational number) {
        variables.put(variable, number);
    }

}
