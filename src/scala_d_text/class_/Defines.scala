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


  //ネストしたメソッド
  cls.func12(name = "Hiro", age = "20")
  cls.func16("Here.")
  cls.func17()("Here.") //戻り値が関数なので()が続く呼び出しとなる。
  println(cls.func17().isInstanceOf[Function1[String, Unit]]) //true
  println(cls.func17().isInstanceOf[Function[String, Unit]]) //true
  println(cls.func17().isInstanceOf[String => Unit]) //true
  println(cls.func17().getClass)
  cls.func17().apply("Here") //Function1traitを実装しているのでapplyをもっている。
  cls.func17()("Here") //applyは省略可能。
  val message = cls.func17() //戻りを変数に格納可能。
  message(v1 = "here") //呼び出しは上記と同じ。

  //カリー化されたメソッドの使用。
  //applyを使用。
  println(cls.add_6(1).apply(2).apply(3))
  //applyを省略。
  println(cls.add_6(1)(2)(3))
  //オブジェクトを格納して使用。
  val funcY = cls.add_6(1)
  val funcZ = funcY(2)
  val total_6 = funcZ(v1 = 3)
  println(total_6)
  //カリー化されたメソッドの部分適用
  //val funcBad = cls.add_6(1)(_)(3) //Error:(119, 28) missing parameter type for expanded function ((<x$4: error>) => cls.add_6(1)(x$4)(3))
  val funcA = cls.add_6(1)(_: Int)(3)
  val total_A = funcA(2)

  //引数リストを使用してカリー化を表現した際の呼び出し。
  println(cls.add_1(1)(2)(3))
  val add1_B = cls.add_1(1)(_: Int)(3) //部分適用した場合。
  println(add1_B(2))
  //val add1_Bad = cls.add_1(1)(_)(3) //Error:(119, 28) missing parameter type for expanded function ((<x$4: error>) => cls.add_6(1)(x$4)(3))
  //型情報を明記しないと部分適用はできない。

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

  //ネストしたメソッド
  def func12(name: String, age: String): Unit = {
    //定義前の呼び出し可能。
    func13(s"My name is ${name}. ${age} old years.")

    def func13(message: String): Unit = {
      println(message)
    }

  }

  //ネストしたメソッド　評価後に値を返却する場合。
  //ネストしたメソッドの記述はカリー化するために必要だったと思われる。。
  //func14はカリー化とは無関係。
  def func14(name: String, age: String): Unit = {
    def func15(message: String): Unit = {
      println(message)
    }
    //ネストしたメソッドのスコープは外側のメソッドまでとなる。
    //ネストしたメソッドを呼び出し、評価後の値を返却する場合。
    func15(s"My name is ${name}. ${age} old years.")

  }

  //ネストしたメソッド　評価後に関数を返却する場合。
  def func16: String => Unit = { //引数がなく、副作用がない場合は()を省略してもよい。
    def func17(message: String): Unit = {
      println(message)
    }
    //関数を返却する場合。
    func17
  }

  //ネストしたメソッド　評価後に関数を返却する場合。
  def func17() = {
    def func17(message: String): Unit = {
      println(message)
    }
    //関数を返却する場合。 関数のオブジェクト化した値の返却。　関数名+_という構文。
    //Cの関数ポインタのイメージ
    func17 _
  }

  //ネストしたメソッド　関数リテラル(無名関数)を使用した場合
  def func18() = {
    (message: String) => println(message) //関数リテラルとした場合、戻り値の型は推測される。
  }

  //ネストしたメソッド　関数リテラルで記述し式ブロックを除去した場合
  def func19() = (message: String) => println(message) //ブロックを除去し一行にした場合。

  //メソッドのカリー化--------開始

  //カリー化　：複数の仮引数を持つ関数Aを単一の引数を持つ関数のチェインに変換すること。または変換された関数(カリー化された関数)　3
  //カリー化した関数は、集合演算時時に順番に処理が記述でき簡潔に記述できる。
  //https://kazu-yamamoto.hatenablog.jp/entry/20110906/1315279311
  ////複数引数：カリー化の方法

  //not curry
  def add_0(x: Int, y: Int, z: Int): Int = x + y

  //curry 1 引数リストを使用した場合。
  def add_1(x: Int)(y: Int)(z: Int): Int = x + y + z

  //curry 2　ネストメソッドを使用した場合：省略なし。
  def add_2(x: Int): (Int) => ((Int) => Int) = { //addの引数：Int。戻り：Intを引数に取りIntを返却する関数。
    def innerY(y: Int): (Int) => Int = { //Yの引数：Int。戻り：Intを引数にとり、Intを返却する関数。
      def innerZ(z: Int): Int = { //Zの引数：Int。戻り：Int
        x + y + z //Zが評価する式
      }

      innerZ //Yが返却する関数
      //関数型のオブジェクトに変換して返却。
      //戻り値の型が定義済みなので、_の記述不要。
    }

    innerY //addが返却する関数。
  }

  //curry 3　ネストメソッドを使用した場合：戻り値の型省略あり。
  def add_3(x: Int) = {
    def innerY(y: Int) = {
      def innerZ(z: Int) = {
        x + y + z
      }

      innerZ _
      //メソッド名の後ろにアンダースコアで関数型のオブジェクトに変換される。　
      //代入先の型が明記されている場合は、アンダースコアの記述がなくても自動変換可能。
      //戻りの型を省略する場合は、アンダースコアが必要。
    }

    innerY _
    //ref: https://www.ne.jp/asahi/hishidama/home/tech/scala/function.html#h_function_object
    //ref: http://daybreaksnow.hatenablog.jp/entry/2013/10/02/185902
  }

  //curry 4　ネストメソッドを使用した場合：戻り値の型省略とZ関数を関数リテラル(無名関数)に書き換えた版
  def add_4(x: Int) = {
    def innerY(y: Int) = {
      (z: Int) => x + y + z //Zを無名関数に変換。
    }

    innerY _
  }

  //curry 5　ネストメソッドを使用した場合：戻り値の型省略とY関数とZ関数を関数リテラル(無名関数)に書き換えた版
  def add_5(x: Int) = {
    (y: Int) => (z: Int) => x + y + z //Yも無名関数に変換。
  }

  //curry　6　ネストメソッドを使用した場合：戻り値の型省略とY関数とZ関数を関数リテラル(無名関数)に書き換え、式ブロックを除去した版
  def add_6(x: Int) = (y: Int) => (z: Int) => x + y + z //式ブロックを削除し、一行で表現。

  //メソッドのカリー化--------終了


  //以下まだちゃんと記述できていない。
  //無名関数のためのプレースホルダ構文
  //http://kmizu.github.io/scala-kansai-summit-2017/#/
  //クロージャ
  //スコープ
  //リテラル
  //値クラス
  //ジェネリクス関係
  //implicit
  //case　class
  //


  //部分関数　2


  //関数の部分適用：複数の仮引数を持つ関数の呼び出しを行う際に一つ以上の引数の値を束縛できる。関数内の変数が束縛された関数が返却される。
  //※部分適用する仮引数は、関数や値などの第一級オブジェクトです。
  //※関数を返却する関数の表現の一つ。
  //https://kazu-yamamoto.hatenablog.jp/entry/20110906/1315279311
  //コンテキスト圧縮
  //抽出子
  //反変・非変・変位指定
  //文字列の補完

  //topic-----開始
  //関数とメソッドの違い
  //https://qiita.com/FScoward/items/926aa29cc1f8e4873e6a
  //http://tkawachi.github.io/blog/2014/11/26/1/
  //https://stackoverflow.com/questions/2529184/difference-between-method-and-function-in-scala
  //topic-----終了

  //見る
  //https://www.slideshare.net/AoiroAoino/scala-79575940
  //キーワード関数 変数 違い scala

  //マクロ
  //システムコマンドの実行


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
