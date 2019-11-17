package tutorial

//ObjectはJavaでいう静的クラスに当たる。

//シングルトンオブジェクトの生成ができる。

//エントリーポイントはObject内のmain(args:Array[String]):Unitと同一のシグネチャを持つ
object Object_Entry {
  //エントリーポイント
  def main(args: Array[String]): Unit = {
    println("Hello, World!")
  }
}