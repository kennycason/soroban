package com.kennycason.soroban.dictionary;

import com.kennycason.soroban.Soroban;
import com.kennycason.soroban.parser.expression.Expression;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Created by kenny on 3/1/16.
 *
 * An variable dictionary
 */
public class VariableDictionary {
    private static final Map<String, Expression> VARIABLES = new HashMap<>();

    private VariableDictionary() {}

    public static void set(final String variable, final Expression expression) {
        VARIABLES.put(variable, expression);
    }

    public static Expression get(final String variable) {
        return VARIABLES.get(variable);
    }

    public static void clearAll() {
        VARIABLES.clear();
    }

    public static boolean isSet(final String variable) {
        return VARIABLES.containsKey(variable);
    }

    public static String buildString() {
        final StringBuilder stringBuilder = new StringBuilder();
        for (Entry<String, Expression> variable : VARIABLES.entrySet()) {
            stringBuilder.append(variable.getKey());
            stringBuilder.append(" = ");
            stringBuilder.append(Soroban.print(variable.getValue()));
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }
}
