package scala_d_text.class_

/**
 *
 * クラス定義について
 *
 * クラス定義直後にコンストラクタ引数を記述する。
 * クラス定義直後のコンストラクタはプライマリコンストラクタと呼ぶ。
 *
 *
 *
 */

object ClassEx1 extends App {
  val a = new ClassEx1_A(1, 1)
  val b = new ClassEx1_B(1, 1)
  val c1 = new ClassEx1_C(1, 1)
  val c2 = c1 + c1
  val c3 = c1.+(c2)
  println(c1.toString())
  println(c2.toString())
  println(c3.toString())


}

/**
 * プライマリコンストラクタ定義
 *
 * @param _x
 * @param _y
 */

class ClassEx1_A(_x: Int, _y: Int) {
  val x = _x
  val y = _y
}

/**
 *
 * valまたはvarをつけるとコンストラクタの値をそのまま公開できる。
 *
 * @param _x
 * @param _y
 */

class ClassEx1_B(val _x: Int, var _y: Int) {

}

/**
 *
 * コンストラクタ引数の値は、メソッド内で使用できる。
 * スコープはクラス全体に及ぶ。
 *
 * @param x
 * @param y
 */

class ClassEx1_C(val x: Int, val y: Int) {
  def +(p: ClassEx1_C): ClassEx1_C = {
    new ClassEx1_C(x + p.x, y + p.y)
  }

  override def toString(): String = "(" + x + ", " + y + ")"
  //　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　^^^^^　　　　　^^^^^
}



