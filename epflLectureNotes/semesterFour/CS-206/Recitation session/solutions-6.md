# Exercise 1 : Spark Fundamentals

## Question 1

```scala
rdd.map(text => text.split(" ").length).reduce(_ + _)
```

## Question 2

```scala
val errorLogs = rawLogs.map(toLog(_)).filter(isError(_)).cache()
val numberErrors = errorLogs.count()
val messages = errorLogs.filter(isRecent).map(message(_)).collect()
```

## Question 3

In the first case, all the strings are displayed on the master. In the second case, records are displayed on the standard output of the worker nodes.

## Question 4

In the case of 2., the map operations are pipelined and only applied to 10 elements.

# Exercise 2 : Demographics


## Question 1

```scala
val adultAges = people.map(_.age).filter(_ >= 18).cache()
groups.map {
  case (lower, upper) => adultAges.filter { (age: Int) =>
    lower <= age && age <= upper
  }.count()
}
```

## Question 2

```scala
val groups: Array[(Int, Int)] =
  people.map(_.age)
        .filter(_ >= 18)
        .map((age: Int) => (groupOf(age), 1))
        .reduceByKey(_ + _)
        .collect()

// Now, we have a small collection
// and can work on a single machine.
groups.sortBy(_._1)
      .map(_._2)
      .toList()
```
