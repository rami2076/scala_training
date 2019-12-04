package scala_d_text.other_

import scala_d_text.class_.{ClassEx2_A, ClassEx2_B, Outer}

object OtherPackage {
  val a = new ClassEx2_A(1, 1)
  val b = new ClassEx2_B(1, 1)
  //パッケージプライベートなクラスを作成できることを確認。
  //val outer = new Outer

}
