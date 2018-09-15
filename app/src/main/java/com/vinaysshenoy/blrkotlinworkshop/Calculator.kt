package com.vinaysshenoy.blrkotlinworkshop

class Calculator {

  private val add = Add()

  fun add(
    a: Int,
    b: Int
  ): Int {
    return add.compute(a, b)
  }
}

private class Add {
  fun compute(
    a: Int,
    b: Int
  ) = a + b
}