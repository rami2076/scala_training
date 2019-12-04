package scala_d_text.class_

import scala_d_text.class_.p.ClassEx2_E

/**
 *
 * アクセス修飾子について
 * アクセス修飾子は2つ存在
 * private
 * protected
 *
 * privateは非公開メンバーの定義
 * protectedは限定公開メンバーの定義
 *
 *
 *
 *
 *
 *
 */

object ClassEx2 extends App {
  val a = new ClassEx2_A(1, 1)
  a.print()

  val b = new ClassEx2_B(1, 1)
  //b.print() エラー：
  val c = new Outer

  val e = new ClassEx2_E(1, 1)
  //パッケージプライベートなメソッド定義の確認
  //e.print()
  e.publicPrint()

}

/**
 * アクセス修飾子なしは公開メンバー
 *
 * @param x
 * @param y
 */

class ClassEx2_A(val x: Int, val y: Int) {

  //^^アクセス修飾子なしはpublic
  def print(): Unit = {
    println(toString)
  }

  override def toString(): String = "(" + x + ", " + y + ")"
}

/**
 * private修飾子はクラス内のみ使用可能、
 *
 * @param x
 * @param y
 */

class ClassEx2_B(val x: Int, val y: Int) {
  //クラス内でのみ可視性がある。
  private def print(): Unit = {
    println(toString)
  }

  def publicPrint(): Unit = {
    //自クラスの値を出力できる。
    this.print
  }

  def publicPrintOther(b: ClassEx2_B): Unit = {
    //自クラスの値を出力できる。
    b.print()
  }

  override def toString(): String = "(" + x + ", " + y + ")"
}

/**
 * private[自クラス]と指定すると自クラスのみ使用可能
 * privateと同じ意味となる。
 *
 * @param x
 * @param y
 */

class ClassEx2_C(val x: Int, val y: Int) {
  //privateと同じ意味。同じクラスの中で可視性がある。
  private[ClassEx2_C] def print(): Unit = {
    println(toString)
  }

  def publicPrint(): Unit = {
    //自クラスの値を出力できる。
    this.print
  }

  def publicPrintOther(c: ClassEx2_C): Unit = {
    //自クラスの値を出力できる。
    c.print()
  }

  override def toString(): String = "(" + x + ", " + y + ")"
}

/**
 *
 * private[this]とすると自インスタンスのみアクセス可能となる。
 *
 * @param x
 * @param y
 */

class ClassEx2_D(val x: Int, val y: Int) {
  //インスタンス内でのみ可視性がある。
  private[this] def print(): Unit = {
    println(toString)
  }

  def publicPrint(): Unit = {
    //自クラスの値を出力できる。
    this.print
  }

  def publicPrintOther(c: ClassEx2_D): Unit = {
    //可視性がインスタンス内に限定されるためメソッドを呼べない。
    //d.print()
  }

  override def toString(): String = "(" + x + ", " + y + ")"
}

/**
 *
 * OuterクラスとInnerクラスについて
 * privateはprivate[クラス名]と同じ意味となり、クラスに属するメンバーはアクセスすることができる。
 * インナークラスの場合は以下となる。
 * http://takafumi-s.hatenablog.com/entry/2015/07/31/152642
 *
 *
 * パッケージプライベートなクラスの確認
 * protectedなクラス定義も可能。　@see <a> https://github.com/apache/kafka/blob/0.10.2.2/core/src/main/scala/kafka/admin/ConsumerGroupCommand.scala#L138</a>
 */

private[class_] class Outer {
  private val foo = "FOO"

  class Inner {
    private def bar() = println("BAR")

    class InnerMost {
      bar()
    }

    new InnerMost
  }

  println(foo) // 1) FOO
  //  (new Inner).bar // 2) error: method bar in class Inner cannot be accessed in Outer.this.Inner
  new Inner // 3) Bar
}


package p {

  /**
   * パッケージプライベートなメソッド定義
   *
   * @param x
   * @param y
   */
  class ClassEx2_E(val x: Int, val y: Int) {
    //インスタンス内でのみ可視性がある。
    private[p] def print(): Unit = {
      println(toString)
    }

    def publicPrint(): Unit = {
      //自クラスの値を出力できる。
      this.print
    }

    def publicPrintOther(d: ClassEx2_E): Unit = {
      //可視性がインスタンス内に限定されるためメソッドを呼べない。
      d.print()
    }

    override def toString(): String = "(" + x + ", " + y + ")"
  }

}


package p2 {

  class Super {
    protected def foo() {
      println("FOO")
    }
  }

  class Sub extends Super {
    foo() // 1) FOO
  }

  class SubSub extends Sub {
    foo() // 1) FOO
  }


  class Other {
    //クラスのサブタイプからのみアクセスできる。
    //(new Super).foo() // 2) error: method foo in class Super cannot be accessed in p.Super
  }

}

package p3 {


  class Super_ {
    //パッケージ内からのアクセスも許可できる。
    //protected[x]のxにはクラス名も記述できる。
    protected[p3] def foo() {
      println("FOO")
    }
  }

  class Sub_ extends Super_ {
    foo() // 1) FOO
  }

  class SubSub_ extends Sub_ {
    foo() // 1) FOO
  }


  class Other_ {
    //クラスのサブタイプからのみアクセスできる。
    (new Super_).foo() // 2) error: method foo in class Super cannot be accessed in p.Super
  }

}