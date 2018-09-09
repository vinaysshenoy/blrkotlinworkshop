package com.vinaysshenoy.blrkotlinworkshop

import androidx.lifecycle.LiveData

interface Controller<T : Any, U : Any, V : Any> {

  fun push(userEvent: T)

  fun pull(): LiveData<U>

  fun navigation(): LiveData<V>
}