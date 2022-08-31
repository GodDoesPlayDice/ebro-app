// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id(Deps.ANDROID_APPLICATION) version Deps.ANDROID_VERSION apply false
    id(Deps.ANDROID_LIBRARY) version Deps.ANDROID_VERSION apply false
    id(Deps.KOTLIN_ANDROID) version Deps.KOTLIN_VERSION apply false
    id(Deps.KOTLIN_JVM) version Deps.KOTLIN_VERSION apply false
    id(Deps.DETEKT) version Deps.DETEKT_VERSION
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
