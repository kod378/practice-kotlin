plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.8.0"
}
rootProject.name = "kotlindemo"
include("user-api")
include("db")
include("admin-api")
