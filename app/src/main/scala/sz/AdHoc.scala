package sz

/**
  * scala99
  * Created by chengpohi on 8/25/16.
  */
case class User(name: String, id: Int)

trait Plus[A] {
  def plus(a1: A, a2: A): A
}

object AdHoc {

  implicit object PlusString extends Plus[String] {
    override def plus(a1: String, a2: String): String = s"""{"$a1": "$a2"}"""
  }

  implicit object PlusInt extends Plus[Int] {
    override def plus(a1: Int, a2: Int): Int = a1 + a2
  }

  implicit object PlusUser extends Plus[User] {
    override def plus(a1: User, a2: User): User = ???
  }

}

object PlusUsage extends App {
  trait PlusOps[T] {
    val p: Plus[T]
    val v: T
    def plus(t: T): T = {
      p.plus(v, t)
    }
  }

  implicit def toPlus[T: Plus](f: T)(implicit ps: Plus[T]) = new PlusOps[T] {
    override val p = ps
    override val v = f
  }
  import AdHoc._
  val res = "hello".plus("world")
  println(res)
}
