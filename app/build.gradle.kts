plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
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
    viewBinding {
        isEnabled = true
    }
    buildFeatures {
        dataBinding = true
    }

    android.applicationVariants.all {
        outputs.all {
            val variantOutputImpl =
                this as com.android.build.gradle.internal.api.BaseVariantOutputImpl
            variantOutputImpl.outputFileName = "Ebro $versionName ${buildType.name}.apk"
        }
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
}
