package tutorial.roughly_scala.companion

/*
コンパニオンの例1


 */


//privateなコンストラクタを作成する場合は仮引数指定の直前にprivateなアクセス修飾子を付与
class Object_Companion_Ex1_Factory private(a: Int) {
  protected var n = a

}


object Object_Companion_Ex1_Factory {
  //applyメソッドは特別なメソッドとなっている。
  //詳細は後日再調査。

  //https://qiita.com/someone7140/items/1f324bfe7e07d840776a
  //http://www.ne.jp/asahi/hishidama/home/tech/scala/def.html
  //https://tanoshiilife.wordpress.com/2015/11/08/scala-%E3%81%AE%E3%82%B3%E3%83%BC%E3%83%89%E3%82%92%E8%AA%AD%E3%82%82%E3%81%86%E3%81%A8%E6%80%9D%E3%81%A3%E3%81%A6%E3%82%B7%E3%83%B3%E3%82%BF%E3%83%83%E3%82%AF%E3%82%B9%E3%82%B7%E3%83%A5%E3%82%AC/
  //http://tkawachi.github.io/blog/2014/11/26/1/

  def apply(a: Int): Object_Companion_Ex1_Factory = new Object_Companion_Ex1_Factory(a)
}
