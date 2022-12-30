object Deps {

    const val APPLICATION_ID = "com.example.ebroapp"
    const val COMPILE_SDK = 31
    const val MIN_SDK = 24
    const val TARGET_SDK = 31

    // Sem Ver: Major.Minor.Patch
    const val VERSION_CODE = 10_014
    const val VERSION_NAME = "1.0.14"

    const val TEST_ANDROID_INSTRUMENTATION_RUNNER = "androidx.test.runner.AndroidJUnitRunner"

    // Dependencies
    const val ANDROID_VERSION = "7.1.1"
    const val KOTLIN_VERSION = "1.6.21"
    const val DETEKT_VERSION = "1.22.0"

    private const val LINT_VERSION = "30.0.0"
    private const val NAVIGATION_VERSION = "2.5.1"
    private const val DAGGER_VERSION = "2.40"
    private const val RETROFIT_VERSION = "2.9.0"

    const val ANDROID_APPLICATION = "com.android.application"
    const val ANDROID_LIBRARY = "com.android.library"
    const val KOTLIN_ANDROID = "org.jetbrains.kotlin.android"
    const val KOTLIN_JVM = "org.jetbrains.kotlin.jvm"
    const val DETEKT = "io.gitlab.arturbosch.detekt"
    const val DETEKT_FORMATTING = "io.gitlab.arturbosch.detekt:detekt-formatting:$DETEKT_VERSION"

    const val CORE = "androidx.core:core-ktx:1.8.0"
    const val APP_COMPAT = "androidx.appcompat:appcompat:1.4.2"
    const val CONSTRAINT_LAYOUT = "androidx.constraintlayout:constraintlayout:2.1.4"
    const val LEGACY = "androidx.legacy:legacy-support-v4:1.0.0"
    const val FRAGMENT = "androidx.fragment:fragment-ktx:1.5.1"

    const val NAVIGATION_FRAGMENT = "androidx.navigation:navigation-fragment-ktx:$NAVIGATION_VERSION"
    const val NAVIGATION_UI = "androidx.navigation:navigation-ui-ktx:$NAVIGATION_VERSION"

    const val MATERIAL = "com.google.android.material:material:1.6.1"
    const val GSON = "com.google.code.gson:gson:2.9.0"
    const val GMS = "com.google.android.gms:play-services-location:20.0.0"
    const val COROUTINES = "org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.1"

    const val DAGGER = "com.google.dagger:dagger:$DAGGER_VERSION"
    const val DAGGER_COMPILER = "com.google.dagger:dagger-compiler:$DAGGER_VERSION"

    const val PICASSO = "com.squareup.picasso:picasso:2.71828"

//    const val NAVIGATION_MAPBOX = "com.mapbox.navigation:android:2.3.0"

    const val TIMBER = "com.jakewharton.timber:timber:5.0.1"

    const val RETROFIT = "com.squareup.retrofit2:retrofit:$RETROFIT_VERSION"
    const val RETROFIT_CONVERTER = "com.squareup.retrofit2:converter-gson:$RETROFIT_VERSION"
    const val LOGGING_INTERCEPTOR = "com.squareup.okhttp3:logging-interceptor:4.9.0"

    const val KTLINT = "com.pinterest:ktlint:0.47.1"

    const val LINT_API = "com.android.tools.lint:lint-api:$LINT_VERSION"
    const val LINT_CHECKS = "com.android.tools.lint:lint-checks:$LINT_VERSION"
}
