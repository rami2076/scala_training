package tutorial.roughly_scala.trait_

//ref:http://dwango.github.io/scala_text_previews/sbt-tut/trait.html

//trait
trait Trait_Ex2 {
  def en = println("Hello, World!")
}

//
trait Trait_Ex2_1 {
  private val STATE: String = "STATE"

  def state: Unit = println(STATE)

  def en: Unit

  def jp: Unit

  def fr: Unit = println("Bonjour le monde!")
}


//トレイとの特徴
//・複数のトレイトを一つのクラスやトレイトにミックスインできる(検証対象：〇)
//・直接インスタンス化できない
//・クラスパラメータ(コンストラクタの引数)を取ることができない


