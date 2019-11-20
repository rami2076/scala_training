package test.roughly_scala.companion

import tutorial.roughly_scala.companion.Object_Companion_Ex1_Factory

/**
  * コンパニオン機能の確認
  * applyの確認
  */

object Test_Object_Companion_Ex1_Factory extends App {

  //http://www.ne.jp/asahi/hishidama/home/tech/scala/def.html
  //apply関数は省略可能。
  //省略なし
  val obj: Object_Companion_Ex1_Factory = Object_Companion_Ex1_Factory(1)
  //省略あり
  val obj2 = Object_Companion_Ex1_Factory.apply(1)

  //コンパニオンを持つクラスのインスタンスをobjectのメソッドより取得することができることを確認
}
