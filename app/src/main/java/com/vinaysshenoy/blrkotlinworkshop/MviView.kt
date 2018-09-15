package com.vinaysshenoy.blrkotlinworkshop

interface MviView<T: Any, P: Any> {

  fun render(state: T)

  fun sideEffect(sideEffect: P)
}