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
