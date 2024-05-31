pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven{ url =  uri("https://jitpack.io") }
        maven { url =  uri("https://europe-west3-maven.pkg.dev/talsec-artifact-repository/freerasp") }
    }
}

rootProject.name = "Trying freeRASP"
include(":app")
 