pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}
@Suppress("UnstableApiUsage")
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
//        maven {
//            setUrl("https://api.mapbox.com/downloads/v2/releases/maven")
//            authentication {
//                create<BasicAuthentication>("basic")
//            }
//            credentials {
//                username = "mapbox"
//                password = System.getenv("MAPBOX_DOWNLOADS_TOKEN")
//            }
//        }
    }
}
rootProject.name = "ebroapp"
include(":app", ":domain", ":network")
