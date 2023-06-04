plugins {
    kotlin("jvm")
    kotlin("plugin.spring")
    kotlin("plugin.noarg")
    kotlin("plugin.allopen")
    id("org.springframework.boot")
    id("io.spring.dependency-management")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(platform("org.jetbrains.kotlin:kotlin-bom"))
    implementation("org.jetbrains.kotlin:kotlin-stdlib")
    testImplementation("org.junit.jupiter:junit-jupiter:5.8.2")
}

tasks.named<Test>("test") {
    useJUnitPlatform()
}
