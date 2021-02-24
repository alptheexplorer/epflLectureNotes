# Exercise 1 : Federal Statistical Office

## Question 1.1

A lot of unnecessary communication happens on the cluster. Data is moved over the network from nodes to other nodes, which is called "shuffling". Network communication is extremely slow relatively to other operations.

## Question 1.2

```scala
people.map(p => (p.age, p.salary))
      .mapValues(s => (1, s, s * s))
      .reduceByKey {
        case ((n1, s1, ss1), (n2, s2, ss2)) =>
          (n1 + n2, s1 + s2, ss1 + ss2)
      }
      .mapValues {
        case (n, sum, sumSquares) =>
          val mean = sum / n
          val variance = sumSquares / n - mean * mean
          (mean, Math.sqrt(variance).toLong)
      }
```

Note the use of `reduceByKey` instead of `groupByKey`. Also, `mapValues` is used whenever possible, which preserves the partitioner, if any. This will be useful later on.

To see the difference in running time between `reduceByKey` and `groupByKey`, you may have a look at the following talk: https://www.youtube.com/watch?v=0KGGa9qX9nw (slides: https://www.slidesearch.net/slide/beyond-shuffling-scala-days-berlin-2016).


## Question 1.3

```scala
// Code to partition the data before processing
val pairs = people.map(p => (p.age, p.salary))
val nPartitions = 32  // 8 machines, 4 cores each
val tunedPartitioner = new RangePartitioner(nPartitions, pairs)
val partitioned = pairs.partitionBy(tunedPartitioner).persist()


// NOTE: Once partitioned, we can compute mean and variance as before,
// using the partitioned RDD.
partitioned.mapValues(s => (1, s, s * s))
           .reduceByKey {
             case ((n1, s1, ss1), (n2, s2, ss2)) =>
               (n1 + n2, s1 + s2, ss1 + ss2)
           }
           .mapValues {
             case (n, sum, sumSquares) =>
               val mean = sum / n
               val variance = sumSquares / n - mean * mean
               (mean, Math.sqrt(variance).toLong)
           }
```

# Exercise 2 : Partitioners

## Question 2.1

Partitioners specify which keys are hosted by the different partitions. Repartitioning in useful for example for:

1. Improving data locality, and thus avoiding network shuffles.
2. Balance the work between the different partitions.

## Question 2.2

The transformations that preserve partitioners are:

- `mapValues`
- `filter`
- `flatMapValues`
- `join`
- `reduceByKey`
- `groupByKey`

(`map` and `flatMap` do not preserve partitioners)

The partitioner can be preserved because the set of keys held by a partition in the resulting RDD is a subsets of the keys the partition held in the parent RDD. Therefore the partitioner still faithfully describes where the different keys are held.

## Question 2.3

The transformations that introduce partitioners are:

- `join`
- `reduceByKey`
- `groupByKey`

(`map`, `mapValues`, `filter`, `flatMap` and `flatMapValues` do not introduce partitioners)

## Question 2.4

A `HashPartitioner` partitions the data according to the hashcode of the key, while a `RangePartitioner` partitions the data according to an ordering on the keys.

`HashPartitioners` generally split the work evenly between the different partitions. This partitioners does not require a specific ordering to exist on the keys.

`RangePartitioners` allow grouping keys in the same range on the same partition. This can be useful to further improve data locality. In some cases, as seen in the video lecture, it also allows for better work balancing.
