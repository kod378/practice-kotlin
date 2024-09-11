import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    kotlin("jvm")
    id("org.springframework.boot")
    id("io.spring.dependency-management")
//    kotlin("plugin.jpa")
    kotlin("plugin.spring")
}

dependencies {
    implementation("org.slf4j:slf4j-api:2.0.13")
    implementation("jakarta.validation:jakarta.validation-api:3.0.2")

    //annotation
    implementation("org.springframework:spring-context")
    implementation("org.springframework:spring-core")
}

tasks {
    named<BootJar>("bootJar") {
        enabled = false
    }

    named<Jar>("jar") {
        enabled = true
    }
}