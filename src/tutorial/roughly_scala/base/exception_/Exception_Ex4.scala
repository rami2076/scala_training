package tutorial.roughly_scala.base.exception_

/**
 *
 * Eitherを使用した例外処理
 * まだ使い方がわかっていない。
 *
 *
 */

object Exception_Ex4 extends App {
  either(1) match {
    case Right(x) => println("Success")
    case Left(x) => println("Fail")
  }

  either(2) match {
    case Right(x) => println("Success")
    case Left(x) => println("Fail")
  }

  def either(a: Int): Either[Throwable, String] = {
    if (a != 2) {
      Right(a.toString)
    } else {
      Left(new Exception)
    }
  }

}
