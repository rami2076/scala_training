package test.roughly_scala.base

import tutorial.roughly_scala.base.syntax_.User_Field

object Test_UserField {
  def main(args: Array[String]): Unit = {


    val array: List[Int] = 1 :: 1 :: 2 :: 1 :: Nil
    array.foreach(value => print(value))
    array.foreach(print(_))
    array.foreach(println)


    //インスタンスの生成
    val user_Field = new User_Field("Tom", 1)
    //内容の表示
    println(user_Field.name)
    println(user_Field.age)


    //値の再代入
    user_Field.name = "Shiori"

    println("----再代入後-------")
    //値の再表示
    println(user_Field.name)
    println(user_Field.age)

    //エラー；イミュータブルな値の再代入はできない
    //user_Field.age = 1

  }


}
