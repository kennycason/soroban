package com.kennycason.soroban;

import com.kennycason.soroban.function.binary.BinaryFunction;
import com.kennycason.soroban.function.binary.arithmetic.*;
import com.kennycason.soroban.function.unary.UnaryFunction;
import com.kennycason.soroban.function.unary.arithmetic.*;
import com.kennycason.soroban.function.unary.trignometry.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by kenny on 3/1/16.
 */
public class FunctionDictionary {
    public static final Map<String, UnaryFunction> UNARY_PREFIX_FUNCTIONS = new HashMap<String, UnaryFunction>() {{
        put("neg", new NegativeFunction());
        put("-", new NegativeFunction());
        put("fact", new FactorialFunction());
        put("ln", new LnFunction());
        put("log10", new Log10Function());
        put("floor", new FloorFunction());
        put("ceil", new CeilFunction());

        put("sin", new SineFunction());
        put("cos", new CosineFunction());
        put("tan", new TangentFunction());
        put("asin", new ArcSineFunction());
        put("acos", new ArcCosineFunction());
        put("atan", new ArcTangentFunction());
        put("sinh", new HyperbolicSineFunction());
        put("cosh", new HyperbolicCosineFunction());
        put("tanh", new HyperbolicTangentFunction());

        put("deg", new RadiansToDegreesFunction());
        put("rad", new DegreesToRadiansFunction());
    }};

    public static final Map<String, UnaryFunction> UNARY_POSTFIX_FUNCTIONS = new HashMap<String, UnaryFunction>() {{
        put("!", new FactorialFunction());
    }};

    public static final Map<String, BinaryFunction> BINARY_FUNCTIONS = new HashMap<String, BinaryFunction>() {{
        put("pow", new ExponentFunction());
        put("mul", new MultiplyFunction());
        put("div", new DivideFunction());
        put("add", new AddFunction());
        put("sub", new SubtractFunction());

        // short names commonly used in infix functions
        put("^", new ExponentFunction());
        put("*", new MultiplyFunction());
        put("/", new DivideFunction());
        put("+", new AddFunction());
        put("-", new SubtractFunction());

    }};

}
