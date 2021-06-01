import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "2.4.4"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    kotlin("jvm") version "1.4.31"
    kotlin("plugin.spring") version "1.4.31"
}

group = "com.example"
version = "1.0.0"
java.sourceCompatibility = JavaVersion.VERSION_1_8

repositories {
    mavenCentral()

    maven{
        url = uri("https://mvnrepository.com/artifact/io.jsonwebtoken/jjwt")
    }

    maven{
        url = uri("https://mvnrepository.com/artifact/org.projectlombok/lombok")
    }

    maven{
        url = uri("https://mvnrepository.com/artifact/com.google.code.gson/gson")
    }
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.springframework.boot:spring-boot-starter-jdbc")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.xerial:sqlite-jdbc:3.34.0")
    implementation("io.jsonwebtoken:jjwt:0.9.1")
    implementation("org.projectlombok:lombok:0.11.0")
    implementation("com.google.code.gson:gson:2.7")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "1.8"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
