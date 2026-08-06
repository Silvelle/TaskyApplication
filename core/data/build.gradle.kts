plugins {
    alias(libs.plugins.tasky.android.library)
    alias(libs.plugins.tasky.hilt)
}

android {
    namespace = "com.example.data"
}

dependencies {
    implementation(project(":core:model"))
    implementation(project(":core:database"))
    api(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.serialization.json)
    testImplementation(libs.junit)
    testImplementation(libs.kotlinx.coroutines.test)
    androidTestImplementation(libs.androidx.junit)
}
