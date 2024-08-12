plugins {
	kotlin("jvm") version "1.9.24"
	kotlin("plugin.spring") version "1.9.24"
	id("org.springframework.boot") version "3.2.8"
	id("io.spring.dependency-management") version "1.1.6"
	id("com.github.node-gradle.node") version "7.0.2"
	kotlin("plugin.jpa") version "1.9.24"
}

group = "com.spa-rest"
version = "0.0.1-SNAPSHOT"

node {
	download.set(false)
	version.set("16.14.0") // Or whatever Node.js version you're using
	npmVersion.set("8.19.4") // Or whatever npm version you're using
	// Set the working directory where your package.json is located
	workDir.set(file("${project.projectDir}/frontend"))
	npmWorkDir.set(file("${project.projectDir}/frontend"))
	nodeProjectDir.set(file("${project.projectDir}/frontend"))
}

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(17)
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	runtimeOnly("com.h2database:h2")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

kotlin {
	compilerOptions {
		freeCompilerArgs.addAll("-Xjsr305=strict")
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}

tasks.register<com.github.gradle.node.npm.task.NpmTask>("buildReact") {
	dependsOn(tasks.npmInstall)
	npmCommand.set(listOf("run", "build"))
	group = "frontend"
	description = "Build the React application"
	// The workingDir is set from the node extension above
}

tasks.register<Copy>("copyReactBuild") {
	dependsOn("buildReact")
	from("${project.projectDir}/frontend/build")
	into("${project.projectDir}/src/main/resources/static")
	group = "frontend"
	description = "Copy React build output to Spring Boot static resources folder"
}

tasks.named("processResources") {
	dependsOn("copyReactBuild")
}
