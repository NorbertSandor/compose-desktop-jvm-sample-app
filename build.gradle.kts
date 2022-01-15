import org.jetbrains.compose.compose
import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.6.10"
    id("org.jetbrains.compose") version "1.0.1"
    id("com.google.devtools.ksp") version "1.6.10-1.0.2"
}

allprojects {
    repositories {
        mavenCentral()
        google()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }
}

dependencies {
    implementation(compose.desktop.currentOs)

    implementation(platform("io.arrow-kt:arrow-stack:1.0.2-alpha.28"))
    implementation("io.arrow-kt:arrow-core")
    implementation("io.arrow-kt:arrow-optics")
    ksp("io.arrow-kt:arrow-optics-ksp-plugin:1.0.2-alpha.28") // 1.0.1 is not available on mavenCentral()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "11"
}

compose.desktop {
    application {
        mainClass = "MainKt"
        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "compose-desktop-jvm-sample-app"
            packageVersion = "1.0.0"
        }
    }
}
