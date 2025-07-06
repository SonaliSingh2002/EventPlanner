plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.ksp) // ✅ Uses version from libs.versions.toml

}

android {
    namespace = "com.eventplanner"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.eventplanner"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    viewBinding{ enable = true }
    dataBinding{ enable = true }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    // Lifecycle (LiveData + ViewModel)
    implementation (libs.androidx.lifecycle.livedata.ktx)
    implementation (libs.androidx.lifecycle.viewmodel.ktx)

    //room
    implementation (libs.androidx.room.runtime)
    ksp(libs.androidx.room.compiler) // Replace kapt with ksp
    //kapt (libs.androidx.room.compiler)
    implementation (libs.androidx.room.ktx) // For coroutine support


    // Coroutine support
    implementation (libs.kotlinx.coroutines.core)
    implementation (libs.kotlinx.coroutines.android)


// Optional: ViewModel scoped coroutine
    implementation (libs.androidx.lifecycle.viewmodel.ktx)

    implementation (libs.sdp.android)
    implementation (libs.ssp.android)
}