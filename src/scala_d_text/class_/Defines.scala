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
  println(x = cls.func17().isInstanceOf[Function[String, Unit]]) //true
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
  println(cls.add_6(x = 1)(2)(3)) //ネストされたメソッドを使用する場合は、名前付き引数は一つ目以降使用できない。
  //オブジェクトを格納して使用。
  val funcY = cls.add_6(1)
  val funcZ = funcY(2)
  val total_6 = funcZ(v1 = 3)
  println(total_6)
  //カリー化されたメソッドの部分適用
  //val funcBad = cls.add_6(1)(_)(3) //Error:(119, 28) missing parameter type for expanded function ((<x$4: error>) => cls.add_6(1)(x$4)(3))
  val funcA = cls.add_6(x = 1)(_: Int)(3) //名前付き引数は一つ目以降使用できない。
  val total_A = funcA(v1 = 2)

  //引数リストを使用してカリー化を表現した際の呼び出し。
  println(cls.add_1(x = 1)(y = 2)(z = 3)) //名前付き引数を使用できる。
  val add1_B = cls.add_1(1)(_: Int)(3) //部分適用した場合。
  println(add1_B(2))
  //val add1_Bad = cls.add_1(1)(_)(3) //Error:(119, 28) missing parameter type for expanded function ((<x$4: error>) => cls.add_6(1)(x$4)(3))
  //型情報を明記しないと部分適用はできない。
  //http://komaken.me/blog/2015/06/05/scala%E3%81%AEeta-expansion%E3%81%A3%E3%81%A6%E3%81%AA%E3%82%93%E3%81%AA%E3%81%AE%E3%81%8B%E3%82%88%E3%81%86%E3%82%84%E3%81%8F%E3%82%8F%E3%81%8B%E3%81%A3%E3%81%9F/


  //部分適用について

  //関数の部分適用：複数の仮引数を持つ関数の呼び出しを行う際に一つ以上の引数の値を束縛できる。関数内の変数が束縛された関数が返却される。
  //※部分適用する仮引数は、関数や値などの第一級オブジェクトです。
  //※関数を返却する関数の表現の一つ。
  //https://kazu-yamamoto.hatenablog.jp/entry/20110906/1315279311
  //https://docs.scala-lang.org/tour/currying.html#inner-main
  //https://docs.scala-lang.org/glossary/#partially-applied-function
  //英訳：partially applied function

  //カリー化していない関数の部分適用。
  //※部分適用なし
  println(cls.add_0(1, 1, 1))
  //※部分適用1
  val partFunc_A = cls.add_0(1, _, 1) //型省略。
  println(partFunc_A(1))
  //※部分適用2
  val partFunc_B = cls.add_0(1, _: Int, 1) //型明記。
  println(partFunc_B(1))
  //部分適用3
  val partFunc_C = cls.add_0(2, _, _) //型を明記せず、部分適用の実行可能。
  val partFunc_D = partFunc_C(2, _)
  println(partFunc_C.getClass)
  println(partFunc_D(2))


  //カリー化された関数の部分適用。
  //部分適用1
  //val partFunc_Bad = cls.add_6(1)(_)(1) //カリー化されたメソッドの戻り値の関数の型情報は拡張された関数として扱われ、型情報は消去されているためエラーが発生する。
  //Error:(150, 35) missing parameter type for expanded function ((<x$8: error>) => cls.add_6(1)(x$8)(1))
  //expanded functionについては下記参照。TL;TR eta-expansion　イータ拡張されたメソッドをexpanded function呼ぶ。expanded functionはFunctionN-Traitを実装したメソッド(SAMクラス)のこと。
  //https://docs.scala-lang.org/sips/minutes/2019-03-13-sip-minutes.html#eta-expansion
  //http://komaken.me/blog/2015/06/05/scala%E3%81%AEeta-expansion%E3%81%A3%E3%81%A6%E3%81%AA%E3%82%93%E3%81%AA%E3%81%AE%E3%81%8B%E3%82%88%E3%81%86%E3%82%84%E3%81%8F%E3%82%8F%E3%81%8B%E3%81%A3%E3%81%9F/
  //部分適用2
  val partFunc_Good = cls.add_6(1)(_: Int)(1) //カリー化されたメソッドは型情報を明記することで部分適用が可能。
  println(partFunc_Good.getClass)
  println(partFunc_Good(v1 = 1))

  //カリー化された関数とカリー化されていない関数では、部分適用の実装が異なる。
  //カリー化された関数もカリー化されていない関数も部分適用は可能。
  //部分適用はは、値が束縛された関数や値を返却するため、初めに定義した式に型が記述している場合としていない場合で部分適用時の記述が異なる。
  println(partFunc_D.isInstanceOf[Int => Int])
  println(partFunc_Good.isInstanceOf[Int => Int])
  println(partFunc_A.isInstanceOf[Function1[Int, Int]]) //3引数の内、1引数が束縛された場合、カリー化された関数が戻ってくる。
  println(partFunc_Good.isInstanceOf[Function1[Int, Int]])
  //TODO::リフレクションの検証は後日。
  //http://xerial.org/scala-cookbook/recipes/2013/02/01/reflection

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
    //ネストしたメソッドをローカル関数と呼ぶ。
    //https://docs.scala-lang.org/glossary/#local-function
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
  def add_0(x: Int, y: Int, z: Int): Int = x + y + z

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
  //無名関数のためのプレースホルダ構文　その次
  //http://kmizu.github.io/scala-kansai-summit-2017/#/
  //クロージャ　その次の次
  //スコープ
  //リテラル
  //値クラス
  //ジェネリクス関係
  //implicit
  //case　class
  //


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


//部分関数とは
//部分関数は、数学用語に由来する言葉。
//関数を分類する際の言葉。
//まず、関数について記載する。
//関数は写像とも呼ばれている。
//数学の世界では写像と関数に調べた限り違いなく使われているようだが、
//関数それ自体を論じる圏論の世界では一般に写像という言葉が使用されているため写像という言葉を使用する。
//圏論(category theory)は数学的構造とその間の関係を抽象的に扱う数学理論の一つ。
//対象(object)と射(morphism,arrow)で構成される圏(category)が論の対象。
//集合Aと集合Bが与えられたとき集合Aの各元に対して、集合B元がただ一つ指定するような規則fが与えられる時、定義域Aから終域Bへの写像という。
//以下のように記述する
//ｆ：A->B
//矢印は射(arrow,morphism)
//AやBを対象(object)
//写像は英語でmapping,mapフランス語でapplication
//写像の同義語として関数、変換、作用素、射と呼ぶ。
//The words map or mapping, transformation, correspondence, and operator are often used synonymously.
//
//------#1解釈開始
//ここまでを簡単に解釈すると集合Aの各要素は集合Bの要素の一つに変換できる場合、射があるといえる。
//------#1解釈終わり
//
//------#2断り開始
//ここから話が飛びます。とくに写像であるための規則を飛ばす。
//写像には対象と射の関係性により全射や単射、全単射や逆写像という分類があるがこれについての説明も割愛。
//------#2断り終わり

//------#3部分写像について開始
//部分写像はpartial mapping　あるいは、部分関数partial functionと英語で表現する。
//scalaで出てくる部分関数はここが由来であると思われる。
//集合A,Bがあり、射fがfであるとした場合に以下であると定義できた。
//ｆ：A->B
//部分写像は、定義域A内の任意の部分集合A'の各元が終域Bの元に変換できる射を要求する。
// 任意の部分集合を特に値域と呼ぶ。
//定義域A内の任意の部分集合A`=Aである場合は、全域写像と呼ぶ。
//------#3部分写像について終了

//補足開始　元について
//元とは数学の集合で出てくる用語。
//集合内の要素を元と呼ぶ。
//英語ではelement
//補足終わり

//##解釈開始
//定義域の範囲が決まっていて終域の元に変換できる場合に部分関数と呼ぶと解釈できる。
//##解釈終わり

//集合について
//集合についても規則があるが、ここでは割愛。

//##上記の解釈を関数にscalaの関数に置き換える。
//まず以下の式について圏論の用語自分なりに置き換える。
//関数1:def f(x:Int):Int = {x+1}
//仮引数xはIntの集合である定義域
//式{x+1}または関数fは射
//戻り値はInt型の集合であり、終域はInt範囲内に像をmappingする関数と読み取れる。
//上記は仮引数に対して制限をかけていないため、全域写像であると解釈できる。
//関数2:def f2(x:Int):Int = {x match {
// case i if (i > -1 && i < 101 )  => 1 + i
// case _ => new Exception
// }
//}
//関数2:f2は射
//仮引数xはIntの集合である定義域
//射に与えられた対象の元は、定義域に対して0以上100以下の制限が要求される。
//定義域の内、制限範囲内の元の集合を値域と呼ぶ。
//値域には射が適用され、終域の各元が存在する。
//終域は、Intの集合範囲内である捉えられる。
//##scalaへの置き換えを終了。

//#まとめ
//つまり、抗議には部分写像はプロダクトの仕様と関数の作り方次第で普通に登場する関数のひとつの表現方法。
//#

//# 部分関数PartialFunctionというライブラリ。
//以下は、scalaに登場する部分関数について記述していく。
//上記で説明した部分写像の構造を表現するためにtraitとしてPartialFunctionが存在する。
//PartialFunctionはscalaで定義されているtraitであり、日本語訳で部分関数と呼ぶ。
//以下部分関数と記述する場合はscalaのライブラリ内のtraitのことである。
//関数2は部分写像の構造を持つ関数であった。
//しかし、呼び出しを行う際に例外が発生する場合がありすこしばかり使いにくい場合がある。
//PartialFunctionを実装すると、上記の値域範囲内かどうかを事前に検査する必要があることをtrait名から示すことができ、かつ
//例外の発生も抑止しより使いやすく読み取りやすい状態となる。
//また、Scalaのもととなった言語である、Haskellなどの関数型言語には部分関数をサポートした機能が存在するため、機能として標準で備えているのではないかと考えました。
//事前にチェックするためのメソッドを実装する必要があると考えると思いますが、コンパイラが解釈して実装するため、簡易な記述で安全に定義することができる点がPartialFunctionの良い点がと考えます。
//実際には下記のように記述します。


//注意：部分関数は部分適用とは全く別の概念。


package partial_function {

  object Partial extends App {
    val partial: PartialFunction[Int, Int] = {
      //無名クラスの作成のような記述方法だが、少し異なる。
      //コンパイラが独自解釈してメソッドを生成する。
      case i if (i > -1 && i < 101) => 1 + i //値域のみ定義
      //複数のcase式を記述可能。
    }
    val x = 1
    partial.isDefinedAt(x) match { //isDefinedAtは値域内であるかの判定を行うメソッドとなる。case文の条件文を使用してコンパイラが作成する。
      case true => println(partial(x)) //Function1のtraitの実装がcase式部となり、applyメソッドを呼び出している。
      case false => println(s"写像partialは元:${x}において定義されない")
    }
    //isDefinedAt
    //applyは自分で実装してもよいがコンパイラが実装してくれるので実行速度がシビアでない限り任せて問題ないと考える。
    //TODO::自分で実装した場合の記述は割愛。

    val y = 101
    partial.isDefinedAt(y) match {
      case true => println(partial(y))
      case false => println(s"写像partialは元:${y}において定義されない")
    }
    //部分関数はコレクションに対しても使用することができる。
    val numbers = Seq(-1, 0, 1, 2, 3, 99, 100, 101, 102)
    numbers.filter { //()は{}に置き換えが可能。
      //partial.isDefinedAt(_)
      partial.isDefinedAt //s補完子を使用しない場合は自明な引数は省略可能。(無名関数のためのプレースホルダーの省略)
    }.map { i => s"元:${i},像:${partial(i)}"
      //}.map { i => s"元:${i},像:${partial}"// s補完子を使用した場合、partial(i)のiは自明でないと判断されるため、applyの呼び出しではなく、toStringが呼び出される。
      //_error: unbound placeholder parameter　ラムダ式の記述を使用せず、無名関数のためのプレースホルダー記述を使用した場合、s補完子が機能しない。


    } foreach (println(_)) //}.foreachはスペースに変換可能。

    //PartialFunctionのその他の機能について
    //圏の合成 orElse andThen　compose
    //atomicな式の実行 applyOrElse　値域に定義されている場合は射を実行し、そうでない場合は、第二引数を返却する
    //不明： elementWise
    //Function1に適用された関数部を提供する　lift
    //不明：runWith
    //toString
    //matchで対象の元を返却。　unapply


    //初期化方法2 以下のように無名クラスのように実装することもできる。
    val pf = new PartialFunction[Int, Int] {
      override def isDefinedAt(x: Int): Boolean = x > 0 && x <= 100

      //Function1のtraitメンバ
      override def apply(v1: Int): Int = x + 1
    }

  }

  //部分関数に関するリンク集
  /*
  [ゆるヨロさんによる紹介記事](https://yuroyoro.hatenablog.com/entry/20100705/1278328898)
  [hishidamaさんPartialFunction](https://www.ne.jp/asahi/hishidama/home/tech/scala/function.html#h_PartialFunction)
  [写像wikipedia](https://ja.wikipedia.org/wiki/%E5%86%99%E5%83%8F)
  [部分写像wikipedia](https://ja.wikipedia.org/wiki/%E9%83%A8%E5%88%86%E5%86%99%E5%83%8F)
  [scalaDocの準ドキュメント](https://docs.scala-lang.org/overviews/quasiquotes/expression-details.html#partial-function)
  [Haskell wikipedia](https://ja.wikipedia.org/wiki/Haskell)
  [関数PDF](http://www.cs.tsukuba.ac.jp/~kam/lecture/discrete2010/text/3.pdf)
  [PartialFunctionAPI](https://www.scala-lang.org/api/current/scala/PartialFunction.html)
  [Scala標準ライブラリドキュメント](https://scala-lang.org/files/archive/spec/2.13/12-the-scala-standard-library.html)
  [ももやまさんの離散数学に関する解説](https://www.momoyama-usagi.com/entry/math/risan06#2-%E9%83%A8%E5%88%86%E9%96%A2%E6%95%B0%E9%83%A8%E5%88%86%E5%86%99%E5%83%8F)
  [PartialFunctionのインスタンス生成の謎について(コンパイラによる実装について)](https://gist.github.com/xuwei-k/1223693)
  [scalaドキュメント内の部分関数という言葉がある文書](https://docs.scala-lang.org/ja/tour/extractor-objects.html)
  [記述例](https://www.geeksforgeeks.org/partial-functions-in-scala/)
  [PartialFunction記述例2](https://alvinalexander.com/scala/how-to-define-use-partial-functions-in-scala-syntax-examples)
   */

}