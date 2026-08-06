plugins {
    alias(libs.plugins.tasky.android.library)
    alias(libs.plugins.jetbrains.kotlin.serialization)

}


android {
    namespace = "com.example.model"
}

dependencies {
    testImplementation(libs.junit)
    implementation(libs.kotlinx.serialization.core)
    androidTestImplementation(libs.androidx.junit)
}
