package com.vinaysshenoy.blrkotlinworkshop

import androidx.lifecycle.ViewModelProvider

interface ServiceLocator {

  val viewModelFactory: ViewModelProvider.Factory
}