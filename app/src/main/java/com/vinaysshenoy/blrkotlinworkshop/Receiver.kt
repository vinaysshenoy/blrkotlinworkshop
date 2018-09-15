package com.vinaysshenoy.blrkotlinworkshop

interface Receiver<T: Any> {

  fun receive(item: T)
}