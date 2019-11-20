package tutorial.roughly_scala

/**
  *
  *
  * 初期化が継承元から順に行われるためfoo が初期化される前の値を使用した文字列が出力されることに注意
  *
  * 上記の問題を解消するためにlazyキーワードを使用する。
  *
  * - 定数の遅延初期化は処理性能が落ちる。
  * - 複雑な呼び出し処理の場合にデッドロックが発生する可能性がある
  *
  * defを使用すると毎回計算をするため、valを用いてvalを使用する場合は利用を検討すること
  *
  *
  */

object Trait_Ex10_A extends App {
  val ob = new C_Ex10_A
  ob.printBar()
}

trait A_Ex10_A {
  val foo: String
}

trait B_Ex10_A extends A_Ex10_A {
  //定数barを遅延初期化できる。
  lazy val bar = foo + "World"
}

class C_Ex10_A extends B_Ex10_A {
  val foo = "Hello"

  def printBar(): Unit = println(bar)
}


/*
結果：
HelloWorld

Process finished with exit code 0


 */