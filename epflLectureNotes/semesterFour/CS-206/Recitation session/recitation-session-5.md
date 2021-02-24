# Exercise 1 : Message Processing Semantics

Consider the following actor system:

```scala
case class Write(value: Int)
case class Read(requester: ActorRef)
case class Answer(value: Int)

class Memory extends Actor {
  var value = 0

  override def receive: Receive = {
    case Write(newValue) => value = newValue
    case Read(requester) => requester ! Answer(value)
  }
}

object Memory {
  def props: Props = Props(new Memory)
}

class Client1(memory: ActorRef) extends Actor {
  memory ! Write(1)
  memory ! Read(self)

  override def receive: Receive = {
    case Answer(value) => println(value)
  }
}

object Client1 {
  def props(memory: ActorRef): Props = Props(new Client1(memory))
}

object Example extends App {
  val system = ActorSystem("example")

  val memory = system.actorOf(Memory.props, "memory")
  val client = system.actorOf(Client1.props(memory), "client")
}
```

What are the possible values printed by the `println` command in the `Client1` actor? Why?
Now, consider the following system:

```scala
// Messages as before.

// Memory class as before.

class Proxy(memory: ActorRef) extends Actor {
  override def receive: Receive = {
    case message => memory ! message
  }
}

object Proxy {
  def props(memory: ActorRef): Props = Props(new Proxy(memory))
}

class Client2(memory: ActorRef, proxy: ActorRef) extends Actor {

  memory ! Write(1)  // XXX
  proxy ! Read(self) // XXX

  override def receive: Receive = {
    case Answer(value) => println(value)
  }
}

object Client2 {
  def props(memory: ActorRef, proxy: ActorRef): Props =
    Props(new Client2(memory, proxy))
}

object Example extends App {
  val system = ActorSystem("example")

  val memory = system.actorOf(Memory.props, "memory")
  val proxy  = system.actorOf(Proxy.props(memory), "proxy")
  val client = system.actorOf(Client2.props(memory, proxy), "client")
}
```

What are the possible values printed by the `println` command in the `Client2` actor? Why? Would the output be different if the commands annotated with `XXX` were issued in the other order? What if both messages are sent through the `Proxy` actor?

## Exercise 2 : The Josephus Problem

In this exercise, we will revisit the famous *Josephus problem*. In this problem, a group of soldiers trapped by the enemy decide to commit suicide instead of surrendering. In order not to have to take their own lives, the soldiers devise a plan. All soldiers are arranged in a single circle. Each soldier, when it is their turn to act, has to kill the next soldier alive next to them in the clockwise direction. Then, the next soldier that is still alive in the same direction acts. This continues until there remains only one soldier in the circle. This last soldier is the lucky one, and can surrender if he decides to. The *Josephus problem* consists in finding the position in the circle of this lucky soldier, depending on the number of soldiers.

In this exercise, you will implement a *simulation* of the mass killing of the soldiers. Each soldier will be modeled by an actor. Soldiers are arranged in a circle and when their turn comes to act, they kill the next alive soldier in the circle. The next soldier that is still alive in the circle should act next. The last soldier remaining alive does not kill himself but prints out its number to the standard output.

The code on the next page covers the creation of all actors and the initialisation of the system. Your goal is to implement the `receive` method of the actors.

*Hint:* Think about what state the soldier must have.

```scala
class Soldier(number: Int) extends Actor {

  // You goal is to implement this.
  def receive: Receive = ???
}

object Soldier {
  // The different messages that can be sent between the actors:

  // The recipient should die.
  case object Death

  // The recipient should update its next reference.
  case class Next(next: ActorRef)

  // The recipient should act.
  case object Act

  def props(number: Int): Props = Props(new Soldier(number))
}


object Simulation {
  // Initialization
  val system = ActorSystem("mySystem")

  def start(n: Int) {
    require(n >= 1)

    // Creation of the actors.
    val actors = Seq.tabulate(n) { (i: Int) =>
      system.actorOf(Soldier.props(i), "Soldier#" + i)
    }

    // Inform all actors of the next actor in the circle.
    for (i <- 0 to (n - 2)) {
      actors(i) ! Next(actors(i + 1))
    }
    actors(n - 1) ! Next(actors(0))

    // Inform the first actor to start acting.
    actors(0) ! Act
  }
}
```
