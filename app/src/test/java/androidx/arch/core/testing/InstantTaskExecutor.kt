package androidx.arch.core.testing

import androidx.arch.core.executor.TaskExecutor

class InstantTaskExecutor : TaskExecutor() {
  override fun executeOnDiskIO(runnable: Runnable) {
    runnable.run()
  }

  override fun isMainThread() = true

  override fun postToMainThread(runnable: Runnable) {
    runnable.run()
  }
}