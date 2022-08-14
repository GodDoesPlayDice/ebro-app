plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
}

android {
    compileSdk = Deps.compileSdk

    defaultConfig {
        applicationId = Deps.applicationId
        minSdk = Deps.minSdk
        targetSdk = Deps.targetSdk
        versionCode = Deps.versionCode
        versionName = Deps.versionName
        testInstrumentationRunner = Deps.testAndroidInstrumentationRunner
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

    implementation(Deps.core)
    implementation(Deps.appCompat)
    implementation(Deps.constraintLayout)
    implementation(Deps.legacy)
    implementation(Deps.fragment)
    implementation(Deps.navigationFragment)
    implementation(Deps.navigationUi)

    implementation(Deps.material)
    implementation(Deps.gson)
    implementation(Deps.gms)
    implementation(Deps.coroutines)

    implementation(Deps.dagger)
    kapt(Deps.daggerCompiler)

    implementation(Deps.picasso)

    implementation(Deps.navigationMapbox)

    implementation(Deps.timber)
}