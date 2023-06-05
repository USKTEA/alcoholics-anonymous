plugins {
    id("ideal.kotlin-library-conventions")
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

dependencies {
    implementation("org.springframework.data:spring-data-commons:3.0.0")
    implementation("jakarta.persistence:jakarta.persistence-api:3.1.0")

    implementation("com.querydsl:querydsl-jpa:5.0.0:jakarta")
    implementation("com.querydsl:querydsl-core:5.0.0")
    kapt("com.querydsl:querydsl-apt:5.0.0:jakarta")
}