package com.kennycason.soroban.print;

import com.kennycason.soroban.ConstantDictionary;
import com.kennycason.soroban.VariableDictionary;
import com.kennycason.soroban.parser.expression.*;

/**
 * Created by kenny on 3/3/16.
 *
 * print an expressed or unexpressed function without applying any functions.
 */
public class ExpressionPrinter {

    public void print(final Expression expression, final StringBuilder stringBuilder) {
        if (expression instanceof NumberExpression) {
            stringBuilder.append(((NumberExpression) expression).getValue());
        }
        else if (expression instanceof VariableExpression) {
            print((VariableExpression) expression, stringBuilder);
        }
        else if (expression instanceof PrefixUnaryFunctionExpression) {
            print((PrefixUnaryFunctionExpression) expression, stringBuilder);
        }
        else if (expression instanceof PostfixUnaryFunctionExpression) {
            print((PostfixUnaryFunctionExpression) expression, stringBuilder);
        }
        else if (expression instanceof InfixFunctionExpression) {
            print((InfixFunctionExpression) expression, stringBuilder);
        }
        else if (expression instanceof FunctionCallExpression) {
            print((FunctionCallExpression) expression, stringBuilder);
        } else {
            throw new IllegalStateException("Unhandled expression type: " + expression.getClass());
        }
    }

    private void print(final PrefixUnaryFunctionExpression expression, final StringBuilder stringBuilder) {
        stringBuilder.append(expression.getFunction().getValue());
        stringBuilder.append('(');
        print(expression.getExpression(), stringBuilder);
        stringBuilder.append(')');
    }

    private void print(final PostfixUnaryFunctionExpression expression, final StringBuilder stringBuilder) {
        print(expression.getExpression(), stringBuilder);
        stringBuilder.append(expression.getFunction().getValue());
    }

    private void print(final InfixFunctionExpression expression, final StringBuilder stringBuilder) {
        print(expression.getLeft(), stringBuilder);
        stringBuilder.append(' ');
        stringBuilder.append(expression.getFunction().getValue());
        stringBuilder.append(' ');
        print(expression.getRight(), stringBuilder);
    }

    private void print(final FunctionCallExpression expression, final StringBuilder stringBuilder) {
        stringBuilder.append(((VariableExpression) expression.getFunction()).getValue());
        stringBuilder.append('(');
        for (int i = 0; i < expression.getExpressions().size(); i++) {
            print(expression.getExpressions().get(i), stringBuilder);
            if (i < expression.getExpressions().size() - 1) {
                stringBuilder.append(", ");
            }
        }
        stringBuilder.append(')');
    }

    private void print(final VariableExpression expression, final StringBuilder stringBuilder) {
        if (VariableDictionary.isSet(expression.getValue())) {
            stringBuilder.append(' ');
            stringBuilder.append(VariableDictionary.get(expression.getValue()));
            return;
        }
        if (ConstantDictionary.isSet(expression.getValue())) {
            stringBuilder.append(' ');
            stringBuilder.append(ConstantDictionary.get(expression.getValue()));
            return;
        }
        stringBuilder.append(expression.getValue());
    }

}
