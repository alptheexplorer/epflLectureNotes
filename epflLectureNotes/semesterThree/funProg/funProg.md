# Functional programming 

## Week 1

### Evaluation strategies: **call by name** vs **call by value**

Suppose we have a program in scala that runs `sumOfSquares(3, 2+2)`. The call-by-value model would produce:
```scala
sumOfSquares(3, 2+2)
sumOfSquares(3, 4)
square(3) + square(4)
3*3 + square(4)
9 + 4*4
9 + 16
25
```
Whereas the call-by-name model would produce 
```scala
sumOfSquares(3, 2+2)
square(3) + square(2+2)
3*3 + square(2+2)
9 + square(2+2)
9 + (2+2)*(2+2)
9 + 4*(2+2)
9 + 4*4
25
```

The advantage of call-by-value is that every function argument is evaluted only once. In the second snippet, we see how 2+2 is evaluated twice. However, call-by-name has the advantage that a function argument is not evaluated if a parameter is unused in the evaluation of the function body. 

Consider this example:
```scala
def test(x: Int, y: Int) = x*x
// which evaluation method is more efficient?
test(3+4,8)

//CBV
7*8
//CBN
(3+4)*8
3*8 + 4*8
```

We can also have that a program will terminate under CBN but not under CBV and vice versa. For instance:

```scala
def first(x: Int, y: Int) = x
first(1,loop)
//CBN
1
//CBV
first(1,loop)
.
.
.
.
```
Scala normally uses CBV. But if the type of a function parameter starts with a right arrow it will use CBN. To demonstrate this, we define the and function making sure its second argument is call by name:

```scala
def and(x: Boolean, y: => Boolean): Boolean =
if x then y else false 
```



## Week 2

### Higher order and anonymous functions

A higher order function is a function that takes another function as argument. Here is an example: 

Suppose we want to take the sum of all integers between some lower bound *a* and an upper bound *b*. We may recursively define

```scala
def sumInts(x:Int, y:Int):Int = 
	if (x > y) 0
	else x + sumInts(x+1,y)
```

We could also define the same but for cubes 

```scala
def sumCubes(x:Int, y:Int):Int = 
	if (x > y) 0
	else cube(x) + sumCubes(x+1,y)
```

However we could make the code more generic and define a general sum function that takes as first argument a function, namely the rule to apply and this way if we also wanted to define some ` sumFactorial` we could simply use the `sum` function. Consider

```scala
def sum(f: Int -> Int, a:Int, b:Int):Int = 
	if a > b then 0 
	else f(a) + sum(f, a+1, b)
```

Then we could easily define 

``` scala
def id(x: Int):Int = x
def sumInts(a: Int, b: Int):Int = sum(id, a, b)
```

Now notice how we tediously had to define an `id` function. Instead we may define `id` anonymously, it would then look like

``` scala
(x:Int) -> x 
```

and then define `sumInts` as 

``` scala
def sumInts(a: Int, b: Int):Int = sum((x:Int) -> x, a, b)
```

Anonymous functions are *syntatic sugar*, that is they make life nicer, but not really essential since we can always go the tedious def way. 

### Currying

The idea behind currying(named after Haskell Curry) is that we are able to desribe a function that takes multiple arguments as a composition of functions that all take one argument. The point of currying is that it takes a function and provides a new function with the parameter applied. For instance, we apply currying to find the product of the square of numbers in a given range as follows:

```scala
def product(f: Int => Int)(a:Int, b:Int): Int = 
	if a > b then 1 else f(a) * product(f)(a+1,b)

// function call
product(x => x*x)(1,5)
/**
will print 14400 = 4*9*16*25
*/
```

### Functions and Data

Suppose we want to define a *rational* type, we would say:

``` scala
class Rational(x:Int, y:Int): Rational
	def numer = x
	def denom = y 
```

 And we would add methods to our class as follows:

```scala
def addRational(r:Rational, s:Rational): Rational=
	Rational (
  r.numer*s.denom+s.numer*r.denom, r.denom*s.denom)
```



