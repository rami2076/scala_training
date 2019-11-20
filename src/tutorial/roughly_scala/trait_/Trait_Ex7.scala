package tutorial.roughly_scala

/**
  *
  * abstract overrideについて
  *
  * superクラスのメソッドを呼び出す場合、
  * 親クラスのメソッドが実装済みである場合に実行できるが
  * Scalaは親クラスの実装が無い場合でも関数のオーバライドが可能なabstract overideという機能がある。
  *
  *
  * 以下のエラーメッセージが表示される。
  * Error:(29, 11) method greet in trait Trait_Ex7_A is accessed from super. It may not be abstract unless it is overridden by a member declared `abstract' and `override'
  *     super.greet()
  *
  *
  * 抽象クラスや抽象メンバーを同じ構文規則で扱うことができるメリットがある。
  * http://dwango.github.io/scala_text_previews/sbt-tut/trait.html
  */

object Trait_Ex7 extends App {

}

//未実装関数定義
//親trait
trait Trait_Ex7_A {
  def greet(): Unit
}

//override
//子trait コンパイルが通らない。
//trait Trait_Ex7_Child_B extends Trait_Ex7_A {
//  override def greet(): Unit = {
//    super.greet()
//    println("Good morning!")
//  }
//}

//abstract override
//子trait　コンパイルが通る
trait Trait_Ex7_Child_C extends Trait_Ex7_A {
  abstract override def greet(): Unit = {
    super.greet()
    println("Good evening!")
  }
}



