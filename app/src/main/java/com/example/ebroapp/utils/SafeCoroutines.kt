package com.example.ebroapp.utils

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

inline fun CoroutineScope.launchIO(
    crossinline safeAction: suspend () -> Unit,
    crossinline onError: suspend (Throwable) -> Unit
): Job {
    val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        launch(Dispatchers.Main) {
            onError.invoke(throwable)
        }
    }

    return this.launch(exceptionHandler + Dispatchers.IO) {
        safeAction.invoke()
    }
}

inline fun CoroutineScope.launchMain(
    crossinline safeAction: suspend () -> Unit,
    crossinline onError: suspend (Throwable) -> Unit
): Job {
    val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        launch(Dispatchers.Main) {
            onError.invoke(throwable)
        }
    }

    return this.launch(exceptionHandler + Dispatchers.Main) {
        safeAction.invoke()
    }
}

@Suppress("NeedToUseCustomWithContextRule")
suspend inline fun <T> withIO(noinline block: suspend CoroutineScope.() -> T): T {
    return withContext(Dispatchers.IO, block)
}

@Suppress("NeedToUseCustomWithContextRule")
suspend inline fun <T> withMain(noinline block: suspend CoroutineScope.() -> T): T {
    return withContext(Dispatchers.Main, block)
}
