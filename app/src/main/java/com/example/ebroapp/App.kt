package com.example.ebroapp

import android.app.Application
import com.example.ebroapp.di.Injector
import ru.ok.tracer.CoreTracerConfiguration
import ru.ok.tracer.HasTracerConfiguration
import ru.ok.tracer.TracerConfiguration
import ru.ok.tracer.crash.report.CrashFreeConfiguration
import ru.ok.tracer.crash.report.CrashReportConfiguration
import ru.ok.tracer.disk.usage.DiskUsageConfiguration
import ru.ok.tracer.heap.dumps.HeapDumpConfiguration
import timber.log.Timber

class App : Application(), HasTracerConfiguration {

    override val tracerConfiguration: List<TracerConfiguration>
        get() = listOf(
            CoreTracerConfiguration.build { setDebugUpload(BuildConfig.DEBUG) },
            CrashReportConfiguration.build {
                setEnabled(true)
                setNativeEnabled(true)
                setSendAnr(true)
            },
            CrashFreeConfiguration.build { setEnabled(true) },
            HeapDumpConfiguration.build { setEnabled(true) },
            DiskUsageConfiguration.build {
                setEnabled(true)
                setInterestingSize(INTERESTING_SIZE)
                setProbability(PROBABILITY)
            }
        )

    override fun onCreate() {
        super.onCreate()

        Injector.initAppComponent(this)

        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
    }

    companion object {
        private const val INTERESTING_SIZE = 3L * 1024 * 1024 * 1024
        private const val PROBABILITY = 1 / 100L
    }
}
