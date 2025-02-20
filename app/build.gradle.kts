plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.mobilei.homework1"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.mobilei.homework1"
        minSdk = 25
        targetSdk = 34
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
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {

    implementation("androidx.datastore:datastore-preferences:1.0.0")
    implementation("com.google.maps.android:maps-compose:2.11.4")
    implementation("com.google.android.gms:play-services-maps:18.2.0")
    implementation(libs.androidx.foundation)
    implementation("io.coil-kt:coil-compose:2.1.0")
    implementation(libs.zxing.core)  // QR Code Generation
    implementation(libs.ui) // Jetpack Compose

    implementation(libs.firebase.auth)
    implementation(platform(libs.firebase.bom))
    implementation(libs.play.services.auth)

    implementation(libs.androidx.credentials)
    implementation(libs.androidx.credentials.play.services.auth)
    implementation (libs.kotlinx.serialization.json)
    implementation(libs.core)
    implementation(libs.androidx.compose.material)
    implementation(libs.androidx.runtime.livedata)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    androidTestImplementation (libs.androidx.navigation.testing)
    implementation (libs.androidx.navigation.dynamic.features.fragment)
    implementation (libs.androidx.navigation.fragment)
    implementation (libs.androidx.navigation.ui)
    implementation (libs.androidx.navigation.compose)

    implementation (libs.androidx.ui.text.google.fonts)
    implementation(libs.coil.compose)

    implementation(libs.coil3.coil.compose)
    implementation(libs.coil.network.okhttp)

    // https://mvnrepository.com/artifact/androidx.compose.material/material-icons-extended
    implementation(libs.androidx.material.icons.extended)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}