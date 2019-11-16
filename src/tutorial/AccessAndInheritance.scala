package tutorial

/**
  *
  */

object AccessAndInheritance {
  def main(args: Array[String]): Unit = {

  }
}


class DefaultUser(_name: String, _age: Int) {
  val name = _name
  val age = _age

  def agree = println("Hi, I`m " + name)

  /**
    *
    */
  def how_old = println("I`m " + fudge_the_count)

  /**
    * @return
    */
  protected def real_age = age //protected メソッド

  private def fudge_the_count = age - 5 //private メソッド
}

class Profile(_name: String, _age: Int) extends DefaultUser(_name: String, _age: Int) {

  //親クラスのメソッドをオーバライド
  override def agree = println("Hi, I`m " + name + ". I`m going to be " + real_age + "this year")

}

