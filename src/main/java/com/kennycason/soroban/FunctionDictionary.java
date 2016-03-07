package com.kennycason.soroban;

import com.kennycason.soroban.function.binary.BinaryFunction;
import com.kennycason.soroban.function.binary.arithmetic.*;
import com.kennycason.soroban.function.poly.PolyFunction;
import com.kennycason.soroban.function.poly.arithmetic.AddPolyFunction;
import com.kennycason.soroban.function.unary.UnaryFunction;
import com.kennycason.soroban.function.unary.arithmetic.*;
import com.kennycason.soroban.function.unary.trignometry.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by kenny on 3/1/16.
 *
 * An extensible function dictionary with presets
 */
public class FunctionDictionary {
    private static final Map<String, UnaryFunction> UNARY_PREFIX_FUNCTIONS = new HashMap<>();
    private static final Map<String, UnaryFunction> UNARY_POSTFIX_FUNCTIONS = new HashMap<>();
    private static final Map<String, BinaryFunction> BINARY_FUNCTIONS = new HashMap<>();
    private static final Map<String, PolyFunction> POLY_FUNCTIONS = new HashMap<>();
    static {
        registerUnaryPrefix("neg", new NegativeFunction());
        registerUnaryPrefix("-", new NegativeFunction());
        registerUnaryPrefix("fact", new FactorialFunction());
        registerUnaryPrefix("ln", new LnFunction());
        registerUnaryPrefix("log10", new Log10Function());
        registerUnaryPrefix("floor", new FloorFunction());
        registerUnaryPrefix("ceil", new CeilFunction());
        registerUnaryPrefix("deg", new RadiansToDegreesFunction());
        registerUnaryPrefix("rad", new DegreesToRadiansFunction());

        registerUnaryPrefix("sin", new SineFunction());
        registerUnaryPrefix("cos", new CosineFunction());
        registerUnaryPrefix("tan", new TangentFunction());
        registerUnaryPrefix("asin", new ArcSineFunction());
        registerUnaryPrefix("acos", new ArcCosineFunction());
        registerUnaryPrefix("atan", new ArcTangentFunction());
        registerUnaryPrefix("sinh", new HyperbolicSineFunction());
        registerUnaryPrefix("cosh", new HyperbolicCosineFunction());
        registerUnaryPrefix("tanh", new HyperbolicTangentFunction());


        registerUnaryPostfix("!", new FactorialFunction());


        registerBinary("pow", new ExponentFunction());
        registerBinary("mul", new MultiplyFunction());
        registerBinary("div", new DivideFunction());
        registerBinary("add", new AddFunction());
        registerBinary("sub", new SubtractFunction());
        // short names commonly used in infix functions
        registerBinary("^", new ExponentFunction());
        registerBinary("*", new MultiplyFunction());
        registerBinary("/", new DivideFunction());
        registerBinary("+", new AddFunction());
        registerBinary("-", new SubtractFunction());

        registerPoly("add", new AddPolyFunction());
    }

    private FunctionDictionary() {}

    public static void registerBinary(final String function, final BinaryFunction binaryFunction) {
        BINARY_FUNCTIONS.put(function, binaryFunction);
    }

    public static void registerUnaryPrefix(final String function, final UnaryFunction unaryFunction) {
        UNARY_PREFIX_FUNCTIONS.put(function, unaryFunction);
    }

    public static void registerUnaryPostfix(final String function, final UnaryFunction unaryFunction) {
        UNARY_POSTFIX_FUNCTIONS.put(function, unaryFunction);
    }

    public static void registerPoly(final String function, final PolyFunction polyFunction) {
        POLY_FUNCTIONS.put(function, polyFunction);
    }

    public static BinaryFunction getBinary(final String function) {
        return BINARY_FUNCTIONS.get(function);
    }

    public static UnaryFunction getUnaryPrefix(final String function) {
        return UNARY_PREFIX_FUNCTIONS.get(function);
    }

    public static UnaryFunction getUnaryPostfix(final String function) {
        return UNARY_POSTFIX_FUNCTIONS.get(function);
    }

    public static PolyFunction getPoly(final String function) {
        return POLY_FUNCTIONS.get(function);
    }
}
