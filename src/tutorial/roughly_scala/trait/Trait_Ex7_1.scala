package tutorial.roughly_scala

/**
  *
  * Ex7の続き
  *
  * http://dwango.github.io/scala_text_previews/sbt-tut/trait.html
  */

object Trait_Ex7_1 extends App {

}

//関数定義
trait Trait_Ex7_A_1 {
  def greet(): Unit
}


trait Trait_Ex7_Child_B_1 extends Trait_Ex7_A_1 {
  def greet(): Unit = println("Hello!")

}


trait Trait_Ex7_Child_C_1 extends Trait_Ex7_A_1 {
  abstract override def greet(): Unit = {
    super.greet()
    println("I like niconico.")
  }
}

//コンパイルエラー
//class Class_Ex7_Child_A_1 extends Trait_Ex7_Child_C_1

//エラーメッセージ
//Class 'Class_Ex7_Child_A_1' needs to be mixin, since member 'greet'
// in 'Trait_Ex7_Child_C_1' is marked abstract and override,
// but no concrete implementation could be found in a base class


class Class_Ex7_Child_B_1 extends Trait_Ex7_Child_B_1 with Trait_Ex7_Child_C_1