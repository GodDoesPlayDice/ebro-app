// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "7.1.1" apply false
    id("com.android.library") version "7.1.1" apply false
    id("org.jetbrains.kotlin.android") version "1.6.21" apply false
    id("org.jetbrains.kotlin.jvm") version "1.6.21" apply false
    id("io.gitlab.arturbosch.detekt") version ("1.21.0")
}

tasks {
    register("detektAll", io.gitlab.arturbosch.detekt.Detekt::class) {
        parallel = true
        setSource(files(projectDir))
        config.setFrom(files("${rootProject.projectDir}/detekt.yml"))
        buildUponDefaultConfig = true
    }

    register("clean", Delete::class) {
        delete(rootProject.buildDir)
    }
}
