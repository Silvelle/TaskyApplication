plugins {
    alias(libs.plugins.tasky.android.library)
}

android {
    namespace = "com.example.domain"
}

dependencies {
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
}
