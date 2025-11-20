plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    // Plugin wajib untuk Kotlin 2.0 + Compose
    id("org.jetbrains.kotlin.plugin.compose") version "2.0.0"
}

android {
    namespace = "id.antasari.p7_modern_230104040123"
    compileSdk = 34

    defaultConfig {
        applicationId = "id.antasari.p7_modern_230104040123"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {
    // --- DEPENDENCY STANDAR ---
    implementation("androidx.core:core-ktx:1.13.1")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.12.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")

    // --- INTEGRASI COMPOSE ---
    implementation("androidx.activity:activity-compose:1.9.0")

    // --- MATERIAL DESIGN 3 (WAJIB) ---
    implementation("androidx.compose.material3:material3:1.2.1")

    // --- SOLUSI ERROR ICON HILANG (WAJIB) ---
    // Library ini berisi icon: Book, Code, Computer, Star, dll.
    implementation("androidx.compose.material:material-icons-extended:1.6.7")

    // --- UI COMPOSE CORE ---
    val composeUiVersion = "1.6.7"
    implementation("androidx.compose.ui:ui:$composeUiVersion")
    implementation("androidx.compose.ui:ui-graphics:$composeUiVersion")
    implementation("androidx.compose.ui:ui-tooling-preview:$composeUiVersion")

    // --- DEBUGGING ---
    debugImplementation("androidx.compose.ui:ui-tooling:$composeUiVersion")
    debugImplementation("androidx.compose.ui:ui-test-manifest:$composeUiVersion")

    // --- TESTING ---
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:$composeUiVersion")
}