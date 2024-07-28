plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.google.services)
    id("kotlin-kapt")
    alias(libs.plugins.dagger.hilt)
    id ("io.realm.kotlin")

}

android {
    namespace = "com.example.baoiaminventoryapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.baoiaminventoryapp"
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    //splash screen
    implementation ("androidx.compose.ui:ui:1.6.8")
    implementation ("androidx.compose.material3:material3:1.2.1")
    implementation ("androidx.compose.ui:ui-tooling-preview:1.6.8")
    implementation ("androidx.navigation:navigation-compose:2.7.7")
    implementation (libs.androidx.lifecycle.runtime.ktx.v251)
    //icons
    implementation("androidx.compose.material:material-icons-extended:1.6.8")
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)

    //Firebase
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.analytics)
    implementation(libs.androidx.constraintlayout.compose)
    implementation(libs.firebase.auth)
    implementation(libs.androidx.navigation.runtime.ktx)
    implementation ("androidx.navigation:navigation-fragment-compose:2.8.0-beta04")
    implementation(libs.androidx.navigation.compose)
    implementation(libs.firebase.auth.ktx)
    implementation("com.google.firebase:firebase-firestore")


    // Coroutine Lifecycle Scopes
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.5.1")

    //Dagger - Hilt
    implementation("com.google.dagger:hilt-android:2.48")
    implementation(libs.androidx.appcompat)
    kapt("com.google.dagger:hilt-compiler:2.48")
    kapt("androidx.hilt:hilt-compiler:1.0.0")
    implementation("androidx.hilt:hilt-navigation-compose:1.0.0")



    //bar code scanner
    implementation("com.google.android.gms:play-services-code-scanner:16.0.0-beta3")

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
    implementation(kotlin("script-runtime"))
    implementation (libs.androidx.material.icons.extended)

    //mongoDB realm libs3
    implementation ("io.realm.kotlin:library-base:1.16.0")
    // If using Device Sync
    implementation ("io.realm.kotlin:library-sync:1.16.0")
    // If using coroutines with the SDK
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.0")
}

kapt {
    correctErrorTypes = true
}
