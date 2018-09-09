package com.vinaysshenoy.blrkotlinworkshop

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import timber.log.Timber

class TheApplication : Application(), ServiceLocator {

  companion object {
    @JvmStatic
    lateinit var serviceLocator: ServiceLocator
  }

  override val viewModelFactory = object : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
      return null as T
    }
  }

  override fun onCreate() {
    super.onCreate()
    serviceLocator = this
    if (BuildConfig.DEBUG) {
      Timber.plant(Timber.DebugTree())
    }
  }
}