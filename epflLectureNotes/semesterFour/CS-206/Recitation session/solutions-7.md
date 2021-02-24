# Exercise 1

## Question 1.1

### Part 1

```scala
val planContracts: RDD[(Int, Int)] =
  contracts.map(c => (c.planId, c.contractId))
val planPrices: RDD[(Int, Double)] =
  plans.map(p => (p.planId, p.price))
val contractPrices: RDD[(Int, Double)] =
  planContracts.join(planPrices).values
val contractBrokers: RDD[(Int, Int)] =
  contracts.map(c => (c.contractId, c.brokerId))
val brokerContractPrices: RDD[(Int, Double)] =
  contractBrokers.join(contractPrices).values
val brokerIdRevenues: RDD[(Int, Double)] =
  brokerContractPrices.reduceByKey(_ + _)
val brokerNames: RDD[(Int, String)] =
  brokers.map(b => (b.brokerId, b.fullName))
val brokerNameRevenues: RDD[(String, Double)] =
  brokerNames.leftOuterJoin(brokerIdRevenues)
             .values
             .mapValues(_.getOrElse(0.0))
```

### Part 2

```scala
val low20 = brokerNameRevenue
  .top((brokers.count * 0.2).toInt)(Ordering.by(_._2).reverse)
```

## Question 1.2

### Part 1

```scala
val planYear: RDD[(Int, Int)] =
  plans.map(p => (p.planId, p.yearIntroducedIn))
val planClient: RDD[(Int, Int)] =
  contracts.map(c => (c.planId, p.clientId))
val clientYear: RDD[(Int, Int)] =
  planClient.join(planYear).values
val clientLatestYear: RDD[(Int, Int)] =
  clientYear.reduceByKey(_ max _)
val clientNames: RDD[(Int, String)] =
  clients.map(c => (c.clientId, c.fullName))
val upsellClientNames: RDD[String] =
  clientLatestYear.filter(p => 2019 - p._2 > 10)
                  .join(clientNames)
                  .values
```

### Part 2

```scala
val contractClient: RDD[(Int, Int)] = ... // similar to above
val contractPlan: RDD[(Int, Plan)] =
  contracts.map(c => (c.planId, c.contractId))
           .join(plans.map(p => (p.planId, p)))
           .values
val clientPlan: RDD[(Int, Plan)] =
  contractClient.join(contractPlan).values
val clientUpsells: RDD[(Int, Plan)] =
  clientLatestPlan.cartesian(plans).filter {
    case ((clientId, clientPlan), newPlan) =>
      newPlan.price > clientPlan.price
  }.map {
    case ((clientId, clientPlan), newPlan) =>
      (clientId, newPlan)
  }
val clientCheapeastUpsell: RDD[(Int, Plan)] =
  clientUpsells.reduceByKey {
    case (p1, p2) if p1.price < p2.price => p1
    case (_, p2)                         => p2
  }
val clientNames: RDD[(Int, String)] =
  clients.map(c => (c.clientId, c.fullName))
val upsellSuggestion: RDD[(String, Plan)] =
  clientCheapeastUpsell.join(clientNames).values
```
