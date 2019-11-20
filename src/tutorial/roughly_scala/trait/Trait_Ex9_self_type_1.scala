package tutorial.roughly_scala

/**
  *
  * self型は循環参照を許す。
  * self型でない場合は循環参照できない。
  *
  * 相互に規約を設ける場合に明示的に依存先の記述をかけることがメリットだが、構造がわかりにくくなるため要相談。
  *
  */

trait Trait_Ex9_self_type_1 {

}

// コンパイルできる
trait Ex9_Greeter {
  self: Ex9_Robot =>

  def greet(): Unit = println(s"My name is $name")
}

trait Ex9_Robot {
  self: Ex9_Greeter =>

  def name: String

  def start(): Unit = greet()
}


// コンパイルエラー
// illegal cyclic reference involving trait Greeter
//trait Ex9_Greeter_1 extends Ex9_Robot_1 {
//  def greet(): Unit = println(s"My name is $name")
//}
//
//trait Ex9_Robot_1 extends Ex9_Greeter_1 {
//  def name: String
//
//  def start(): Unit = greet()
//}


