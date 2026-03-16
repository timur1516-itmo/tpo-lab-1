import org.gradle.api.tasks.testing.Test
import org.gradle.testing.jacoco.tasks.JacocoReport

plugins {
    id("jacoco")
}

group = "ru.itmo.tpo.lab1"
version = "1.0-SNAPSHOT"

subprojects {
    apply(plugin = "java")
    apply(plugin = "jacoco")

    group = rootProject.group
    version = rootProject.version

    repositories {
        mavenCentral()
    }

    dependencies {
        "testImplementation"(platform("org.junit:junit-bom:5.10.0"))
        "testImplementation"("org.junit.jupiter:junit-jupiter")
        "testRuntimeOnly"("org.junit.platform:junit-platform-launcher")
    }

    tasks.named<Test>("test") {
        useJUnitPlatform()
        finalizedBy(tasks.named("jacocoTestReport"))
    }

    tasks.named<JacocoReport>("jacocoTestReport") {
        dependsOn(tasks.named("test"))

        reports {
            xml.required.set(true)
            csv.required.set(false)
            html.required.set(true)
        }
    }
}
