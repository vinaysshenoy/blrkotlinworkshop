package com.vinaysshenoy.blrkotlinworkshop.gameoflife


class GameOfLifeControllerImpl(
  val size: Int,
  seed: Set<Pair<Int, Int>> = emptySet()
) : GameOfLifeController<GameOfLifeControllerImpl.State>() {

  private var initialStep = true

  var state = State(
      size = size,
      cells = generateSequence(Cell(x = 0, y = 0, alive = seed.contains(0 to 0))) { prevCell ->
        var nextCell = when {
          prevCell.x == size - 1 && prevCell.y == size - 1 -> null
          prevCell.x == size - 1 -> Cell(x = 0, y = prevCell.y + 1, alive = false)
          else -> Cell(x = prevCell.x + 1, y = prevCell.y, alive = false)
        }

        if (nextCell != null && seed.contains(nextCell.x to nextCell.y)) {
          nextCell = nextCell.copy(alive = true)
        }

        nextCell
      }.toList()
  )

  override fun step(): State {
    if (initialStep) {
      initialStep = false
      return state
    }

    state = state.nextGeneration()
    return state
  }

  override fun adaptToViewState(gameState: State): List<Cell> {
    return gameState.cells
  }

  data class State(
    val size: Int,
    val cells: List<Cell>
  ) {

    private fun neighbours(cell: Cell): List<Cell> {
      val requiredX = when {
        cell.x == 0 -> (0..1)
        cell.x == size -> (size - 1..size)
        else -> ((cell.x - 1)..(cell.x + 1))
      }

      val requiredY = when {
        cell.y == 0 -> (0..1)
        cell.y == size -> (size - 1..size)
        else -> ((cell.y - 1)..(cell.y + 1))
      }
      return cells.filter { currentCell ->
        currentCell.x in requiredX && currentCell.y in requiredY && currentCell != cell
      }
    }

    fun isAlive(
      x: Int,
      y: Int
    ): Boolean {
      return cells.find { it.x == x && it.y == y }?.alive ?: throw AssertionError("Position [$x, $y] not present!")
    }

    fun nextGeneration(): GameOfLifeControllerImpl.State {
      val futureCells = cells.associateBy { it.x to it.y }
          .toMutableMap()

      cells.forEach { currentCell ->
        val neighbours = neighbours(currentCell)
        val numLiveNeighbours = neighbours.count { it.alive }

        val positionOfCurrentCell = currentCell.x to currentCell.y

        if (currentCell.alive) {
          when {
            numLiveNeighbours < 2 -> futureCells[positionOfCurrentCell] = currentCell.copy(alive = false)
            numLiveNeighbours > 3 -> futureCells[positionOfCurrentCell] = currentCell.copy(alive = false)
          }
        } else {
          if (numLiveNeighbours == 3) {
            futureCells[positionOfCurrentCell] = currentCell.copy(alive = true)
          }
        }
      }
      return copy(cells = futureCells.values.toList())
    }
  }
}
