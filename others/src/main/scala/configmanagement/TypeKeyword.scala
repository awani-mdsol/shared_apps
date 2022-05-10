package configmanagement

import cats.data.EitherT

import java.time.LocalDate
import scala.concurrent.Future

object TypeKeyword1 extends App {

   type Row = List[Int]
   type Matrix = List[Row]

  def Row(xs: Int*): Row = List(xs: _*)
  def Matrix(xs: Row*): Matrix = List(xs: _*)

  val matrix = Matrix(
    Row(1,2,3),
    Row(4,5,6)
  )
  println(matrix)
  println(matrix.getClass)
}

object TypeKeyword2 extends App {

  trait Base {
    type T
    def method: T
  }

  class Implementation extends Base {
    type T = Int
    def method: T = 32
  }
  println(new Implementation().method)
  //also,  Type Alias for Function Types
  type FunctorType = (LocalDate, Int, Boolean) => LocalDate

  //Type declaration is a Scala feature that enables us to declare our own types
  type positive = Int
 //
  // CTE, def f:positive = "hi"
  def f: positive = 10

}

object TypeKeyword3 extends App

object TypeKeyword4 extends App {
  val s: Right[Int, String] = Right("str")

  trait GenericException extends Exception {
    def message: String
  }

  trait MonixException extends GenericException


  type EitherF[String, Int] = EitherT[Future, String, Int]
  type FinalResult[Int] = EitherF[MonixException, Int]

  def method(x: Int): EitherT[Future, String, Int] = {
    val e: Either[String, Int] = Right(100)
    val f: Future[Either[String, Int]] = Future.successful(e)

    val x: EitherT[Future, String, Int] = EitherT(f)
    x
  }

  def method2: EitherF[String, Int] = {
    val e: Either[String, Int] = Right(100)
    val f: Future[Either[String, Int]] = Future.successful(e)

    val x: EitherT[Future, String, Int] = EitherT(f)
    x
  }

  def method3: FinalResult[Int] = {
    val e = if(true) {
      Right(100)
    } else {
      Left(new MonixException {
        override def message: String = ""
      })
    }
    val f: Future[Either[MonixException, Int]] = Future.successful(e)

    val x: EitherT[Future, MonixException, Int] = EitherT(f)
    x
  }

  println(method3)
  println(method3.getClass)

  val tuple: Tuple3[Int, Boolean, String] = (10, true, "hello")
  type Tuple2[Int, Boolean] = Tuple3[Int, Boolean, String]
}