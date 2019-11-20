package tutorial.roughly_scala.base

object Function {

  //まとめ
  /*
  関数はdefを付ける
  returnありの場合、戻り値の型を記述する必要がある。
   */

  //関数=Function
  //def 関数名 (仮引数名：仮引数の型)：戻り値の型 = {}
  def appendTen(num: Int): Int = {
    return num + 10
  }

  //return は省略可能 省略した場合最後に評価した式の値が戻り値として設定される
  def appendTen_not_return(num: Int): Int = {
    num + 10
  }

  //戻り値の型を省略可能
  def appendTen_not_returnType(num: Int) = {
    num + 10
  }

  //戻り値の型を省略した場合returnを付けるとエラーになる。
  //def appendTen_not_returnType_error(num: Int) = {
  //  return num + 10
  //}

  //ブロックの省略が可能
  def appendTen_not_Block(num: Int): Int = return num + 10

  //ブロックの省略が可能 returnの省略可能
  def appendTen_not_Block_not_return(num: Int): Int = num + 10

  //ブロックの省略が可能 returnの省略可能
  def appendTen_not_Block_not_return_not_returnType(num: Int) = num + 10

  //ブロック省略 returnあり　かつ戻り値省略は不可
  //def appendTen_not_Block_not_return_not_returnType_error(num: Int) = return num + 10

}
