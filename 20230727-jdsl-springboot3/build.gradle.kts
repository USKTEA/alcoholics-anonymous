import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "3.1.2"
	id("io.spring.dependency-management") version "1.0.13.RELEASE"
	kotlin("jvm") version "1.9.0"
	kotlin("plugin.spring") version "1.9.0"
	kotlin("plugin.jpa") version "1.9.0"
	kotlin("plugin.allopen") version "1.9.0"
	kotlin("plugin.noarg") version "1.9.0"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-webflux")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	testImplementation("org.springframework.boot:spring-boot-starter-test")

	// just add these dependencies for use kotlin-jdsl
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")

	// coroutine
	val coroutineVersion = "1.7.3"
	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-jdk8:$coroutineVersion")
	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactive:$coroutineVersion")
	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutineVersion")
	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor:$coroutineVersion")

	// reactive
	val jdslVersion = "2.2.1.RELEASE"
	implementation("com.linecorp.kotlin-jdsl:kotlin-jdsl-reactive-core-jakarta:$jdslVersion")
	implementation("com.linecorp.kotlin-jdsl:spring-data-kotlin-jdsl-reactive-core-jakarta:2.2.1.RELEASE")
	implementation("com.linecorp.kotlin-jdsl:hibernate-reactive-kotlin-jdsl-jakarta:2.2.1.RELEASE")
	implementation("com.linecorp.kotlin-jdsl:spring-data-kotlin-jdsl-hibernate-reactive-jakarta:$jdslVersion")
	implementation("org.springframework.data:spring-data-commons:3.1.2")
	implementation("org.hibernate.reactive:hibernate-reactive-core-jakarta:1.1.9.Final")
	implementation("io.smallrye.reactive:mutiny-kotlin:2.3.1")
	implementation("org.hibernate.orm:hibernate-core:6.2.7.Final")
	// h2 db reactive
	implementation("io.agroal:agroal-pool:2.0")
	implementation("com.h2database:h2")
	implementation("io.vertx:vertx-jdbc-client:4.4.4")
}

kotlin {
	jvmToolchain(17)
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs += "-Xjsr305=strict"
		jvmTarget = "17"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
