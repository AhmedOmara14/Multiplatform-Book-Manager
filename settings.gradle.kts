rootProject.name = "CMP-Bookpedia"
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
    repositories {
        // Allow all content from Google's Maven repository for plugins
        google()
        // Standard Maven Central repository for plugins
        mavenCentral()
        // The default repository for Gradle plugins
        gradlePluginPortal()
        // If you use any other custom repositories for plugins, add them here
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
    }
}

include(":composeApp")