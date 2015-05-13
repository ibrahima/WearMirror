package io.ibrahim.wearmirror

import breeze.linalg._
import breeze.numerics._
import breeze.stats.distributions._

import android.util.Log;

object TestCS  {
  /** Generates a random dictionary matrix for use in compressed sensing
    * applications */
  def gen_a(n: Int, m: Int) = {
    var A = DenseMatrix.rand(n, m)
    for(j <- 0 until m){
      val n = norm(A(::, j))
      A(::, j) := A(::, j)/n
    }
    A
  }

  def gen_x(m: Int, s: Int) = {
    var x = DenseVector.zeros[Double](m)
    val indexes = Rand.randInt(m).samplesVector(s)
    // val N = Rand.uniform.samplesVector(s)
    for (i <- indexes){
      x(i) = Rand.uniform.sample();
    }
    // x(indexes) := N
    x
  }

  def mp(A: DenseMatrix[Double], b: DenseVector[Double]) = {
    val e_0 = 0.01
    val (n, m) = (A.rows, A.cols);
    var x_hat = DenseVector.zeros[Double](m)
    var e = 100000.0
    var iterations = 1
    var resid = b
    while(abs(e) > e_0){
      val aTb = A.t*resid

      val k_p = argmax(abs(aTb))
      val a_kp = A(::, k_p);

      x_hat(k_p) = x_hat(k_p) + aTb(k_p);
      resid = resid - aTb(k_p) * a_kp
      e = resid.t * resid
      iterations = iterations + 1
    }
    x_hat
  }

  def testCS(s: Int, N_tests: Int) = {
    val n = 30
    val m = 50
    var errors = DenseVector.zeros[double](N_tests)
    for(i <- 0 until N_tests){
      val A = gen_a(n, m)
      val x = gen_x(m, s)
      val b = A*x
      val x_hat = mp(A, b)

      errors(i) = (x-x_hat).t*(x-x_hat)
    }
    errors
  }

  def mat2String(mat: Matrix[Double]) = {
    IndexedSeq.tabulate(mat.rows,mat.cols)(mat(_,_).toString).toString
  }
}
