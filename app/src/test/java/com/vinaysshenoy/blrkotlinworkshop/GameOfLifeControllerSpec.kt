package com.vinaysshenoy.blrkotlinworkshop

import com.vinaysshenoy.blrkotlinworkshop.gameoflife.GameOfLifeControllerImpl
import org.amshove.kluent.shouldBeFalse
import org.amshove.kluent.shouldBeTrue
import org.amshove.kluent.shouldEqualTo
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on

object GameOfLifeControllerSpec : Spek({

  group("basic rules") {
    describe("a 4 X 4 board") {
      describe("a live cell with < 2 live neighbours") {
        val testCases = listOf(
            setOf(1 to 1),
            setOf(1 to 1, 0 to 0),
            setOf(1 to 1, 0 to 1),
            setOf(1 to 1, 0 to 2),
            setOf(1 to 1, 0 to 3),
            setOf(1 to 1, 1 to 0),
            setOf(1 to 1, 1 to 2),
            setOf(1 to 1, 1 to 3),
            setOf(1 to 1, 2 to 0),
            setOf(1 to 1, 2 to 1),
            setOf(1 to 1, 2 to 2),
            setOf(1 to 1, 2 to 3),
            setOf(1 to 1, 3 to 0),
            setOf(1 to 1, 3 to 1),
            setOf(1 to 1, 3 to 2),
            setOf(1 to 1, 3 to 3)
        )

        testCases.forEachIndexed { index, seed ->
          val controller = GameOfLifeControllerImpl(
              size = 4,
              seed = seed
          )

          on("step for seed #$index") {

            // Needed to skip the initial state emission
            controller.step()

            controller.state.isAlive(1, 1)
                .shouldBeTrue()
            controller.step()

            it("should die") {
              controller.state.isAlive(1, 1)
                  .shouldBeFalse()
            }
          }
        }
      }

      describe("a live cell with 2 or 3 live neighbours") {
        val testCases = listOf(
            setOf(1 to 1, 0 to 0, 0 to 1),
            setOf(1 to 1, 0 to 1, 0 to 2),
            setOf(1 to 1, 1 to 2, 0 to 1, 1 to 2),
            setOf(1 to 1, 2 to 0, 2 to 1),
            setOf(1 to 1, 2 to 1, 2 to 2, 0 to 1),
            setOf(1 to 1, 2 to 2, 0 to 2, 2 to 1)
        )

        testCases.forEachIndexed { index, seed ->
          val controller = GameOfLifeControllerImpl(
              size = 4,
              seed = seed
          )

          on("step for seed #$index") {
            // Needed to skip the initial state emission
            controller.step()
            controller.state.isAlive(1, 1)
                .shouldBeTrue()
            controller.step()

            it("should live") {
              controller.state.isAlive(1, 1)
                  .shouldBeTrue()
            }
          }
        }
      }

      describe("a live cell with more than 3 live neighbours") {
        val testCases = listOf(
            setOf(1 to 1, 1 to 2, 0 to 1, 1 to 0, 0 to 2, 0 to 0) to true,
            setOf(1 to 1, 1 to 2, 0 to 1, 1 to 0, 0 to 2, 0 to 0, 2 to 2) to true,
            setOf(1 to 1, 1 to 2, 0 to 1, 2 to 2, 0 to 2, 2 to 1) to true,
            setOf(1 to 1, 1 to 2, 0 to 1, 1 to 0, 3 to 2) to false,
            setOf(1 to 1, 1 to 2, 0 to 1, 1 to 0, 3 to 1, 3 to 3) to false
        )

        testCases.forEachIndexed { index, (seed, shouldDie) ->
          val controller = GameOfLifeControllerImpl(
              size = 4,
              seed = seed
          )

          on("step for seed #$index") {
            // Needed to skip the initial state emission
            controller.step()
            controller.state.isAlive(1, 1)
                .shouldBeTrue()
            controller.step()

            it("should die") {
              controller.state.isAlive(1, 1)
                  .shouldEqualTo(shouldDie.not())
            }
          }
        }
      }

      describe("a dead cell with 3 live neighbours") {
        val testCases = listOf(
            setOf(1 to 2, 0 to 1, 1 to 0) to true,
            setOf(1 to 2, 0 to 1, 1 to 0, 0 to 2, 0 to 0) to false,
            setOf(1 to 2, 0 to 1, 1 to 0, 0 to 2, 0 to 0) to false,
            setOf(1 to 2, 0 to 1, 0 to 0) to true,
            setOf(1 to 2, 0 to 1, 1 to 0, 0 to 2, 2 to 2) to false,
            setOf(1 to 2, 0 to 1, 1 to 0, 0 to 2, 0 to 0, 2 to 2) to false,
            setOf(1 to 2, 2 to 2, 2 to 1) to true,
            setOf(1 to 2, 0 to 1, 1 to 0, 3 to 2) to true,
            setOf(1 to 2, 1 to 0, 3 to 1, 3 to 3) to false
        )

        testCases.forEachIndexed { index, (seed, shouldLive) ->
          val controller = GameOfLifeControllerImpl(
              size = 4,
              seed = seed
          )

          on("step for seed #$index") {
            // Needed to skip the initial state emission
            controller.step()
            controller.state.isAlive(1, 1)
                .shouldBeFalse()
            controller.step()

            it("should become alive") {
              controller.state.isAlive(1, 1)
                  .shouldEqualTo(shouldLive)
            }
          }
        }
      }
    }
  }

  group("step") {
    describe("a 5 X 5 board") {
      describe("an initial seed") {
        val seed = setOf(
            1 to 2,
            2 to 2,
            3 to 2
        )

        describe("a controller") {
          val controller = GameOfLifeControllerImpl(5, seed)
          controller.step()

          on("executing the step") {
            controller.state.isAlive(1, 2)
                .shouldBeTrue()
            controller.state.isAlive(2, 2)
                .shouldBeTrue()
            controller.state.isAlive(3, 2)
                .shouldBeTrue()

            controller.step()

            it("should update the next state") {
              controller.state.isAlive(1, 2)
                  .shouldBeFalse()
              controller.state.isAlive(3, 2)
                  .shouldBeFalse()
              controller.state.isAlive(2, 1)
                  .shouldBeTrue()
              controller.state.isAlive(2, 2)
                  .shouldBeTrue()
              controller.state.isAlive(2, 3)
                  .shouldBeTrue()
            }
          }
        }
      }
    }
  }
})