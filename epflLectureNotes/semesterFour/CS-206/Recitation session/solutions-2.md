# Exercise 1 : Aggregate

## Question 1

- g(f(z, x1), f(f(z, x2), x3))
- g(f(f(z, x1), x2), f(z, x3))
- g(g(f(z, x1), f(z, x2)), f(z, x3))
- g(f(z, x1), g(f(z, x2), f(z, x3)))

## Question 2

Variant 1

This might lead to different results.

Variant 2

This might lead to different results.

Variant 3

This always leads to the same result.

Variant 4

This always leads to the same result.

## Question 3

A property that implies the correctness is:

```
forall xs, ys.   g(xs.F, ys.F) == (xs ++ ys).F   (split-invariance)
```

where we define

```
xs.F == xs.foldLeft(z)(f)
```

The intuition is the following. Take any computation tree for
`xs.aggregate`. Such a tree has internal nodes labelled by g and segments processed using `foldLeft(z)(f)`. The split-invariance law above says that any internal g-node can be removed by concatenating the segments. By repeating this transformation, we obtain the entire result equals `xs.foldLeft(z)(f)`.

The split-invariance condition uses `foldLeft`. The following two conditions together are a bit simpler and imply split-invariance:

```
forall u. g(u,z) == u                       (g-right-unit)
forall u, v. g(u, f(v,x)) == f(g(u,v), x)   (g-f-assoc)
```

Assume g-right-unit and g-f-assoc. We wish to prove split-invariance. We do so by induction on the length of `ys`. If ys has length zero, then `ys.foldLeft` gives `z`, so by g-right-unit both sides reduce to xs.foldLeft. Let `ys` have length `n>0` and assume by I.H. split-invariance holds for all `ys` of length strictly less than `n`. Let `ys == ys1 :+ y` (that is, y is the last element of `ys`). Then

```
g(xs.F, (ys1 :+ y).F) == (foldLeft definition)
g(xs.F, f(ys1.F, y))  == (by g-f-assoc)
f(g(xs.F, ys1.F), y)  == (by I.H.)
f((xs++ys1).F, y)     == (foldLeft definition)
((xs++ys1) :+ y).F    == (properties of lists)
(xs++(ys1 :+ y)).F
```

## Question 4

```scala
def aggregate[B](z: B)(f: (B, A) => B, g: (B, B) => B): B =
  if (this.isEmpty) z
  else this.map((x: A) => f(z, x)).reduce(g)
```

## Question 5

```scala
def aggregate(z: B)(f: (B, A) => B, g: (B, B) => B): B = {

  def go(s: Splitter[A]): B = {
    if (s.remaining <= THRESHOLD)
      s.foldLeft(z)(f)
    else {
      val splitted = s.split

      val subs = splitted.map((t: Splitter[A]) => task { go(t) })
  subs.map(_.join()).reduce(g)
    }
  }

  go(splitter)
}
```

## Question 6

The version from question 4 may require 2 traversals (one for `map`, one for `reduce`) and does not benefit from the (potentially faster) sequential operator `f`.

# Exercise 2 : Depth

## Question 1

Somewhat counterintuitively, the property doesn't hold. To show this, let's take the following values for *L1*, *L2*, *T*, *c*, and *d*.

```
L1 = 10, L2 = 12, T = 11, c = 1, and d = 1.
```

Using those values, we get that:

```
D(L1) = 10
D(L2) = max(D(6), D(6)) + 1 = 7
```

## Question 2

*Proof sketch*

Define the following function D'(L).

![](images/2-2.png)

Show that *D(L) ≤ D'(L)* for all *1 ≤ L*.

Then, show that, for any *1 ≤ L1 ≤ L2* we have *D'(L1) ≤ D'(L2)*. This property can be shown by induction on *L2*.

Finally, let *n* be such that *L ≤ 2n < 2L*. We have that:

```
D(L) ≤ D'(L)                     Proven earlier.
     ≤ D'(2n)                    Also proven earlier.
     ≤ log2(2n) (d + cT) + cT
     < log2(2L) (d + cT) + cT
     = log2(L) (d + cT) + log2(2) (d + cT) + cT
     = log2(L) (d + cT) + d + 2cT
```

Done.
