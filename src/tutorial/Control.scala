package tutorial

object Control {

  //基本的な記述

  //if
  if (true) 1 else 2

  //while
  while (true) println(1)

  //for
  for (s <- Array("a", "b", "c")) println(s) //格納用変数の型の記述不要

  //match mutable
  var one = 1
  one match {
    case 1 => println("one")
    case 2 => println("two")
    case _ => println("other")
  }


}
