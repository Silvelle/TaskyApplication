plugins {
    alias(libs.plugins.tasky.android.library)
    alias(libs.plugins.tasky.android.compose)
}

android {
    namespace = "com.example.navigation"
}

dependencies {
    implementation(libs.androidx.compose.runtime)
    implementation(libs.androidx.lifecycle.viewmodel.navigation3)
    implementation(libs.androidx.navigation3.runtime)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
}
