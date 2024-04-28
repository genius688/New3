pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
        maven { url=uri("https://mvn.mob.com/android")}
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.PREFER_SETTINGS)
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
        maven {url = uri("https://mvn.mob.com/android")}
    }
}

rootProject.name = "SmartStore"
include(":app")
