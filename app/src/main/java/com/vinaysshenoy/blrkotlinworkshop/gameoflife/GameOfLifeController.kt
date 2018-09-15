package com.vinaysshenoy.blrkotlinworkshop.gameoflife

import com.vinaysshenoy.blrkotlinworkshop.Controller
import com.vinaysshenoy.blrkotlinworkshop.Receiver
import kotlinx.coroutines.experimental.DefaultDispatcher
import kotlinx.coroutines.experimental.Job
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.launch
import kotlinx.coroutines.experimental.withContext
import java.util.concurrent.TimeUnit

abstract class GameOfLifeController<T> : Controller<GameOfLifeUserEvent, GameOfLifeState, GameOfLifeSideEffect> {

  private var state = GameOfLifeState(cells = emptyList())

  private var stateReceiver: Receiver<GameOfLifeState>? = null
  private var sideEffectReceiver: Receiver<GameOfLifeSideEffect>? = null

  private var gameOfLifeJob: Job? = null

  private var paused: Boolean = true

  override fun push(userEvent: GameOfLifeUserEvent) {

  }

  override fun registerStateReceiver(
    receiver: Receiver<GameOfLifeState>,
    initial: Boolean
  ) {
    stateReceiver = receiver
    paused = false
    if (initial) {
      gameOfLifeJob = launch(UI) {
        while (isActive) {
          if (!paused) {
            val gameState = withContext(DefaultDispatcher) {
              step()
            }

            state = state.copy(cells = adaptToViewState(gameState))
            stateReceiver?.receive(state)
          }
          delay(1L, TimeUnit.SECONDS)
        }
      }
    }
  }

  override fun unregisterStateReceiver() {
    stateReceiver = null
    paused = true
  }

  override fun registerSideEffectReceiver(receiver: Receiver<GameOfLifeSideEffect>) {
    sideEffectReceiver = receiver
  }

  override fun unregisterSideEffectReceiver() {
    sideEffectReceiver = null
  }

  override fun destroy() {
    gameOfLifeJob?.cancel()
  }

  abstract fun step(): T

  abstract fun adaptToViewState(gameState: T): List<Cell>
}
