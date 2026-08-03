plugins {
    alias(libs.plugins.tasky.android.library)
}

android {
    namespace = "com.example.domain"
}

dependencies {
    api(project(":core:data"))

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
}
