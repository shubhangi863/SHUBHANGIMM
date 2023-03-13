package collection

abstract class Maybe[+T] {
def map[B](f:T=>B):Maybe[B]
def flatMap[B](f:T=>Maybe[B]):Maybe[B]
def filter(p: T=> Boolean):Maybe[T]
}

case object MayNot extends Maybe[Nothing]{
 def map[B](f:Nothing=>B):Maybe[B]= MayNot
  def flatMap[B](f: Nothing => Maybe[B]): Maybe[B] = MayNot
  def filter(p: Nothing => Boolean): Maybe[Nothing] = MayNot
}

case class Just[+T](value:T) extends Maybe[T]{
  def map[B](f: T => B): Maybe[B] = Just(f(value))
  def flatMap[B](f: T => Maybe[B]): Maybe[B] = f(value)
  def filter(p: T => Boolean): Maybe[T] =
    if(p(value)) this
    else MayNot
}

object MaybeTest extends App{
  val just3 =Just(3)
  println(just3)
  println(just3.map(_ * 2))
  println(just3.flatMap(x=>Just(x% 2 == 0)))
  println(just3.filter(_ %2==0))
}