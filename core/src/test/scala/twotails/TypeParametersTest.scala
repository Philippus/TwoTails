package twotails

import org.scalatest.{ FlatSpec, Matchers }

//first test arg types in the class constructor
class Fiji[A]{
  @mutualrec final def thing(x: A, y: Int): A = if(0 < y) thing(x, y-1) else x
}

class Bahama[A]{
  @mutualrec final def one(x: A, y: Int): A = if(0 < y) two(x, y-1) else x
  @mutualrec final def two(x: A, y: Int): A = if(0 < y) one(x, y-1) else x
}

class KeyLargo{
  @mutualrec final def thing[A](x: A, y: Int): A = if(0 < y) thing(x, y-1) else x
}

/*class Burmuda{
  @mutualrec final def one[A](x: A, y: Int): A = if(0 < y) two(x, y-1) else x
  @mutualrec final def two[A](x: A, y: Int): A = if(0 < y) one(x, y-1) else x
}*/

class TypeParamtersTest extends FlatSpec with Matchers{
  val fourK = 400000

  "A single argument, annotated method of a class with a type paramter" should "be equivalent to a tailrec" in{
    val fiji = new Fiji[Int]

    fiji.thing(1, fourK) should equal (1)
  }

  "A single argument, annotated method with a type parameter" should "be equivalent to a tailrec" in{
  	val largo = new KeyLargo

  	largo.thing(1, fourK) should equal (1)
  }

  "Two mutually recursive, single argument, annotated methods of a class with a type parameter" should "not throw a StackOverflow" in{
    val b = new Bahama[String]

    b.one("one", fourK) should equal ("one")
  }
}