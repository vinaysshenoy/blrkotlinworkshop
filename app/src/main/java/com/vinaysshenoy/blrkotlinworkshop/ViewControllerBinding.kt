package com.vinaysshenoy.blrkotlinworkshop

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.vinaysshenoy.blrkotlinworkshop.Controller

class ViewControllerBinding<T : Any, U : Any, V : Any>(
  private val controller: Controller<T, U, V>,
  private val view: MviView<U, V>
) : LifecycleObserver {

  private var initialLoadDone = false

  private val stateReceiver = object : Receiver<U> {
    override fun receive(item: U) {
      view.render(item)
    }
  }

  private val sideEffectReceiver = object : Receiver<V> {
    override fun receive(item: V) {
      view.sideEffect(item)
    }
  }

  @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
  fun bind() {
    controller.registerStateReceiver(stateReceiver, initialLoadDone.not())
    controller.registerSideEffectReceiver(sideEffectReceiver)
    initialLoadDone = true
  }

  @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
  fun unbind() {
    controller.unregisterStateReceiver()
    controller.unregisterSideEffectReceiver()
  }

  @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
  fun destroy() {
    controller.destroy()
  }

}
