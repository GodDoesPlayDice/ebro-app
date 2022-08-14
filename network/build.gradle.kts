plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    compileSdk = Deps.compileSdk

    defaultConfig {
        minSdk = Deps.minSdk
        targetSdk = Deps.targetSdk

        testInstrumentationRunner = Deps.testAndroidInstrumentationRunner
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(Deps.core)

    implementation(Deps.dagger)

    implementation(Deps.retrofit)
    implementation(Deps.retrofitConverter)
    implementation(Deps.loggingInterceptor)
}