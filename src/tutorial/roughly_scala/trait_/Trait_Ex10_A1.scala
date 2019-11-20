package tutorial.roughly_scala

/**
  *
  *
  * 初期化が継承元から順に行われるためfoo が初期化される前の値を使用した文字列が出力されることに注意
  *
  * 上記の問題を解消するため際のlazyを使用した遅延初期化以外の解決方法
  *
  * 事前定義(Early Definitions)
  * 親クラスの初期化を実行する前に先に定義する記述方式
  *
  */

object Trait_Ex10_A1 extends App {
  val ob = new C_Ex10_A1
  ob.printBar()
}

trait A_Ex10_A1 {
  val foo: String
}

trait B_Ex10_A1 extends A_Ex10_A1 {
  //定数barを遅延初期化できる。
  lazy val bar = foo + "World"
}

class C_Ex10_A1 extends {
  //事前定義部
  val foo = "Hello"
} with B_Ex10_A1 {
  def printBar(): Unit = println(bar)
}


/*
結果：
HelloWorld

Process finished with exit code 0


 */