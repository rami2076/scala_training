package tutorial.roughly_scala.base.exception_

/**
 * Scalaの例外はすべて非検査例外なのでどんな例外でも例外処理を強制されることはない。
 */

object Exception_Ex2 extends App {

  myFunc()


  def myFunc(): Unit = {
    throw new Exception
  }

}
