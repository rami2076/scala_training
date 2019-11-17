package test

import tutorial.{DefaultUser, Profile}

object Test_AccessAndinheritance extends App {


  var user = new DefaultUser("Tom", 1)
  user.agree
  user.how_old

  println("------------")

  var profile = new Profile("Tom", 1)
  //メソッドのオーバライドを確認
  profile.agree
  //親クラスのメソッドの使用を確認
  profile.how_old
}
