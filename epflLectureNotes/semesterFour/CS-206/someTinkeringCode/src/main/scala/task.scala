import jdk.internal.org.jline.utils.ShutdownHooks.Task

object task {
  /**
   * exploring the task construct
   */

  def task(c: => A) : Task[A]

  trait Task[A] {
    def join: A
  }

  // implicit def gets called automatically by compiler
  implicit def getJoin[T](x:Task[T]): T = x.join


  //defining parallel using task
  def parallel[A,B](cA: => A, cB: => B): (A,B) = {
    val tB: Task[B] = task{cB}
    val tA: A =cA
    // compute A normally and B in parallel then join
    (tA,tB.join)
  }


}
