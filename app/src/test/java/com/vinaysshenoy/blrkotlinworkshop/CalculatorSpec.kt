package com.vinaysshenoy.blrkotlinworkshop

import org.amshove.kluent.shouldEqual
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on

data class Input(val a: Int, val b: Int)

object CalculatorSpec: Spek({

  describe("a calculator") {
    describe("that can add integers") {
      val calculator = Calculator()

      val testCases = setOf(
          Input(2, 3) to 5,
          Input(-1, 5) to 4,
          Input(-5, 3) to -2,
          Input(7, 0) to 7
      )

      testCases.forEach { (input, expected) ->
        on("input of $input") {
          val sum = calculator.add(input.a, input.b)

          it("should equal $expected") {
            sum shouldEqual expected
          }
        }
      }
      /*on("adding 2 & 3") {
        val sum = calculator.add(2, 3)

        it("should equal 5") {
          sum shouldEqual 5
        }
      }

      on("adding -1 and 5") {
        val sum = calculator.add(-1, 5)

        it("should equal 4") {
          sum shouldEqual 4
        }
      }*/
    }
  }
})

