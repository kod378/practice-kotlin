import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    kotlin("jvm")
    id("org.springframework.boot")
    id("io.spring.dependency-management")
    kotlin("plugin.spring")
}

dependencies {

    implementation("org.springframework.boot:spring-boot-starter-amqp") // RabbitMQ

    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.0.2")
    implementation("org.springframework.boot:spring-boot-starter-validation")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("io.kotest:kotest-assertions-core-jvm:5.8.0")
    testImplementation("io.kotest:kotest-runner-junit5-jvm:5.8.0")
    testImplementation("io.kotest.extensions:kotest-extensions-spring:1.1.2")

    implementation("org.springframework.security:spring-security-crypto:6.3.1") // password encoder
    implementation("io.jsonwebtoken:jjwt-api:0.11.5")
    runtimeOnly("io.jsonwebtoken:jjwt-impl:0.11.5")
    runtimeOnly("io.jsonwebtoken:jjwt-jackson:0.11.5")

    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.17.0")

    implementation(project(":db"))
    implementation(project(":common"))
}

tasks {
    named<BootJar>("bootJar") {
        enabled = true
    }

    named<Jar>("jar") {
        enabled = false
    }
}