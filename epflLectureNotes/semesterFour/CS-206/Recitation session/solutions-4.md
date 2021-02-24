# Exercise 1 : Implementing map and filter on Futures

```scala
trait Future[T] { self =>
  def map[S](f: T => S): Future[S] =
    new Future[S] {
      def onComplete(callback: Try[S] => Unit): Unit = self.onComplete {
        case Success(v) => callback(Success(f(v)))
        case Failure(e) => callback(Failure(e))
  }
    }

  def filter(f: T => Boolean): Future[T] =
    new Future[T] {
      def onComplete(callback: Try[T] => Unit): Unit = self.onComplete {
        case Success(v) =>
          if (f(v)) callback(Success(v))
          else callback(Failure(new NoSuchElementException("...")))
        case Failure(e) => callback(Failure(e))
  }
    }
}
```

# Exercise 2 : Coordinator / Worker

```scala
class Coordinator extends Actor {
  var availableWorkers: List[ActorRef] = Nil
  var pendingRequests: List[Request] = Nil

  def receive: Receive = {
    case Ready =>
      if (pendingRequests.isEmpty) {
        availableWorkers = availableWorkers :+ sender
      }
      else {
        val request = pendingRequests.head
        pendingRequests = pendingRequests.tail
        sender ! request
      }
    case request: Request => availableWorkers match {
      case worker :: rest =>
        worker ! request
        availableWorkers = rest
      case Nil =>
        pendingRequests = pendingRequests :+ request
    }
  }
}

class Worker(coordinator: ActorRef) extends Actor {
  coordinator ! Ready

  def receive: Receive = {
    case Request(f) =>
      f()
      coordinator ! Ready
  }
}
```
