package tutorial.roughly_scala.base

object Variable {

  //変数
  //var 変数名:型 = 値
  //型については型推論であるため省略可能
  var num: Int = 1
  var str: String = "abc"
  var short: Short = _ // _で初期値が設定される
  //_で初期値が設定

  //定数
  //val 定数名:型 = 値
  val NUM: Int = 1
  val STR: String = "abc"
  //val SHORT: Short = _ 定数で初期値は利用できない

}
