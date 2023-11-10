@file:Suppress("UnstableApiUsage")

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
    id("ru.ok.tracer") version "0.2.8"
}

android {
    compileSdk = Deps.COMPILE_SDK

    defaultConfig {
        applicationId = Deps.APPLICATION_ID
        minSdk = Deps.MIN_SDK
        targetSdk = Deps.TARGET_SDK
        versionCode = Deps.VERSION_CODE
        versionName = Deps.VERSION_NAME
        testInstrumentationRunner = Deps.TEST_ANDROID_INSTRUMENTATION_RUNNER
    }

    signingConfigs {
        create("sign") {
            storeFile = file("keystore.jks")
            storePassword = "123456"
            keyAlias = "key0"
            keyPassword = "123456"
        }
    }

    buildTypes {
        getByName("release").apply {
            isDebuggable = false
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            signingConfig = signingConfigs.getByName("sign")
        }
        getByName("debug").apply {
            signingConfig = signingConfigs.getByName("sign")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
        freeCompilerArgs = listOf(
            "-Xuse-experimental=kotlinx.coroutines.DelicateCoroutinesApi",
            "-Xjvm-default=all"
        )
    }
    buildFeatures {
        viewBinding = true
    }

    android.applicationVariants.all {
        outputs.all {
            val variantOutputImpl =
                this as com.android.build.gradle.internal.api.BaseVariantOutputImpl
            variantOutputImpl.outputFileName = "Ebro $versionName ${buildType.name}.apk"
        }
    }
}

tracer {
    create("defaultConfig") {
        pluginToken = "GvPWaETDEgr9Xl5lus5J2uI8HzmE1fC4tSdztVwyM0y1"
        appToken = "CUzRuhcZdxE7gMySkEXaXkBUu9B7aEODecYr0mtgvjv2"

        uploadMapping = true
    }
}

dependencies {
    api(project(":domain"))
    api(project(":network"))

    implementation(Deps.CORE)
    implementation(Deps.APP_COMPAT)
    implementation(Deps.CONSTRAINT_LAYOUT)
    implementation(Deps.LEGACY)
    implementation(Deps.FRAGMENT)
    implementation(Deps.NAVIGATION_FRAGMENT)
    implementation(Deps.NAVIGATION_UI)

    implementation(Deps.MATERIAL)
    implementation(Deps.GSON)
    implementation(Deps.GMS)
    implementation(Deps.COROUTINES)

    implementation(Deps.DAGGER)
    kapt(Deps.DAGGER_COMPILER)

    implementation(Deps.PICASSO)

//    implementation(Deps.NAVIGATION_MAPBOX)

    implementation(Deps.TIMBER)

    lintChecks(project(":custom_lint"))

    implementation("ru.ok.tracer:tracer-crash-report:0.2.8")
    implementation("ru.ok.tracer:tracer-crash-report-native:0.2.8")
    implementation("ru.ok.tracer:tracer-heap-dumps:0.2.8")
    implementation("ru.ok.tracer:tracer-disk-usage:0.2.8")
    implementation("ru.ok.tracer:tracer-profiler-sampling:0.2.8")
    implementation("ru.ok.tracer:tracer-profiler-systrace:0.2.8")
}
