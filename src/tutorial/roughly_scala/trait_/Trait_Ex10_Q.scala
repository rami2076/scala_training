package tutorial.roughly_scala.trait_


/**
  *
  *
  * 初期化が継承元から順に行われるためfoo が初期化される前の値を使用した文字列が出力されることに注意
  *
  *
  */

object Trait_Ex10_Q extends App {
  val ob = new C_Ex10
  ob.printBar()
}

trait A_Ex10 {
  val foo: String
}

trait B_Ex10 extends A_Ex10 {
  val bar = foo + "World"
}

class C_Ex10 extends B_Ex10 {
  val foo = "Hello"

  def printBar(): Unit = println(bar)
}


/*
結果：
nullWorld

Process finished with exit code 0

 */