package tutorial.roughly_scala.base.collection_.seq_

/**
  *
  *
  *
  *
  */

object Seq_Ex1 extends App{
  //初期化
  var seq : Seq[Int]= Seq(1,2,3)

  //取得
  println(seq(1))
  //逐次処理
  seq.foreach( println )

  //クラス名
  println(seq.getClass)
}
