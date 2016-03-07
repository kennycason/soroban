Soroban (算盤/Abacus)

===========

Calculator Library in Java. This library is my attempt at the infamous "calculator app"

Contents:
   - Lexer implementation. Character/Token stream generators.
   - The Parser, single pass via Pratt Parser. Can parse prefix/postfix/infix/mixfix mathematical grammars. Produces Expression trees.
   - Expression Evaluator, consumes an expression and attempts to evaluate it recursively. Supports partially solving functions, and variables.
   - Custom function support via the FunctionDictionary class (N-parameter function support coming soon).
   - BigRational class which supports fraction/decimal math in attempt to delay loss of precision until as late in the calculation as possible.
   - Lots of tests.
   
Todo:
    - Add constants
    - Simplify fractions.
    - Some cases auto convert from integer to decimal in strange ways, investigate.
    - Add pretty expression printing
    - Add assignment for variables / expressions
  
    
Examples:
    - `(x + y) * x`
    - `log10(10)`
    - `ln(2.718)`
    - `10!`
    - `-n`
    - `a + b`
    - `0b101`
    - `0xFF + 0x01`
    - `-((10 ^ 2) / 4 + 25) * (1 + 1)`
    - `sin(rad(45 + 45))`
    - `sin(45) + cos(45)`