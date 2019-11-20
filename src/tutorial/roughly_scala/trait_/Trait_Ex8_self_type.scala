package tutorial.roughly_scala

/**
  *
  *
  *
  *
  *
  *
  */
object Trait_Ex8_self_type extends App {
  //traitで記述するが、これは自分型(self型)という形式。
  //依存性の注入が容易にできることが利点
  val r: Traiot_Ex8_Robot = new Traiot_Ex8_Robot with Traiot_Ex8_RHelloGreeter
  r.start()
  //コンパイルエラー
  //r.greet()


  val r2: Traiot_Ex8_Robot2 = new Traiot_Ex8_Robot2 with Traiot_Ex8_RHelloGreeter
  //外部からアクセスできる。
  r2.greet()
  r2.start()

  //可視性のコントロールが可能になる。


}


//未実装のtrait
trait Trait_Ex8_Greeter {
  def greet(): Unit
}

trait Traiot_Ex8_Robot {
  //実装クラスでTrait_Ex8_Greeterを実装しなければならないことを明示的に記述できる。
  self: Trait_Ex8_Greeter =>

  //greet Methodを必ず使用する。
  def start(): Unit = greet()

}

trait Traiot_Ex8_Robot2 extends Trait_Ex8_Greeter {
  //greet Methodを必ず使用する。
  def start(): Unit = greet()

}


trait Traiot_Ex8_RHelloGreeter extends Trait_Ex8_Greeter {
  def greet(): Unit = println("Hello!")
}




