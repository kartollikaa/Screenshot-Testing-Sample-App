plugins {
  alias(libs.plugins.android.application)
  alias(libs.plugins.kotlin.android)
  alias(libs.plugins.kotlin.compose)
  alias(libs.plugins.roborazzi)
}

android {
  namespace = "ru.kartollika.screenshot.testing"
  compileSdk = 35

  defaultConfig {
    applicationId = "ru.kartollika.screenshot.testing"
    minSdk = 24
    targetSdk = 35
    versionCode = 1
    versionName = "1.0"

    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
  }

  buildTypes {
    release {
      isMinifyEnabled = false
      proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
    }
  }
  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
  }
  kotlinOptions {
    jvmTarget = "17"
  }
  buildFeatures {
    compose = true
  }

  testOptions {
    unitTests {
      isIncludeAndroidResources = true

      // Roborazzi recommends to enable hardware render mode to enable PixelCopy
      all {
        it.systemProperties["robolectric.pixelCopyRenderMode"] = "hardware"
      }
    }
  }
}

dependencies {

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
  testImplementation(libs.androidx.ui.test.junit4)
  testDebugImplementation(libs.roborazzi)
  testDebugImplementation(libs.robolectric)
  testDebugImplementation(libs.sergioSastre.composablePreviewScanner)
  testDebugImplementation(libs.roborazzi.compose.preview.scanner.support)
}

roborazzi {
  generateComposePreviewRobolectricTests {
    enable = true
    packages = listOf("ru.kartollika.screenshot.testing.ui.components")
    includePrivatePreviews = true
    testerQualifiedClassName = "ru.kartollika.screenshot.testing.common.CustomPreviewTester"
  }
}