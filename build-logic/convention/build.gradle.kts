plugins {
    `kotlin-dsl`
}

group = "com.example.tasky.buildlogic"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(17)
    }
}

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.ksp.gradlePlugin)
    compileOnly(libs.room.gradlePlugin)
}

tasks {
    validatePlugins {
        enableStricterValidation = true
        failOnWarning = true
    }
}

gradlePlugin {
    plugins {
        register("androidApplication") {
            id = "tasky.android.application"
            implementationClass =
                "com.example.tasky.buildlogic.AndroidApplicationConventionPlugin"
        }

        register("androidLibrary") {
            id = "tasky.android.library"
            implementationClass =
                "com.example.tasky.buildlogic.AndroidLibraryConventionPlugin"
        }

        register("androidCompose") {
            id = "tasky.android.compose"
            implementationClass =
                "com.example.tasky.buildlogic.AndroidComposeConventionPlugin"
        }

        register("androidRoom") {
            id = "tasky.android.room"
            implementationClass =
                "com.example.tasky.buildlogic.AndroidRoomConventionPlugin"
        }

        register("hilt") {
            id = "tasky.hilt"
            implementationClass =
                "com.example.tasky.buildlogic.HiltConventionPlugin"
        }
    }
}
