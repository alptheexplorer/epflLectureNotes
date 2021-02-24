# Exercise 1 : Introduction to Concurrency

## Question 1

Yes

## Question 2

#### Property 1 holds

Assuming that the execution of two concurrent threads only interleaves instructions and that reads and writes are executed atomically, it can be shown that property 1 always holds. Unfortunately, such strong guarantees are not offered by the Java Memory Model. If you are interested, have a look at the note below on the Java Memory Model.

#### Violation of property 2

Consider 2 threads that execute concurrently `transfer(from, to, amount)` with the exact same parameters. Assume that the account from has sufficient funds for at least one transfer.

Thread 1 executes until it has computed the value balanceFrom - amount and then stops. Thread 2 then executes in its entirety the call to `transfer(from, to, amount)`. Then thread 1 resumes its execution and completes the call to `transfer`.

At the end of this execution, the total amount of money held by the bank has changed. It has in fact increased by the value amount.

#### Note on the Java Memory Model

Assuming the Java Memory Model, both of the two properties can potentially be violated. Indeed, the model only ensures that the execution of each thread appears sequential to the thread itself, and not to any other concurrently running threads. Seemingly atomic instructions can be arbitrarily decomposed by the underlying virtual machine. Sequences of instructions can also be reordered at will by the VM, as long as the execution of a single thread appears as if it were executed sequentially. In these settings, both properties can be violated.

## Question 3

Variant 1

In this variant, property 2 can be violated. It is not vulnerable to deadlocks.

Variant 2

In this variant, none of the two properties can be violated. However, it is susceptible to deadlocks.

Variant 3

In this last variant, none of the two properties can be violated and no deadlock can occur. It is however still not entirely satisfactory, since no two threads can execute transfers in parallel, even when the accounts are totally disjointed. Can you think of a better solution?

## Question 1

```scala
def minMax(a: Array[Int]): (Int, Int) = {
  val threshold = 10

  def minMaxPar(from: Int, until: Int): (Int, Int) = {

    if (until - from <= threshold) {
      var i = from
      var min = a(from)
      var max = a(from)

      while (i < until) {
        val x = a(i)
        if(x < min) min = x
        if(x > max) max = x
        i = i + 1
      }

      (min, max)
    } else {
      val mid = from + ((until - from) / 2)
      val ((xMin, xMax),
           (yMin, yMax)) = parallel(minMaxPar(from, mid),
                                    minMaxPar(mid, until))
      (min(xMin, yMin), max(xMax, yMax))
    }
  }

  minMaxPar(0, a.size)
}
```

## Question 2

```scala
def minMax(data: ParSeq[Int]): (Int, Int) = data.map({
  (x: Int) => (x, x)
}).reduce({
  case ((mn1, mx1), (mn2, mx2)) => (min(mn1, mn2), max(mx1, mx2))
})
```

Or:

```scala
def minMax(data: ParSeq[Int]): (Int, Int) =
  (data.reduce(min), data.reduce(max))
```

## Question 3

The function `f` must be associative. That is, for any `x`, `y`, `z`, it should be the case that:

```
f(x, f(y, z)) == f(f(x, y), z).
```

Both the `min` and `max` functions are associative. In addition, it can be easily shown that pairwise application of associative functions is also associative. From this follows that `f` is indeed associative.
