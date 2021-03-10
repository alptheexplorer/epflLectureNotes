object week1 {

  /**
   * demonstration of a deadlock
   */
  class Account(private var amount: Int = 0) {
    def transfer(target: Account, n: Int) =
      this.synchronized {
        target.synchronized {
          this.amount -= n
          target.amount += n
        }
      }
  }

  def startThread(a: Account, b: Account, n: Int) = {
    val t = new Thread {
      override def run(): Unit = {
        for (i <- 0 until n) {
          a.transfer(b, 1)
        }
      }
    }
    t.start()
    t
  }

  def main(args: Array[String]): Unit = {
    val a1 = new Account(50000)
    val a2 = new Account(70000)

    val t = startThread(a1, a2, 15000)
    val s = startThread(a2, a1, 15000)
    t.join()
    s.join() // join waits until thread dies
  }


  /**
   * Deadlock: program never completes as the two threads wait for the other to finish without releasing monitor ownership
   */

  /**
   * resolving the deadlock
   */

  private val x = new AnyRef{}
  private var uidCount = 0L
  def getUniqueId(): Long = x.synchronized{
    uidCount = uidCount + 1
    uidCount
  }


  class AccountTwo(private var amount: Int = 0) {
    val uid = getUniqueId()
    private def lockAndTransfer(target: AccountTwo, n: Int) =
      this.synchronized {
        target.synchronized {
          this.amount -= n
          target.amount += n
        }
      }

    def transfer(target: AccountTwo, n: Int) =
      if (this.uid < target.uid) this.lockAndTransfer(target,n)
      else target.lockAndTransfer(this,-n)
  }






}