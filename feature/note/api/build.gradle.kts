plugins {
    alias(libs.plugins.tasky.android.library)
}

android {
    namespace = "com.example.feature.note.api"
}

dependencies {
    implementation(project(":core:domain"))

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
}
