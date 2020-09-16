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


