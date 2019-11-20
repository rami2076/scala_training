package test.roughly_scala.`trait`

import tutorial.roughly_scala.{Trait_Ex1, Trait_Ex1_1}

.trait

object Test_Trait_Ex1 extends App {


  //https://docs.scala-lang.org/tour/traits.html

  var trait_instance: Trait_Ex1 = null
  //直接インスタンス化できない
  //trait_instance = new Trait_Ex1

  //一つのファイルに複数のtraitを定義(Define)可能
  var trait_Ex1_1_instance: Trait_Ex1_1 = null
  //当然インスタンス化できない
  //trait_instance = new Trait_Ex1_1


}
