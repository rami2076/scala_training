package scala_d_text.class_

import scala_d_text.class_.call_by_name.CallByName
import scala_d_text.class_.call_by_name_global.CallByNameGlobal
import scala_d_text.class_.call_by_value.CallByValue


/**
 *
 * メソッドのカリー化(Curryingまたは、カリー化された=curried)について
 *
 * https://qiita.com/yotsak83/items/c7db219fd90248288841
 * https://qiita.com/f81@github/items/e8bfab96b4be9e404840
 * http://dwango.github.io/scala_text_previews/sbt-tut/class.html
 * https://alvinalexander.com/source-code/scala/simple-scala-call-name-example
 */

object Defines extends App {
  val cls = new Defines()

  //名前付き引数渡し。
  cls.func5(name = "Erebus", age = "5")
  //一部省略可能。前方例。
  cls.func5("Erebus", age = "5")
  //一部省略可能。後方例。
  cls.func5(name = "Erebus", "5")

  //可変長引数の引数渡し。
  cls.func6("Gaia", "Nyx")
  //可変長引数の名前付き引数指定
  cls.func6(names = "Tom", "Ken", "Nick")

  //引数に関数あり。関数リテラルまたは無名関数として渡す場合 省略なし。 引数名 : 型 => 式 戻り値の型は推論される。　
  cls.func7(name = "Jack", (name: String) => {
    println(s"My name is name ${name}")
  })

  //引数に関数あり。関数リテラルまたは無名関数として渡す場合　　引数の型省略。
  cls.func7(name = "Anthony", name => {
    println(s"My name is name ${name}")
  })

  //引数に関数あり。関数リテラルまたは無名関数として渡す場合　　引数の型と式のブロック省略。
  cls.func7(name = "Mikel", name => println(s"My name is name ${name}"))

  //引数に関数あり。定義済みメソッドを渡す場合　　プレースホルダを引数に使用した場合
  cls.func7(name = "Nickle", println(_))

  //引数に関数あり。定義済みメソッドを渡す場合　　引数が一つで推測できる場合仮引数の記述を省略可能。
  cls.func7(name = "Cathy", println)

  //引数に関数あり。自作の定義済みメソッドを渡す場合　
  cls.func7(name = "Tomas", cls.func4(_))

  //引数に関数あり。関数として変数を渡す場合。右記のように定義されている。　val　変数名 : 型※1　= 式※2
  //※1　関数の型定義　　引数の型 => 戻り値の型
  //※2　関数の式定義　　引数名　=> 式
  //※3　記述方法は複数あるがここでは述べない。
  val printName: String => Unit = name => println(s"My Name is ${name}")
  cls.func7(name = "Atom", printName)
  //引数に関数あり。関数として変数を渡す場合。　関数を名前渡しで渡した場合。
  cls.func10("Elen", "11", nameAgePrint = cls.func5)

  //

  //部分適用
  //プレースホルダを使用。
  val a = cls.func5(name = "noct", _)
  a.apply("age")
  println(a.apply("age"))


  //-------------引数については終了-------------


  //値渡し
  println("Value------------")
  println(CallByValue.exec(CallByValue.time()))
  //名前渡し close Scope 名前私の場合は都度評価されるため使用する際は注意
  println("Name------------")
  println(CallByName.exec(CallByName.time()))
  //名前渡し wide Scope
  println("Name2------------")
  println(CallByNameGlobal.exec(CallByNameGlobal.time()))

  //デフォルト値を設定した関数の呼び出し。
  cls.func11() //デフォルト値を使用。
  cls.func11(name = "human") //前方のみ指定
  cls.func11(name = "human", age = "21") //どちらも変更
  cls.func11(age = "25") //後方の引数のみ指定
  //名前指定しない場合、引数の始めから順に割り当てられるためコンパイルエラーする場合と偶然、型が一致してコンパイルエラーしない場合があるため
  //名前付き引数を渡す方が安全
  cls.func11("25")


}

/**
 * var またはvalキーワードを使用して表現する値
 */

class Variables {

  val b = (aaa: Int, bbb: Int) => aaa + bbb
  val b2 = b(_, 1)
  println(b2(1))
  val a = (a: Int, b: Int) => Int

}

/**
 * def キーワードを使用して表現する値
 */

class Defines {
  //引数なし。
  def func(): Unit = {
    println("Hello")
  }

  //省略形式1　ブロックの省略
  def func2(): Unit = println("Hello")

  //省略形式2　ブロックと戻りの型の省略　※基本的に戻り値の型は明示する
  def func3 = println("Hello")

  //引数あり。オブジェクトを引数として一つ受け取る場合
  def func4(name: String): Unit = println(s"Hello ${name}")

  //複数引数あり。オブジェクトを引数として一つ受け取る場合
  def func5(name: String, age: String): Unit = println(s"My Name is ${name}. ${age} years old.")

  //可変長引数。オブジェクトを引数として一つ受け取る場合
  def func6(names: String*): Unit = names.foreach(name => println(s"My Name is ${name}."))


  //以下高階関数----始まり

  //高階関数：関数を受け取る。または関数を返すメソッドのこと。

  //引数あり。関数を引数として受け取る場合 (この部分。namePrint: String => Unit)Stringが引数でUnitが戻り値の関数を渡す。
  def func7(name: String, namePrint: String => Unit): Unit = namePrint.apply(name)

  //引数あり。関数を引数として受け取る場合 applyは特別な関数なので省略可能。
  def func8(name: String, namePrint: String => Unit): Unit = namePrint(name)

  //引数あり。関数を引数として受け取る場合 引数が複数ある場合プレースホルダの仮引数は機能しない。
  //def func9(name: String, namePrint: String => Unit): Unit = namePrint(_)

  //引数あり。関数を引数として受け取る場合 引数(String引数2,戻り値の型はUnit)が二つある式を受け取る場合。
  //※　(String, String) => Unit　括弧があるので構文エラーにならないことに気を付ける。
  def func10(name: String, age: String, nameAgePrint: ((String, String) => Unit)): Unit = nameAgePrint.apply(name, age)


  //以下高階関数----終わり。


  //デフォルト引数：仮引数のデフォルト値を設定できる。
  def func11(name: String = "anonymous", age: String = "??"): Unit = println(s"My name is ${name}. ${age} old years. ")


  //以下まだちゃんと記述できていない。
  //無名関数のためのプレースホルダ構文
  //http://kmizu.github.io/scala-kansai-summit-2017/#/
  //クロージャ
  //スコープ
  //リテラル
  //値クラス
  //ジェネリクス関係
  //implicit
  //カリー化　：複数の仮引数を持つ関数Aを単一の引数を持つ関数のチェインに変換すること。または変換された関数(カリー化された関数)
  //カリー化した関数は、集合演算時時に順番に処理が記述でき簡潔に記述できる。
  //https://kazu-yamamoto.hatenablog.jp/entry/20110906/1315279311
  ////複数引数：カリー化の方法
  //部分関数
  //関数の部分適用：複数の仮引数を持つ関数の呼び出しを行う際に一つ以上の引数の値を束縛できる。関数内の変数が束縛された関数が返却される。
  //※部分適用する仮引数は、関数や値などの第一級オブジェクトです。
  //※関数を返却する関数の表現の一つ。
  //https://kazu-yamamoto.hatenablog.jp/entry/20110906/1315279311
  //コンテキスト圧縮
  //抽出子
  //反変・非変・変位指定
  //文字列の補完
  //ネストしたメソッド　まずはこれっぽい。
  //topic-----開始
  //関数とメソッドの違い
  //https://qiita.com/FScoward/items/926aa29cc1f8e4873e6a
  //http://tkawachi.github.io/blog/2014/11/26/1/
  //https://stackoverflow.com/questions/2529184/difference-between-method-and-function-in-scala
  //topic-----終了


}

package call_by_value {

  /**
   * 値渡しの検証
   */
  object CallByValue {
    def time(): Long = {
      println("In time()")
      System.nanoTime //値が評価される。
    }

    /**
     * 引数tが値渡しとなっている。
     *
     * @param t 　評価済みの値が渡される。
     * @return
     */
    def exec(t: Long): Long = {
      println("Entered exec, calling t ...")
      println("t = " + t)
      println("Calling t again ...")
      t
    }
  }

}

package call_by_name {

  /**
   * 名前渡しの検証
   */
  object CallByName {
    def time() = {
      println("Entered time() ...")
      System.nanoTime
    }

    /**
     * 引数tが名前渡しとなっている。
     * 渡された値t内の式がここで評価される。
     *
     * @param t 　未評価の値が渡される。
     * @return
     */
    // `t` is now defined as a by-name parameter
    def exec(t: => Long) = {
      println("Entered exec, calling t ...")
      println("t = " + t) //tが評価される。
      println("Calling t again ...")
      t
    }


  }

}

package call_by_name_global {

  /**
   * 名前渡しの検証
   */
  object CallByNameGlobal {

    private var x: Long = 0

    def time() = {
      println("Entered time() ...")
      x * System.nanoTime
      //System.nanoTimeとして値を渡すことも可能。
    }

    /**
     * 引数tが名前渡しとなっている。
     * 渡された値t内の式がここで評価される。
     * 引数には、値を渡すことも可能。
     *
     * @param t 　未評価の値が渡される。
     * @return
     */
    // `t` is now defined as a by-name parameter
    def exec(t: => Long) = {
      x = 1 //x=1として式が評価される。　値渡しの場合、xが変動するだけで特に変数を変更する必要はない。
      println("Entered exec, calling t ...")
      println("t = " + t) //tが評価される。
      x = 0 //x=0として式が評価される。
      println("Calling t again ...")
      t
    }


  }

}
