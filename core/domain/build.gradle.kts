plugins {
    alias(libs.plugins.tasky.android.library)
    alias(libs.plugins.tasky.hilt)
}

android {
    namespace = "com.example.domain"
}

dependencies {
    api(project(":core:data"))

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.kotlinx.coroutines.test)
}
