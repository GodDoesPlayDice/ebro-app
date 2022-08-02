plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
}

android {
    compileSdk = 31

    defaultConfig {
        applicationId = "com.example.ebroapp"
        minSdk = 24
        targetSdk = 31
        versionCode = 10014
        versionName = "1.0.14"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    signingConfigs {
        create("sign") {
            storeFile = file("keystore.jks")
            storePassword = "123456"
            keyAlias = "key0"
            keyPassword = "123456"
        }
    }

    android.applicationVariants.all {
        outputs.all {
            val variantOutputImpl =
                this as com.android.build.gradle.internal.api.BaseVariantOutputImpl
            variantOutputImpl.outputFileName = "Ebro $versionName ${buildType.name}.apk"
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
        freeCompilerArgs =
            freeCompilerArgs + "-Xuse-experimental=kotlinx.coroutines.DelicateCoroutinesApi"
        freeCompilerArgs = freeCompilerArgs + "-Xjvm-default=all"
    }
    viewBinding {
        isEnabled = true
    }
    buildFeatures {
        dataBinding = true
    }
}

dependencies {
    implementation("androidx.core:core-ktx:1.8.0")
    implementation("androidx.appcompat:appcompat:1.4.2")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.legacy:legacy-support-v4:1.0.0")
    implementation("androidx.fragment:fragment-ktx:1.5.0")
    implementation("androidx.navigation:navigation-fragment-ktx:2.5.0")
    implementation("androidx.navigation:navigation-ui-ktx:2.5.0")

    implementation("com.google.android.material:material:1.6.1")
    implementation("com.google.code.gson:gson:2.9.0")
    implementation("com.google.android.gms:play-services-location:20.0.0")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.1")

    implementation("com.google.dagger:dagger:2.16")
    kapt("com.google.dagger:dagger-compiler:2.16")

    implementation("com.squareup.picasso:picasso:2.71828")
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.9.0")

    implementation("com.mapbox.navigation:android:2.3.0")

    implementation("com.jakewharton.timber:timber:5.0.1")
}