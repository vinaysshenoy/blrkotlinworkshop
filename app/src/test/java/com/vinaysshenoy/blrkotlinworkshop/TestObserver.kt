package com.vinaysshenoy.blrkotlinworkshop

import androidx.lifecycle.Observer

class TestObserver<T> : Observer<T> {

  var changes = emptyList<T>()
    private set

  val latest: T
    get() = changes.last()

  val lastButOne: T
    get() = changes[changes.lastIndex - 1]

  val hasChanges: Boolean
    get() = changes.isNotEmpty()

  override fun onChanged(change: T) {
    changes += change
  }

  fun reset() {
    changes = emptyList()
  }
}