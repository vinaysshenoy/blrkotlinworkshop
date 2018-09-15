package com.vinaysshenoy.blrkotlinworkshop

import com.vinaysshenoy.blrkotlinworkshop.Receiver

interface Controller<T : Any, U : Any, V : Any> {

  fun push(userEvent: T)

  fun registerStateReceiver(receiver: Receiver<U>, initial: Boolean)

  fun unregisterStateReceiver()

  fun registerSideEffectReceiver(receiver: Receiver<V>)

  fun unregisterSideEffectReceiver()

  fun destroy()
}
