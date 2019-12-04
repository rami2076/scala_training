package tutorial.roughly_scala.base.exception_

/**
 * Optionを使用した例外処理
 *
 * マッチ式でoptionの中身を判定して例外の処理を実行できる。
 * OptionはSomeまたは、Noneを持ち、Optionの初期化時にnullである場合は、型Noneそれ以外の場合は型Someに内包された値が返却される。
 *
 */

object Exception_Ex3 extends App {

  //ScalaのMapクラスのgetの戻り値はOptionで帰ってくる。
  //値が存在しない場合の例外処理は以下のようになる。
  val map = Map(1 -> 1, 2 -> 2)


  val item = map.get(9)
  val r = item match {
    case Some(x) => "OK"
    case None => "NG"
  }
  println(r)

  //例外時の処理が値の返却のみの場合は以下で十分。
  println(map.getOrElse(9, 0))


  //Optionの挙動について

  //型NoneがOptionに存在するためOK
  val c = Option(None)
  println(judge(c))

  //型はNoneなのでNG
  val a = None
  println(judge(a))

  //型IntがOptionに存在するためOK
  val b = Option(1)
  println(judge(b))

  //空のOptionの中身はNone
  val d = Option.empty
  println(judge(d))

  //nullの場合はOptionはNone
  val e = Option(null)
  println(judge(e))


  def judge(v: Option[Any]) = {
    v match {
      case Some(x) => "OK"
      case None => "NG"
    }
  }


}
