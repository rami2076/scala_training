package tutorial.roughly_scala.companion

//ref: https://docs.scala-lang.org/ja/tour/singleton-objects.html

//scalaの機能：コンパニオン
//訳：仲間、
//語源：俗ラテン語「パン(食事)を共にする人」の意 (COM‐+ラテン語 pānis 「パン」)
//ref: https://ejje.weblio.jp/content/companion
//静的メンバーとインスタンスメンバーを区別して記述することが可能。
//静的メンバーにファクトリーメソッドを付与することで複雑化するインスタンス生成の隠ぺいが可能になる

//ファクトリメソッドについて
//ref: https://qiita.com/shoheiyokoyama/items/d752834a6a2e208b90ca

//Javaだとフィールドが増えすぎて核となる記述が埋もれたり、ファクトリメソッドの影響でコアな記述が故不明になりやすくなる点が解消できる。

//コンパニオンクラス
//インスタンス化して使用するクラス
class Object_Companion {

}


//コンパニオンオブジェクト
//シングルトン？静的クラス？
object Object_Companion {

}


//※同ファイル内に記述する場合にのみ有効
