package tutorial

/**
  * 線形化の補足
  *
  * linearization
  */

object Trait_Ex6 extends App {

  //すべてのtraitにsuperがあると親クラスからのメソッドが順に呼び出しされる。

  //superキーワードを使用することで親traitの関数を使用することもできる
  val a = new ClassA_Ex6
  a.greet()

  println("-----------")
  val b = new ClassB_Ex6
  b.greet()
}


trait TraitA_Ex6 {
  def greet(): Unit = println("Hello!")
}

trait TraitB_Ex6 extends TraitA_Ex6 {
  override def greet() = {
    super.greet()
    println("B_Good morning!")
  }
}

trait TraitC_Ex6 extends TraitA_Ex6 {
  override def greet() = {
    super.greet()
    println("C_Good evening!")

  }
}


class ClassA_Ex6 extends TraitB_Ex6 with TraitC_Ex6

class ClassB_Ex6 extends TraitC_Ex6 with TraitB_Ex6


/*
実行結果
Hello!
B_Good morning!
C_Good evening!
-----------
Hello!
C_Good evening!
B_Good morning!

Process finished with exit code 0
 */