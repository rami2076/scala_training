package test.roughly_scala.trait_

import tutorial.roughly_scala.trait_.{Trait_Ex2, Trait_Ex2_1}

object Test_Trait_Ex2 extends App {


  //https://docs.scala-lang.org/tour/traits.html

  //インスタンス化可能:型はtrait。Javaのinterfaceのように扱える。
  val trait_Ex2_instance: Trait_Ex2 = new Trait_Ex2_Class
  trait_Ex2_instance.en


  var trait_Ex2_1_instance: Trait_Ex2_1_Class = null
  trait_Ex2_1_instance = new Trait_Ex2_1_Class
  //定義済みの関数が呼び出された
  trait_Ex2_1_instance.en
  //フィールドの定義が可能
  trait_Ex2_1_instance.state
  //実装されたメソッドの呼び出しが可能
  trait_Ex2_1_instance.jp
  //traitで継承したメソッド呼び出しが可能
  trait_Ex2_1_instance.fr

  //型をキャスト可能
  val trait_Ex2_1_instance_a: Trait_Ex2 = trait_Ex2_1_instance
  //traitされた型には再代入可能
  trait_Ex2_1_instance_a.en
  //方が異なるので呼び出しは不可
  //trait_Ex2_1_instance_a.fr

  //型のキャスト可能
  val trait_Ex2_1_instance_b: Trait_Ex2_1 = trait_Ex2_1_instance
  trait_Ex2_1_instance_b.en
  trait_Ex2_1_instance_b.state
  trait_Ex2_1_instance_b.jp
  trait_Ex2_1_instance_b.fr


  //型のキャスト可能
  val trait_Ex2_1_instance_c: Trait_Ex2_1 = trait_Ex2_1_instance_a.asInstanceOf[Trait_Ex2_1_Class]
  trait_Ex2_1_instance_b.en
  trait_Ex2_1_instance_b.state
  trait_Ex2_1_instance_b.jp
  trait_Ex2_1_instance_b.fr

  //型のキャスト不可
  val trait_Ex2_1_instance_d: Trait_Ex2_1 = trait_Ex2_1_instance_a.asInstanceOf[Trait_Ex2_1]

  //型を戻すことが可能
  val t: Trait_Ex2_1_Class = trait_Ex2_1_instance_b.asInstanceOf[Trait_Ex2_1_Class]
  t.en
  t.jp
  t.state
  t.fr

  //同じキーワードを継承しているが、型が異なると判別されjava.lang.ClassCastException
  //val d : D = t.asInstanceOf[D]

}

//mix-in
//クラスに対してmix-inが可能
//未継承の場合extendsキーワードでmix-in可能
//traitのみで完結できる場合はブロックの記述不要
class Trait_Ex2_Class extends Trait_Ex2

//複数のtraitの継承が可能
//二つ目以降の場合、withキーワードを使用する
//abstractクラスの継承はひとつだが、traitは複数可能
class Trait_Ex2_1_Class extends Trait_Ex2_1 with Trait_Ex2 {
  //未定義のメソッドの実装を制約可能
  override def jp: Unit = println("こんにちは")
}

//Trait_Ex2_1_Classと同じ構成だが異なるクラスとして扱われる。反変の関係
trait D extends Trait_Ex2_1 with Trait_Ex2

//トレイとの特徴
//・複数のトレイトを一つのクラスやトレイトにミックスインできる(検証対象：〇)(インスタンス化の検証)
//・直接インスタンス化できない
//・クラスパラメータ(コンストラクタの引数)を取ることができない