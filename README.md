Soroban (算盤/Abacus)
====================

Command Line Calculator Tool in Java. 

### Usage

#### Execute a single expression

```bash
soroban ln(E) * PI / 2
```

#### Enter Interactive mode

```bash
soroban -i
```

#### Load commands from file

Load a file of commands, one command per line, and execute them sequentially. 

```bash
soroban programs/test.soro 
soroban /full/path/to/file.soro 
```

#### Print Help

```bash
soroban -h
```

#### Print version

```bash
soroban -v
```


### Contents
   - Lexer implementation. Character/Token stream generators.
   - The Parser, single pass via Pratt Parser. Can parse prefix/postfix/infix/mixfix mathematical grammars. Produces Expression trees.
   - Expression Evaluator, consumes an expression and attempts to evaluate it recursively. Supports partially solving functions, and variables.
   - Custom function support via the FunctionDictionary class. Unary, Binary, Poly parameter function support.
   - BigRational class which supports fraction/decimal math in attempt to delay loss of precision until as late in the calculation as possible.
   - Variable support.
   - Variable assignment of numbers and expressions
   - Constant support.
   - Interactive Cli.
   - Lots of tests.
   
#### Todo
   - Simplify fractions.
   - Some cases auto convert from integer to decimal in strange ways, investigate.
   - Variable assignment of expressions.
  
### Example Expressions
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
   - `add(a, b, c)`
   - `add((a + a), (b + b))`
   - `a = 10`
   - `x = a ^ 3`
   
   
### Install

#### Brew Install

```bash
brew install https://raw.githubusercontent.com/kennycason/soroban/master/script/brew/soroban.rb
```

#### Install (via Bash Script)

A helper script has been added to install Run. Java is required to run.
The install is a single a jar from Maven Central.

```bash
bash <(curl -s https://raw.githubusercontent.com/kennycason/soroban/master/script/install.sh)
```

#### Maven Install (To include in other code)

```xml
<dependency>
    <groupId>com.kennycason</groupId>
    <artifactId>soroban</artifactId>
    <version>1.1</version>
</dependency>
```
   
   
### Interactive CLI Demo:
```
> 10
10
> x = 10
10
> x + 20
30
> x * 2
20
> x ^ 10
1.0E+10
> x ^ 3
1000.0
> x / 10
10/10
> x / 10.0
1
> 3/5 + 1/5
4/5
> 3/5 + 1
8/5
> sin(rad(90))
1.0
> x = y ^ 2
y ^ 2
> x
y ^ 2
> y = 3
3
> x
9.0
> memory
x = 3 ^ 2
y = 3
> PI
3.141592653589793
> E
2.718281828459045
> ln(E)
1.0
> x = y = 10
10
```
