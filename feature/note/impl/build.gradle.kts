plugins {
    alias(libs.plugins.tasky.android.library)
}

android {
    namespace = "com.example.feature.note.impl"
}

dependencies {
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
}
