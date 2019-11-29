package tutorial.roughly_scala.base.tuple_

/**
  *
  * tupleについて
  * tupleとは複数の構成要素からなる一組の情報を表現するための型
  *
  * tuple(名詞)：組、順序
  *
  * Scalaでは最大22となっている。
  *
  *
  *
  */

object Tuple_Ex1 extends App {

  //tupleの初期化
  val tuple = ("tom", 5)
  //tupleの文字列化
  println(tuple)
  println(tuple.getClass)

  println(tuple._1)
  println(tuple._1.getClass)

  println(tuple._2)
  println(tuple._2.getClass)

  val tuple_max = ("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22")
  println(tuple_max)
  println(tuple_max.getClass)

  //Error:(27, 19) too many elements for tuple: 23, allowed: 22
  //  val tuple_max = ("1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23")
  //val tuple_over = ("1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23")
  //println(tuple_max)
}
