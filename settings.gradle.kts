plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.8.0"
}
rootProject.name = "kotlindemo"
include("db")
include("user-api")
include("admin-api")
include("common")
