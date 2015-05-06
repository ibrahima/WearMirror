package io.ibrahim.wearmirror

import breeze.linalg._
import android.util.Log;
object MyClassTest  {
  def foo = {
    val x = DenseVector.zeros[Double](5)
    println("Hello world")
    println(x)
    Log.d("WearMirror","Hi from Scala")
    Log.d("WearMirror", x.toString)
  }
}
