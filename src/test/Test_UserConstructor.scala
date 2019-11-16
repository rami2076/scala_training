

package test

//Javaでいうところのデフォルトパッケージはimportできない：Javaと同じ仕様。
import temp.User_Constructor


object Test_UserConstructor {

  def main(args: Array[String]): Unit = {
    val user = new User_Constructor("aa")
    println(user.name)
  }

}
