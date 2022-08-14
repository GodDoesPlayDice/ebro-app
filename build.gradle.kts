// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "7.1.1" apply false
    id("com.android.library") version "7.1.1" apply false
    id("org.jetbrains.kotlin.android") version "1.6.21" apply false
    id("org.jetbrains.kotlin.jvm") version "1.6.21" apply false
    id("io.gitlab.arturbosch.detekt") version ("1.21.0")
}

detekt {
    parallel = true
    buildUponDefaultConfig = true
    allRules = false
    config.setFrom(files("$projectDir/detekt.yml"))
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}