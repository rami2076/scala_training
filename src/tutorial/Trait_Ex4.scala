package tutorial

/**
  * 菱形継承問題について
  * コンフリクトした場合基本的には例外となる。　
  *
  */

object Trait_Ex4 extends App {
  //コンパイルは通るが、メンバーがコンフリクトしている場合、例外が発生する。
  //  val classA = new ClassA
  // classA.greet()

  val classA_1: A_resolve1_class = new A_resolve1_class
  classA_1.greet()

  val classA_2: A_resolve2_class = new A_resolve2_class
  classA_2.greet()

}


trait TraitA {
  def greet(): Unit
}

trait TraitB extends TraitA {
  def greet(): Unit = println("Good morning!")
}

trait TraitC extends TraitA {
  def greet(): Unit = println("Good evening!")
}


/*
コンフリクトするため実行時エラーが発生する。
class ClassA extends TraitB with TraitC

Error:(27, 7) class ClassA inherits conflicting members:
  method greet in trait TraitB of type ()Unit  and
  method greet in trait TraitC of type ()Unit
(Note: this can be resolved by declaring an override in class ClassA.)
class ClassA extends TraitB with TraitC
 */


//解決方法　エラーメッセージにあるようにクラスでコンフリクトしているメンバーを　overrideすれば解決できる
class A_resolve1_class extends TraitB with TraitC {
  //overrideしてコンフリクトを解決する方法1
  override def greet(): Unit = println("How are you?")
}


//親クラスで定義済みの関数を再利用することが可能
class A_resolve2_class extends TraitB with TraitC {
  //overrideしてコンフリクトを解決する方法1
  override def greet(): Unit = super[TraitB].greet()

}

//その他
//親クラスで定義済みの関数を再利用することが可能
class A_resolve3_class extends TraitB with TraitC {
  //overrideしてコンフリクトを解決する方法1
  override def greet(): Unit = {
    //両方呼び出すことも可能
    super[TraitB].greet()
    super[TraitC].greet()
  }

}