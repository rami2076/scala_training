package tutorial.roughly_scala.base.exception_

import scala.util.{Failure, Success, Try}

/**
 *
 * Try [T]を使用した記述
 * Try{}で値を包める。
 * https://futurismo.biz/archives/3645
 *
 *
 * 以下の方が詳しい
 * http://www.mwsoft.jp/programming/scala/scala_try.html
 */


object TryCatch extends App {


  //型によるパターンマッチで評価できる。
  val result = divide(0)
  result match {
    case Success(v) => show(v)
    case Failure(e) => println("Failure!!")
  }

  val result2 = divide2(0)
  result match {
    case Success(v) => show(v)
    case Failure(e) => println("Failure!!")
  }


  def divide(num: Int): Try[Double] = Try {
    val result = Try {
      1 / num
    }
    result match {
      case Success(v) => v
      case Failure(e) =>
        println("Failure!!")
        throw e
    }
  }

  //Tryで囲むことで例外時に自動的にFailureで囲んでくれる。
  def divide2(num: Int): Try[Double] = Try {
    1 / num
  }


  def show(result: Double): Try[Double] = Try {
    println(result)
    result
  }
}