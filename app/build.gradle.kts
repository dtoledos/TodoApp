plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.compose.compiler)
    // Nota: Dependiendo de tu versión de Kotlin, podrías necesitar el plugin del compilador de Compose
    // id("org.jetbrains.kotlin.plugin.compose") version "2.0.0"
}

android {
    namespace = "org.todo.task.pe"
    compileSdk {
        version = release(36)
    }

    defaultConfig {
        applicationId = "org.todo.task.pe"
        minSdk = 29
        targetSdk = 36
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    // 1. ACTIVAR JETPACK COMPOSE
    buildFeatures {
        compose = true
    }

    // Si usas una versión de Kotlin menor a la 2.0, descomenta esto y ajusta la versión:
    // composeOptions {
    //     kotlinCompilerExtensionVersion = "1.5.1"
    // }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.foundation)
    //implementation(libs.androidx.runtime) // Puedes quitar esta si usas el BOM de abajo
    //implementation(libs.androidx.ui)      // Puedes quitar esta si usas el BOM de abajo
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    // 2. DEPENDENCIAS DE COMPOSE Y MATERIAL 3
    // Usamos el BOM (Bill of Materials) para gestionar las versiones de Compose automáticamente
    val composeBom = platform("androidx.compose:compose-bom:2024.10.00")
    implementation(composeBom)
    androidTestImplementation(composeBom)

    // Material Design 3 (Soluciona tu error de importación)
    implementation("androidx.compose.material3:material3")

    // Herramientas base de Compose
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")

    // Integración con Activity y ViewModel
    implementation("androidx.activity:activity-compose:1.8.2")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.7.0")

    // Íconos extendidos de Material Design (necesario para Icons.Outlined.Inbox)
    implementation("androidx.compose.material:material-icons-extended")
    implementation("com.google.android.gms:play-services-ads:23.1.0")


}