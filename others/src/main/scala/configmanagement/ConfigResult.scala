package configmanagement

import cats.data.EitherT

import scala.concurrent.{ExecutionContext, Future}

trait SomeException extends Exception {
  def message: String
}
object ConfigResult extends App {

  type EitherF[A, B] = EitherT[Future, A, B]
  type ConfigResult[A] = EitherF[SomeException, A]

  implicit def fromFuture[A](fa: Future[Either[SomeException, A]]): ConfigResult[A] =
    EitherT(fa)

  implicit def fromEither[A](either: Either[SomeException, A]): ConfigResult[A] =
    fromFuture(Future.successful(either))

  implicit class ToConfigResult[A](val a: A) extends AnyVal {
    def toConfigResult: ConfigResult[A] = fromEither(Right(a))
  }

  implicit class ConfigResultOps[A](val c: ConfigResult[Either[SomeException, A]]) extends AnyVal {
    def flatten(implicit ec: ExecutionContext): ConfigResult[A] =
      c.flatMap(fromEither)
  }

}