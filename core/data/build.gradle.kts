plugins {
    alias(libs.plugins.tasky.android.library)
}

android {
    namespace = "com.example.data"
}

dependencies {
    api(project(":core:model"))
    implementation(project(":core:database"))
    api(libs.kotlinx.coroutines.core)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
}
