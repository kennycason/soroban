package com.kennycason.soroban.print;

import com.kennycason.soroban.Soroban;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by kenny on 3/6/16.
 */
public class ExpressionPrinterTest {

    @Test
    public void print() {
        assertEquals("a", Soroban.print("a"));
        assertEquals("10", Soroban.print("10"));
        assertEquals("10/2", Soroban.print("10 / 2"));
        assertEquals("5", Soroban.print("10 / 2.0"));
        assertEquals("4", Soroban.print("10.0 / 2.5"));
        assertEquals("3.3333333333333333333", Soroban.print("10.0 / 3.0"));
        assertEquals("1.0", Soroban.print("sin(rad(90))"));
        assertEquals("sin(x)", Soroban.print("sin(x)"));
        assertEquals("-(n)", Soroban.print("-n"));  // TODO fix, this is a bug due to unary prefix functions, single param function calls are mapped to unary prefix functions
        assertEquals("sin(x + y)", Soroban.print("sin(x + y)"));
        assertEquals("sin(x) + cos(y) + 0.47669014603118814", Soroban.print("sin(x) + cos(y) + tan(PI^2)"));
    }

    @Test
    public void chainedVariableAssignment() {
        assertEquals("10", Soroban.print("x = y = 10"));
    }

}
