import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("com.google.cloud.tools.jib") version "3.1.4"
	id("org.springframework.boot") version "3.1.3"
	id("io.spring.dependency-management") version "1.1.3"

	val kotlinVer = "1.8.22"
	kotlin("jvm") version kotlinVer
	kotlin("plugin.spring") version kotlinVer
	kotlin("plugin.jpa") version kotlinVer
	kotlin("plugin.allopen") version kotlinVer
	kotlin("plugin.noarg") version kotlinVer
}

group = "com.usktea"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_17
}

allOpen {
	annotation("jakarta.persistence.Entity")
	annotation("jakarta.persistence.Embeddable")
	annotation("jakarta.persistence.MappedSuperclass")
}

noArg {
	annotation("jakarta.persistence.Entity")
	annotation("jakarta.persistence.Embeddable")
	annotation("jakarta.persistence.MappedSuperclass")
}

repositories {
	mavenCentral()
}

extra["springCloudVersion"] = "2022.0.4"

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.springframework.cloud:spring-cloud-starter-openfeign")
	runtimeOnly("com.h2database:h2")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
}

dependencyManagement {
	imports {
		mavenBom("org.springframework.cloud:spring-cloud-dependencies:${property("springCloudVersion")}")
	}
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

jib {
	from {
		image = "openjdk:17"
		platforms {
			platform {
				architecture = "amd64"
				os = "linux"
			}
		}
	}
	to {
		image = "docker.io/${project.findProperty("DOCKERHUB_USERNAME")}/jib-practice"
		tags = setOf("latest")
		auth {
			username = project.findProperty("DOCKERHUB_USERNAME").toString()
			password = project.findProperty("DOCKERHUB_PASSWORD").toString()
		}
	}
	container {
		creationTime = "USE_CURRENT_TIMESTAMP"
		jvmFlags = listOf("-Dspring.profiles.active=local", "-XX:+UseContainerSupport", "-Dserver.port=8080", "-Dfile.encoding=UTF-8")
	}
}
