package io.ibrahim.wearmirror

import breeze.linalg._
import breeze.numerics._

import android.util.Log;

object TestCS  {
  /** Generates a random dictionary matrix for use in compressed sensing
    * applications */
  def gen_a(m: Int, n: Int) = {
	var A = DenseMatrix.rand(m, n)
    for(j <- 0 until n){
      val n = norm(A(::, j))
      A(::, j) := A(::, j)/n
    }
    A
  }

  def gen_x(m: Int, s: Int) = {
    var x = DenseVector(m)
    x
  }

  def mat2String(mat: Matrix[Double]) = {
    IndexedSeq.tabulate(mat.rows,mat.cols)(mat(_,_).toString).toString
  }
}
