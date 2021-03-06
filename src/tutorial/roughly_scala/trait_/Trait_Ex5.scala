package tutorial.roughly_scala.trait_


/**
  * 菱形継承問題の解決2
  *
  * 線形化
  * linearization
  * "線形化によるトレイトの積み重ねの処理をScalaの用語では積み重ね可能なトレイト（Stackable Trait）と呼ぶことがあります。"
  * http://dwango.github.io/scala_text_previews/sbt-tut/trait.html
  */

object Trait_Ex5 extends App {
  //コンパイル成功->Ex4のときはコンパイル失敗したが今回は成功
  //上記は、override修飾子を付与したことで解決されている。

  val a = new ClassA_Ex5
  a.greet()

  println("-----------")

  val b = new ClassB_Ex5
  //継承順序を逆にすると呼び出される関数が変更される。
  b.greet()

  //継承関係が逆になったのは、override修飾子を付与したことで線形化(linearization)していると思われる。
  //線形化すると、継承関係に優先度が働く。
  //定義順が右にある方が優先度が高くなる。


}


trait TraitA_Ex5 {
  def greet(): Unit
}

trait TraitB_Ex5 extends TraitA_Ex5 {
  override def greet() = println("Good morning!")
}

trait TraitC_Ex5 extends TraitA_Ex5 {
  override def greet() = println("Good evening!")
}


class ClassA_Ex5 extends TraitB_Ex5 with TraitC_Ex5

class ClassB_Ex5 extends TraitC_Ex5 with TraitB_Ex5


/*
実行結果

Good evening!
-----------
Good morning!

Process finished with exit code 0

 */