package com.vinaysshenoy.blrkotlinworkshop

import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on

object GameOfLifeSpec: Spek({

  describe("a game of life") {
    describe("basic rules") {
      describe("a board of size 4 X 4") {

        describe("a live cell with less than two live neighbours") {
          on("tick") {
            it("should die") {
            }
          }
        }

        describe("a live cell with two or three live neighbours") {

        }

        describe("a live cell with more than three live neighbours") {

        }

        describe("a dead cell with 3 neighbours") {

        }
      }
    }
  }
})