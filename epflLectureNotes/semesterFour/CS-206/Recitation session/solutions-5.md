# Exercise 1 : Message Processing Semantics

For the `Client1` actor, the only possible output is 1. The reason is that messages between two actors are guaranteed to be received in the order they were sent.

For the `Client2` actor, either 0 or 1 can be printed. There are no restrictions on the order in which messages are processed in this case. It might be the case that the `Memory` actor receives the `Write` message from the `Client2` first, or the Read message from the `Proxy` first. The order in which the messages are sent by the `Client2` doesn't change the possible behaviours of the system. In the case both messages are sent through the `Proxy`, then the only possible output is 1, since in this case the messages between the `Client2` and `Proxy`, as well as between `Proxy` and `Memory`, are guaranteed to be handled in the same order they were sent.

## Exercise 2 : The Josephus Problem

```scala
import akka.actor._

class Soldier(number: Int) extends Actor {

  import Soldier._

  def receive: Receive = behavior(None, None, false)

  def behavior(next: Option[ActorRef],
               killer: Option[ActorRef],
               mustAct: Boolean): Receive = {

    case Death => next match {
      case Some(myNext) =>
        sender ! Next(myNext)
        myNext ! Act
        println("Soldier " + number + " dies.")
        self ! PoisonPill

      case None =>
        context.become(behavior(
          next = None,
          killer = Some(sender),
          mustAct = mustAct))
    }

    case Next(newNext) =>
      if (newNext == self) {
        println("Soldier " + number + " is last !")
      } else if (!killer.isEmpty) {
        killer.get ! Next(newNext)
        newNext ! Act
        println("Soldier " + number + " dies.")
        self ! PoisonPill
      } else if (mustAct) {
        newNext ! Death
        context.become(behavior(
          next = None,
          killer = None,
          mustAct = false))
      } else {
        context.become(behavior(
          next = Some(newNext),
          killer = None,
          mustAct = false))
      }
    }


    case Act => next match {
      case Some(myNext) =>
        myNext ! Death
        context.become(behavior(
          next = None,
          killer = killer,
          mustAct = false))

      case None =>
        context.become(behavior(
          next = None,
          killer = killer,
          mustAct = true))
    }
  }
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

  import Soldier._

  // Initialization
  val system = ActorSystem("mySystem")

  def start(n: Int) {
    require(n >= 1)

    // Creation of the actors.
    val actors = Seq.tabulate(n) { (i: Int) =>
      system.actorOf(Soldier.props(i), "Soldier" + i)
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
