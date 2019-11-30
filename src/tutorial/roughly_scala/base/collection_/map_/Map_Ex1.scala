package tutorial.roughly_scala.base.collection_.map_

import scala.collection.immutable.HashMap
import scala.collection.mutable


/**
  *
  * WeekHashMapは参照を削除していき目的が異なるのため除外
  * 10万エントリーまではjavaのHashMapが最速
  * 100万エントリーからはscalaのLongMapが最速
  * scalaのHashMap遅いため使用しないこと
  * 通常のエントリーは100万も格納されることないため速度を求められる場合はjavaのhashMapを使用することが望ましい
  * 100万を超えるエントリーを扱うようなMapを使用する場合は、LongMapを使用する方が効率的だが、LongMapをの使用を検討する前にアルゴリズムや方式の見直しをするべき。
  *
  * 下記2クラスは遅い。　
  * scala.collection.immutable.HashMap
  * scala.collection.Map
  *
  * 性能チェック第一弾
  * https://docs.google.com/spreadsheets/d/1odMxSxcV4Hb-uYEUbfrpzasdKM8bdGr5NNydt7nj4wc/edit?usp=sharing
  * 試行回数の平均を求めていない。
  * 冗長なコードが多くみられる。
  *
  *
  */

object Map_Ex1 extends App {
  val map = new Map_Ex1_1
  val map2 = new Map_Ex1_2
  val map3 = new Map_Ex1_3
  val map4 = new Map_Ex1_4
  val map5 = new Map_Ex1_5


  val maxes: Array[ Long ] = Array(10L, 100L, 1000L, 10000L, 100000L, 1000000L)
  loop

  def loop = {
    maxes.foreach(action)
  }

  def action(max: Long) = {
    val digits = max.toString.length
    val format = s"%0${digits}d"
    //println(s"--${digits}桁開始--")

    //--
    val s1 = System.nanoTime()
    for (i <- 0L to max) {
      map.add(i, i)
    }
    val m1_t = System.nanoTime() - s1
    val mapFormat1 = map.digitsFormat(format)
    map.clear

    //--
    val s2 = System.nanoTime()
    for (i <- 0L to max) {
      map2.add(i, i)
    }
    val m2_t2 = System.nanoTime() - s2
    val mapFormat2 = map2.digitsFormat(format)
    map2.clear
    //--
    val s3 = System.nanoTime()
    for (i <- 0L to max) {
      map3.add(i, i)
    }
    val m3_t3 = System.nanoTime() - s3
    val mapFormat3 = map3.digitsFormat(format)
    map3.clear
    //--
    val s4 = System.nanoTime()
    for (i <- 0L to max) {
      map4.add(i, i)
    }
    val m4_t4 = System.nanoTime() - s4
    val mapFormat4 = map4.digitsFormat(format)
    map4.clear
    //--
    val s5 = System.nanoTime()
    for (i <- 0L to max) {
      map5.add(i, i)
    }
    val m5_t5 = System.nanoTime() - s5
    val mapFormat5 = map5.digitsFormat(format)
    map5.clear


    //型変換をScalaのデフォルトに合わせたほうが効率が良い。
    println("Map:        " + m1_t.formatted("%010d") + ":" + mapFormat1)
    //型変換せずHashMap型を指定して使用した場合の方が
    println("s_HashMap:  " + m2_t2.formatted("%010d") + ":" + mapFormat2)
    println("WeakHashMap:" + m3_t3.formatted("%010d") + ":" + mapFormat3)
    println("LongMap:    " + m4_t4.formatted("%010d") + ":" + mapFormat4)
    println("j_HashMap:  " + m5_t5.formatted("%010d") + ":" + mapFormat5)

    // println(s"--${digits}桁終了--")
  }


}


class Map_Ex1_1() {
  //初期化
  private[ this ] var map_ = Map[ Long, Long ]()

  def add(k: Long, v: Long): Unit = {
    map_ += (k -> v)
    //型
    // println(k + ":" + map_.getClass)
    //文字列
    //println(map_)
    //取得apply
    //println(map(k)
    map_(k)
  }

  def digitsFormat(format: String) = map_.size.formatted(format)


  def clear = {
    map_ = Map.empty
  }
}

class Map_Ex1_2 {
  private[ this ] var hashMap: Map[ Long, Long ] = HashMap[ Long, Long ]()

  def add(k: Long, v: Long): Unit = {
    hashMap += (k -> v)
    //型
    //hashMap.getClass
    //文字列
    //println(hashMap)
    //取得apply
    //println(hashMap(k))
    hashMap(k)
  }

  def digitsFormat(format: String) = hashMap.size.formatted(format)

  def clear = {
    hashMap = Map.empty
  }
}

class Map_Ex1_3 {
  private[ this ] var hashMap: mutable.WeakHashMap[ Long, Long ] = mutable.WeakHashMap[ Long, Long ]()

  def add(k: Long, v: Long): Unit = {
    hashMap += (k -> v)
    //型
    //hashMap.getClass
    //文字列
    //println(hashMap)
    //取得apply
    //println(hashMap(k))

    hashMap(k)
  }

  def digitsFormat(format: String) = hashMap.size.formatted(format)

  def clear = {
    hashMap = mutable.WeakHashMap.empty
  }
}


class Map_Ex1_4 {
  private[ this ] var hashMap: mutable.LongMap[ Long ] = mutable.LongMap[ Long ]()

  def add(k: Long, v: Long): Unit = {
    hashMap += (k -> v)
    //型
    //hashMap.getClass
    //文字列
    //println(hashMap)
    //取得apply
    //println(hashMap(k))
    hashMap(k)
  }

  def digitsFormat(format: String) = hashMap.size.formatted(format)

  def clear = {
    hashMap = mutable.LongMap.empty
  }
}

class Map_Ex1_5 {
  private[ this ] val hashMap = new java.util.HashMap[ Long, Long ]()

  def add(k: Long, v: Long): Unit = {
    hashMap.put(k, v)
    //型
    //hashMap.getClass
    //文字列
    //println(hashMap)
    //取得apply
    //println(hashMap(k))
    hashMap.get(k)
  }

  def digitsFormat(format: String) = hashMap.size.formatted(format)


  def clear = {
    hashMap.clear()
  }
}


