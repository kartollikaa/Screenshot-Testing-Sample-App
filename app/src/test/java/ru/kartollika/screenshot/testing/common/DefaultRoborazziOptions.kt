package ru.kartollika.screenshot.testing.common

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import androidx.compose.ui.test.onRoot
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.github.takahirom.roborazzi.DefaultFileNameGenerator
import com.github.takahirom.roborazzi.ExperimentalRoborazziApi
import com.github.takahirom.roborazzi.InternalRoborazziApi
import com.github.takahirom.roborazzi.RoborazziOptions
import com.github.takahirom.roborazzi.RoborazziOptions.CompareOptions
import com.github.takahirom.roborazzi.RoborazziOptions.RecordOptions
import com.github.takahirom.roborazzi.captureRoboImage
import com.github.takahirom.roborazzi.provideRoborazziContext
import java.io.File

private const val DefaultOutputDirectory = "src/test/screenshots"

val DefaultRoborazziOptions =
  RoborazziOptions(
    // Pixel-perfect matching
    compareOptions = CompareOptions(changeThreshold = 0f),
    // Reduce the size of the PNGs
    recordOptions = RecordOptions(resizeScale = 0.5),
  )

@OptIn(InternalRoborazziApi::class, ExperimentalRoborazziApi::class)
fun <A : ComponentActivity> AndroidComposeTestRule<ActivityScenarioRule<A>, A>.recordScreenshot(
  screenshotName: String = File(DefaultFileNameGenerator.generateFilePath()).nameWithoutExtension,
  outputDirectory: String = DefaultOutputDirectory,
  roborazziOptions: RoborazziOptions = DefaultRoborazziOptions,
  body: @Composable () -> Unit,
) {
  this.activity.setContent {
    CompositionLocalProvider(
      LocalInspectionMode provides true,
    ) {
      body()
    }
  }

  this.onRoot()
    .captureRoboImage(
      "$outputDirectory/$screenshotName.${provideRoborazziContext().imageExtension}",
      roborazziOptions = roborazziOptions,
    )
}