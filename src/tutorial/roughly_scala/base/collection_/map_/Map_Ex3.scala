
/**
  *
  *
  * 第三段
  * 第二弾の改良を行おうとするが、Classを引数に渡す方法がJavaと異なり、一旦確認をclassを渡す方法を確認する
  *
  *
  */


//
//package tutorial.roughly_scala.base.collection_.map_
//
//import java.util.Collections
//
//
//object Map_Ex3 extends App {
//  //val maxes: Array[ Long ] = Array(10L, 100L, 1000L, 10000L, 100000L, 1000000L)
//  //loop
//
//  //def loop = {
//  //maxes.foreach(action)
//  //}
//  //val format = s"%0${digits}d"
//
//  val format = s"%0${10}d"
//
//  val a: Option[ scala.collection.Map[ Long, Long ] ] = Option(Map[ Long, Long ]())
//  val b_1: Option[ scala.collection.Map[ Long, Long ] ] = Option(scala.collection.mutable.HashMap[ Long, Long ]())
//  val b_2: Option[ scala.collection.Map[ Long, Long ] ] = Option(scala.collection.mutable.LongMap[ Long ]())
//  val c: Option[ java.util.Map[ Long, Long ] ] = Option(new java.util.HashMap[ Long, Long ]())
//
//
//  val map: ThisIsMapaaaa[ scala.collection.Map[ Long, Long ] ] = new ThisIsMapaaaa((None, None, c))
//  map.update(1L, 1L)
//  map.update(2L, 2L)
//  println(map.value(1L))
//  println(map.digitsFormat(format))
//  println(c)
//
//
//}
//
//
//class ThisIsMapaaaa[ K <: Any, V <: Any ,T <: AnyRef](c: Class[ T ]) {
//
//
//  private def thisMap = {
//    println( c.getClass)
//  }
//
//
//  def digitsFormat(format: String) = {
//    this.thisMap match {
//      case msMap: scala.collection.immutable.Map[ K, V ] => msMap.size.formatted(format)
//      case isMap: scala.collection.mutable.Map[ K, V ] => isMap.size.formatted(format)
//      case uMap: java.util.Map[ K, V ] => uMap.size.formatted(format)
//
//    }
//  }
//
//  def update(k: K, v: V): Unit = {
//    this.thisMap match {
//      //case Map =>
//      case isMap: scala.collection.immutable.Map[ K, V ] => thisMap = isMap.updated(k, v)
//      case msMap: scala.collection.mutable.Map[ K, V ] => msMap += (k -> v)
//      case uMap: java.util.HashMap[ K, V ] => uMap.put(k, v)
//    }
//  }
//
//  def value(k: A): B = {
//    this.thisMap match {
//      case sMap: scala.collection.Map[ A, B ] => sMap(k)
//      case uMap: java.util.Map[ A, B ] => uMap.get(k)
//    }
//  }
//
//
//}
