package test.roughly_scala.base

import temp.User_Constructor

object Test_UserConstructor {
  //Javaでいうところのデフォルトパッケージはimportできない：Javaと同じ仕様。
  def main(args: Array[String]): Unit = {
    val user = new User_Constructor("aa")
    println(user.name)
  }

}
