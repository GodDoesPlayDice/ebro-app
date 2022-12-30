plugins {
    `java-library`
    id("kotlin")
}

tasks.jar {
    manifest {
        attributes["Lint-Registry-v2"] = "com.example.customlint.CustomIssueRegistry"
    }
}

dependencies {
    compileOnly("org.jetbrains.kotlin:kotlin-gradle-plugin:${Deps.KOTLIN_VERSION}")
    compileOnly(Deps.LINT_API)
    compileOnly(Deps.LINT_CHECKS)
}
