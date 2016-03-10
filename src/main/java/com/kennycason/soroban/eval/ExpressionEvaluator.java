package com.kennycason.soroban.eval;

import com.kennycason.soroban.dictionary.ConstantDictionary;
import com.kennycason.soroban.dictionary.FunctionDictionary;
import com.kennycason.soroban.dictionary.VariableDictionary;
import com.kennycason.soroban.eval.exception.EvaluationException;
import com.kennycason.soroban.function.binary.BinaryFunction;
import com.kennycason.soroban.function.poly.PolyFunction;
import com.kennycason.soroban.function.unary.UnaryFunction;
import com.kennycason.soroban.lexer.token.Token;
import com.kennycason.soroban.lexer.token.TokenType;
import com.kennycason.soroban.parser.expression.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by kenny on 3/3/16.
 *
 * partially or completely evaluate an expression.
 */
public class ExpressionEvaluator {

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
        else if (expression instanceof VariableAssignmentFunctionExpression) {
            return evaluate((VariableAssignmentFunctionExpression) expression);
        }
        throw new IllegalStateException("Unhandled expression type: " + expression.getClass());
    }

    private Expression evaluate(final PrefixUnaryFunctionExpression expression) {
        final Expression evaulatedExpression = evaluate(expression.getExpression());

        if (evaulatedExpression instanceof NumberExpression) {
            return evaluateUnaryPrefixFunction(expression.getFunction(), (NumberExpression) evaulatedExpression);
        }
        // if we can't resolve to number, return unsolved expression
        return expression;
    }

    private Expression evaluate(final PostfixUnaryFunctionExpression expression) {
        final Expression evaulatedExpression = evaluate(expression.getExpression());

        if (evaulatedExpression instanceof NumberExpression) {
            return evaluateUnaryPostfixFunction(expression.getFunction(), (NumberExpression) evaulatedExpression);
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

    private Expression evaluate(final VariableAssignmentFunctionExpression expression) {
        VariableDictionary.set(expression.getVariableExpression().getValue(), expression.getValue().getValue());
        return expression.getValue();
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
        if (VariableDictionary.isSet(expression.getValue())) {
            return new NumberExpression(VariableDictionary.get(expression.getValue()));
        }
        if (ConstantDictionary.isSet(expression.getValue())) {
            return new NumberExpression(ConstantDictionary.get(expression.getValue()));
        }
        return expression;
    }

    private Expression evaluatePolyFunction(final Token function, final List<Expression> parameters) {
        final PolyFunction polyFunction = FunctionDictionary.getPoly(function.getValue());
        if (polyFunction == null) {
            throw new EvaluationException("Function [" + function.getValue() + "] does not exist");
        }
        return new NumberExpression(polyFunction.apply(
                parameters.stream()
                        .map(p -> ((NumberExpression) p).getValue())
                        .collect(Collectors.toList())));
    }

    private NumberExpression evaluateBinaryInfixFunction(final Token function, final NumberExpression left, final NumberExpression right) {
        final BinaryFunction binaryFunction = FunctionDictionary.getBinary(function.getValue());
        if (binaryFunction == null) {
            throw new EvaluationException("Function [" + function.getValue() + "] does not exist");
        }
        return new NumberExpression(binaryFunction.apply(left.getValue(), right.getValue()));
    }

    private NumberExpression evaluateUnaryPostfixFunction(final Token function, final NumberExpression numberExpression) {
        final UnaryFunction unaryFunction = FunctionDictionary.getUnaryPostfix(function.getValue());
        if (unaryFunction == null) {
            throw new EvaluationException("Function [" + function.getValue() + "] does not exist");
        }
        return new NumberExpression(unaryFunction.apply(numberExpression.getValue()));
    }

    private NumberExpression evaluateUnaryPrefixFunction(final Token function, final NumberExpression numberExpression) {
        final UnaryFunction unaryFunction = FunctionDictionary.getUnaryPrefix(function.getValue());
        if (unaryFunction == null) {
            throw new EvaluationException("Prefix function [" + function.getValue() + "] does not exist");
        }
        return new NumberExpression(unaryFunction.apply(numberExpression.getValue()));
    }

}
