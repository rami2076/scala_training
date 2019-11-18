package tutorial

/**
  * 菱形継承問題の解決2
  *
  * 線形化
  * linearization
  */

object Trait_Ex5 extends App {
  //コンパイル成功->Ex4のときはコンパイル失敗したが今回は成功
  //上記は、override修飾子を付与したことで解決されている。

  val a = new ClassA_Ex5

  a.greet()


  val b = new ClassB_Ex5
  //継承順序を逆にすると呼び出される関数が変更される。
  b.greet()

  //継承関係が逆になったのは、override修飾子を付与したことで線形化(linearization)していると思われる。
  //線形化すると、継承関係に優先度が働く。
  //定義順序が早い方が優先度が高くなっている。


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



