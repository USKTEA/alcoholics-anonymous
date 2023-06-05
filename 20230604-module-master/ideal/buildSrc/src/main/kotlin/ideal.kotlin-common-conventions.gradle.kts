import gradle.kotlin.dsl.accessors._b37ce871afd0e63f793c9cb128ce80b2.implementation

plugins {
    kotlin("jvm")
    kotlin("plugin.spring")
    kotlin("plugin.noarg")
    kotlin("plugin.allopen")
    kotlin("kapt")
    id("org.springframework.boot")
    id("io.spring.dependency-management")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.apache.commons:commons-lang3")
    implementation("io.github.microutils:kotlin-logging-jvm:3.0.4")
    testImplementation("org.mockito.kotlin:mockito-kotlin:4.1.0")

    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
}

tasks.named<Test>("test") {
    useJUnitPlatform()
}
