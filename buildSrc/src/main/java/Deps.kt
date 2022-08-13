object Deps {

    const val applicationId = "com.example.ebroapp"
    const val compileSdk = 31
    const val minSdk = 24
    const val targetSdk = 31

    //Sem Ver: Major.Minor.Patch. If you update major or minor version you must check AppUpdatedDialog for correct updates info (more in README.md)
    const val versionCode = 10014
    const val versionName = "1.0.14"

    const val testAndroidInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

    // Dependencies
    private const val navigationVersion = "2.5.1"
    private const val daggerVersion = "2.40"
    private const val retrofitVersion = "2.9.0"

    const val core = "androidx.core:core-ktx:1.8.0"
    const val appCompat = "androidx.appcompat:appcompat:1.4.2"
    const val constraintLayout = "androidx.constraintlayout:constraintlayout:2.1.4"
    const val legacy = "androidx.legacy:legacy-support-v4:1.0.0"
    const val fragment = "androidx.fragment:fragment-ktx:1.5.1"

    const val navigationFragment = "androidx.navigation:navigation-fragment-ktx:$navigationVersion"
    const val navigationUi = "androidx.navigation:navigation-ui-ktx:$navigationVersion"

    const val material = "com.google.android.material:material:1.6.1"
    const val gson = "com.google.code.gson:gson:2.9.0"
    const val gms = "com.google.android.gms:play-services-location:20.0.0"
    const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.1"

    const val dagger = "com.google.dagger:dagger:$daggerVersion"
    const val daggerCompiler = "com.google.dagger:dagger-compiler:$daggerVersion"

    const val picasso = "com.squareup.picasso:picasso:2.71828"

    const val navigationMapbox = "com.mapbox.navigation:android:2.3.0"

    const val timber = "com.jakewharton.timber:timber:5.0.1"

    const val retrofit = "com.squareup.retrofit2:retrofit:$retrofitVersion"
    const val retrofitConverter = "com.squareup.retrofit2:converter-gson:$retrofitVersion"
    const val loggingInterceptor = "com.squareup.okhttp3:logging-interceptor:4.9.0"
}