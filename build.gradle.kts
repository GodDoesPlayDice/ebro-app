import io.gitlab.arturbosch.detekt.Detekt

plugins {
    id(Deps.ANDROID_APPLICATION) version Deps.ANDROID_VERSION apply false
    id(Deps.ANDROID_LIBRARY) version Deps.ANDROID_VERSION apply false
    id(Deps.KOTLIN_ANDROID) version Deps.KOTLIN_VERSION apply false
    id(Deps.KOTLIN_JVM) version Deps.KOTLIN_VERSION apply false
    id(Deps.DETEKT) version Deps.DETEKT_VERSION
}

val configuration: Configuration by configurations.creating

dependencies {
    configuration(Deps.KTLINT) {
        attributes {
            attribute(Bundling.BUNDLING_ATTRIBUTE, objects.named(Bundling.EXTERNAL))
        }
    }
    detektPlugins(Deps.DETEKT_FORMATTING)
}

val ktlint by tasks.creating(JavaExec::class) {
    description = "Check Kotlin code style."
    classpath = configuration
    mainClass.set("com.pinterest.ktlint.Main")
    args = listOf("src/**/*.kt")
}

val ktlintFormat by tasks.creating(JavaExec::class) {
    description = "Fix Kotlin code style deviations."
    classpath = configuration
    mainClass.set("com.pinterest.ktlint.Main")
    args = listOf("-F", "src/**/*.kt")
    jvmArgs("--add-opens", "java.base/java.lang=ALL-UNNAMED")
}

detekt {
    source = files(projectDir)
    toolVersion = Deps.DETEKT_VERSION
    config = files("$rootDir/detekt.yml")
}

tasks.withType<Detekt>().configureEach {
    jvmTarget = "1.8"
    reports {
        listOf(xml, html, txt, sarif, md).forEach { it.required.set(false) }
    }
}

val clean by tasks.creating(Delete::class) {
    delete(rootProject.buildDir)
}
