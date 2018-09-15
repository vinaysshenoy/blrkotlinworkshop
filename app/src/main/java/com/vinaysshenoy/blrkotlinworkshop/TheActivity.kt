package com.vinaysshenoy.blrkotlinworkshop

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.vinaysshenoy.blrkotlinworkshop.gameoflife.GameOfLifeScreen
import kotlinx.android.synthetic.main.the_activity.*

class TheActivity: AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.the_activity)
    setSupportActionBar(toolbar_app)

    if(savedInstanceState == null) {
      supportFragmentManager.beginTransaction()
          .replace(R.id.frame_screens, GameOfLifeScreen(), "screen_game_of_life")
          .commit()
    }
  }
}