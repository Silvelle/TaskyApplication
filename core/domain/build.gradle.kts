plugins {
    alias(libs.plugins.tasky.android.library)
    alias(libs.plugins.tasky.hilt)
}

android {
    namespace = "com.example.domain"
}

dependencies {
    api(project(":core:data"))
    implementation(project(":core:model"))

    testImplementation(libs.junit)
    testImplementation(libs.kotlinx.coroutines.test)
    androidTestImplementation(libs.androidx.junit)
}
