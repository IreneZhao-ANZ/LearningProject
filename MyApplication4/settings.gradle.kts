pluginManagement {
    repositories {
        maven("https://artifactory.gcp.anz/artifactory/dl-google/dl/android/maven2/")
        maven("https://artifactory.gcp.anz/artifactory/gradle-plugins/m2/")
        maven("https://artifactory.gcp.anz/artifactory/maven-central/")
        maven("https://artifactory.gcp.anz/artifactory/plugins-m2-gradle")
    }
}
dependencyResolutionManagement {
    repositories {
        maven("https://artifactory.gcp.anz/artifactory/dl-google/dl/android/maven2/")
        maven("https://artifactory.gcp.anz/artifactory/gradle-plugins/m2/")
        maven("https://artifactory.gcp.anz/artifactory/maven-central/")
        maven("https://artifactory.gcp.anz/artifactory/plugins-m2-gradle")
        maven("https://artifactory.gcp.anz/artifactory/anzx-apis-kotlin-maven-releases/")
        google()
    }
}

rootProject.name = "My Application"
include(":app")
