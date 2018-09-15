package com.vinaysshenoy.blrkotlinworkshop.gameoflife

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.vinaysshenoy.blrkotlinworkshop.Controller
import com.vinaysshenoy.blrkotlinworkshop.MviView
import com.vinaysshenoy.blrkotlinworkshop.R.layout
import com.vinaysshenoy.blrkotlinworkshop.ViewControllerBinding
import kotlinx.android.synthetic.main.screen_gameoflife.widget

class GameOfLifeScreen : Fragment(),
    MviView<GameOfLifeState, GameOfLifeSideEffect> {

  lateinit var controller: Controller<GameOfLifeUserEvent, GameOfLifeState, GameOfLifeSideEffect>

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    // TODO: Create controller
  }

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    viewLifecycleOwner.lifecycle.addObserver(ViewControllerBinding(controller, this))
    return inflater.inflate(layout.screen_gameoflife, container, false)
  }

  override fun render(state: GameOfLifeState) {
    widget.cells = state.cells
  }

  override fun sideEffect(sideEffect: GameOfLifeSideEffect) {

  }
}
