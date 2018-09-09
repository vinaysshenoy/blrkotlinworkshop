package com.vinaysshenoy.blrkotlinworkshop

import org.amshove.kluent.shouldEqualTo
import org.amshove.kluent.shouldNotThrow
import org.amshove.kluent.shouldThrow
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on

object SampleSpec : Spek({

  describe("test setup") {

    describe("a function that succeeds") {
      val func = { 2 + 2 }

      on("executing the function") {

        it("should succeed without failing") {
          func.shouldNotThrow(Throwable::class)
        }

        it("should return a result") {
          func() shouldEqualTo 4
        }
      }
    }

    describe("a function that fails") {
      val func = { throw RuntimeException() }

      on("executing the function") {

        it("should throw the exception") {
          func shouldThrow RuntimeException::class
        }
      }
    }
  }
})