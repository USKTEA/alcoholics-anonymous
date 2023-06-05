plugins {
    `kotlin-dsl`
}

repositories {
    gradlePluginPortal()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-gradle-plugin:3.0.0")
    implementation("io.spring.gradle:dependency-management-plugin:1.1.0")
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.6.10")
    implementation("org.jetbrains.kotlin:kotlin-allopen:1.6.10")
    implementation("org.jetbrains.kotlin:kotlin-noarg:1.6.10")
    implementation("org.jetbrains.kotlin.kapt:org.jetbrains.kotlin.kapt.gradle.plugin:1.6.10")
}