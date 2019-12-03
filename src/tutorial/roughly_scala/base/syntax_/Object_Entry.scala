package tutorial.roughly_scala.base.syntax_

/**
  * エントリーポイントはObject内のmain(args:Array[String]):Unitと同一のシグネチャを持つ
  *
  *
  * ObjectはJavaでいう静的クラスに当たる。
  * /シングルトンオブジェクトの生成ができる。
  */

object Object_Entry {
  //エントリーポイント
  def main(args: Array[String]): Unit = {
    println("Hello, World!")
  }
}
