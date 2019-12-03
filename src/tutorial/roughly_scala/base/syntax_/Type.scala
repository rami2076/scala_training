package tutorial.roughly_scala.base.syntax_

object Type {
  //参考
  //https://www.ne.jp/asahi/hishidama/home/tech/scala/literal.html

  //代表的な型


  // Boolean	真偽値	false
  // Char	2byte文字列	'\0'
  // String	文字列	null
  // Short	整数(16bit)	0
  // Int	整数(32bit)	0
  // Long	整数(64bit)	0
  // BigInt	任意精度整数	null
  // Float	浮動小数(32bit)	0.0
  // Double	浮動小数(64bit)	0.0
  // BigDecimal	任意精度固定小数	null
  // Symbol	シンボル(文字の前にシングルクォート	null


  var bool: Boolean = _
  var char: Char = _
  var string: String = _
  var short: String = _
  var int: Int = _
  var long: Long = _
  var bigInt: BigInt = _
  var float: Float = _
  var double: Double = _
  var bigDecimal: BigDecimal = _
  var symbol: Symbol = _

  val BOOL: Boolean = true
  val CHAR: Char = 'a'
  val STRING: String = "abc"
  val SHORT: Short = 1.toShort
  val INT: Int = 1
  val LONG: Long = 1l
  val BIGINT: BigInt = 1
  val FLOAT: Float = 0.1f
  val DOUBLE: Double = 0.1
  val BIG_DECIMAL: BigDecimal = 1
  val SYMBOL: Symbol = 'aaa


}
