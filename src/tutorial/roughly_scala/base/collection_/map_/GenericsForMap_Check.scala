package tutorial.roughly_scala.base.collection_.map_

import java.util.{HashMap => UHashMap, Map => UMap}

import scala.collection.mutable.{HashMap => MHashMap, LongMap => MLongMap, Map => MMap}
import scala.collection.{Map => SMap}

object GenericsForMap_Check extends App {

  val a = new CC[ Map[ Long, Long ], Long, Long ](classOf[ Map[ Long, Long ] ])
  val b = new CC[ MHashMap[ Long, Long ], Any, Any ](classOf[ MHashMap[ Long, Long ] ])
  val c = new CC[ MLongMap[ Long ], Long, Long ](classOf[ MLongMap[ Long ] ])
  val d = new CC[ UMap[ Long, Long ], Long, Long ](classOf[ UMap[ Long, Long ] ])


  a.print
  a.update(1L, 1L)
  a.update(2L, 2L)
  a.update(3L, 3L)
  a.print
  println(a.size)

  b.print
  b.update(1L, 1L)
  b.update(2L, 2L)
  b.update(3L, 3L)
  b.print
  println(b.size)


  c.print
  c.update(1L, 1L)
  c.update(2L, 2L)
  c.update(3L, 3L)
  c.print
  println(c.size)

  d.print
  d.update(1L, 1L)
  d.update(2L, 2L)
  d.update(3L, 3L)
  d.print
  println(d.size)


}


class CC[ T <: Any, K <: Any, V <: Any ](a: Class[ T ]) {
  private var map = {
    a match {
      case x if x == classOf[ Map[ K, V ] ] => Map[ K, V ]()
      case x if x == classOf[ MHashMap[ K, V ] ] => x.newInstance()
      case x if x == classOf[ MHashMap[ K, V ] ] => x.newInstance()
      case x if x == classOf[ MLongMap[ V ] ] => x.newInstance()
      case x if x == classOf[ UMap[ K, V ] ] => new UHashMap[ K, V ]();
    }
  }

  def print: Unit = {
    println(map.getClass.getName)
  }

  def update(k: K, v: V): Unit = {
    map match {
      case x: Map[ K, V ] => map = x.updated(k, v)
      case x: MMap[ K, V ] => x += (k -> v)
      case x: UMap[ K, V ] => x.put(k, v)
    }
  }


  def size: Int = {
    map match {
      case x: SMap[ K, V ] => x.size
      case x: UMap[ K, V ] => x.size
    }
  }


}
