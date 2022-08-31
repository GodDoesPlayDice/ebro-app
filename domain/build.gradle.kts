plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    compileSdk = Deps.COMPILE_SDK

    defaultConfig {
        minSdk = Deps.MIN_SDK
        targetSdk = Deps.TARGET_SDK

        testInstrumentationRunner = Deps.TEST_ANDROID_INSTRUMENTATION_RUNNER
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
    implementation(Deps.CORE)
    implementation(Deps.GSON)
    implementation(Deps.DAGGER)
}
