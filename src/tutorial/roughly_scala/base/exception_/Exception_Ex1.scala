package tutorial.roughly_scala.base.exception_

/**
 *
 * try catch を利用した例外処理
 *
 *
 * try-catchを式として扱える。
 * throw句を付ける必要がない。
 *
 * try-catchの結果は以下のようになる。
 * catch節の中でmatchしなかった場合は呼び出し元に再スローされる。
 * try節で例外が発生しなかった場合、戻り値はtry節の中の最後に評価された値。
 * try節で例外が発生した場合、catch節で最後に評価された値。
 *
 * finally句について
 * finally節内で評価された式はtry-catch句の結果に影響を与えない。
 *
 *
 *
 *
 */

object Exception_Ex1 extends App {
  val r = try {
    funcC
    funcA()
    //throw new Exception
  } catch {
    case e: Exception => e
    case _ => println("Hello")
  } finally {
    println("C")
    99
  }
  println(r.getClass)


  /**
   *
   * JavaからScalaを呼びたい場合には、明示的にアノテーションを付与することでJavaコードにthrow句が付与され例外が解決される。
   *
   * @throws java.lang.Exception
   * @return
   */
  @throws(classOf[Exception])
  def funcA() = throw new Exception("a")

  /**
   * どのクラスを例外としてスローするかについて記述は不要、
   *
   * @return
   */
  def funcB() = throw new Exception("a")

  def funcC() = new Exception("b")
}
