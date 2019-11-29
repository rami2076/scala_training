
package tutorial.roughly_scala.base.collection_.map_

/**
  *
  *
  * 第一弾の記述に冗長が存在したため整理
  * 計測まで行うこと。。
  *
  *
  * 第二弾
  * Optionで引数を渡すコードに冗長なコードあり。
  * 記述の改善の必要性を感じる。
  *
  *
  *
  *
  */

object Map_Ex2 extends App {
  //val maxes: Array[ Long ] = Array(10L, 100L, 1000L, 10000L, 100000L, 1000000L)
  //loop

  //def loop = {
  //maxes.foreach(action)
  //}
  //val format = s"%0${digits}d"

  val format = s"%0${10}d"

  val a: Option[ scala.collection.Map[ Long, Long ] ] = Option(Map[ Long, Long ]())
  val b_1: Option[ scala.collection.Map[ Long, Long ] ] = Option(scala.collection.mutable.HashMap[ Long, Long ]())
  val b_2: Option[ scala.collection.Map[ Long, Long ] ] = Option(scala.collection.mutable.LongMap[ Long ]())
  val c: Option[ java.util.Map[ Long, Long ] ] = Option(new java.util.HashMap[ Long, Long ]())


  val map: ThisIsMapaaa[ Long, Long ] = new ThisIsMapaaa((None, None, c))
  map.update(1L, 1L)
  map.update(2L, 2L)
  println(map.value(1L))
  println(map.digitsFormat(format))
  println(c)


}

//FIXME::引数を二つまで絞り込めるのでは？その場合Either使えるのでは？
class ThisIsMapaaa[ A <: Any, B <: Any ](any: (Option[ Map[ A, B ] ], Option[ scala.collection.Map[ A, B ] ], Option[ AnyRef ])) {


  private var thisMap = {
    any match {
      case (_, None, None) => any._1.get
      case (None, _, None) => any._2.get
      case (None, None, _) => any._3.get
      case _ =>
    }
  }

  def digitsFormat(format: String) = {
    this.thisMap match {
      case msMap: scala.collection.immutable.Map[ A, B ] => msMap.size.formatted(format)
      case isMap: scala.collection.mutable.Map[ A, B ] => isMap.size.formatted(format)
      case uMap: java.util.Map[ A, B ] => uMap.size.formatted(format)

    }
  }

  def update(k: A, v: B): Unit = {
    this.thisMap match {
      //case Map =>
      case isMap: scala.collection.immutable.Map[ A, B ] => thisMap = isMap.updated(k, v)
      case msMap: scala.collection.mutable.Map[ A, B ] => msMap += (k -> v)
      case uMap: java.util.HashMap[ A, B ] => uMap.put(k, v)
    }
  }

  def value(k: A): B = {
    this.thisMap match {
      case sMap: scala.collection.Map[ A, B ] => sMap(k)
      case uMap: java.util.Map[ A, B ] => uMap.get(k)
    }
  }


}
