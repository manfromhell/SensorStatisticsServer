plugins {
    id ("org.springframework.boot") version "2.5.12";
    id ("io.spring.dependency-management") version "1.0.11.RELEASE";
    id ("java");
}

group = "com.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation ("org.springframework.boot:spring-boot-starter-web");
    implementation ("org.springframework.boot:spring-boot-starter-actuator");
    implementation ("org.springframework.boot:spring-boot-starter-thymeleaf");
    implementation ("org.springframework.boot:spring-boot-starter-data-jpa");
    implementation ("org.projectlombok:lombok:1.18.30");
    runtimeOnly ("org.postgresql:postgresql");
    testImplementation ("org.springframework.boot:spring-boot-starter-test");
}

tasks.test {
    useJUnitPlatform()
}