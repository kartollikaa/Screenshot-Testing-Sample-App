package ru.kartollika.screenshot.testing.common

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.github.takahirom.roborazzi.ComposePreviewTester
import com.github.takahirom.roborazzi.ComposePreviewTester.Options
import com.github.takahirom.roborazzi.ExperimentalRoborazziApi
import sergio.sastre.composable.preview.scanner.android.AndroidComposablePreviewScanner
import sergio.sastre.composable.preview.scanner.android.AndroidPreviewInfo
import sergio.sastre.composable.preview.scanner.android.screenshotid.AndroidPreviewScreenshotIdBuilder
import sergio.sastre.composable.preview.scanner.core.preview.ComposablePreview

/**
 * Custom Roborazzi tester for Roborazzi Preview scanner
 */
@Suppress("unused")
@OptIn(ExperimentalRoborazziApi::class)
class CustomPreviewTester : ComposePreviewTester<AndroidPreviewInfo> {

  private val composeTestRule = createAndroidComposeRule<ComponentActivity>()

  override fun options(): Options {
    val testLifecycleOptions = ComposePreviewTester.Options.JUnit4TestLifecycleOptions(
        testRuleFactory = { composeTestRule }
    )

    return super
        .options()
        .copy(
            testLifecycleOptions = testLifecycleOptions
        )
  }

  @Suppress("SpreadOperator")
  override fun previews(): List<ComposablePreview<AndroidPreviewInfo>> {
    val options = options()
    return AndroidComposablePreviewScanner()
        .scanPackageTrees(*options.scanOptions.packages.toTypedArray())
        .excludeIfAnnotatedWithAnyOf(DoNotTestPreview::class.java)
        .includeAnnotationInfoForAllOf(PreviewRoborazziOptions::class.java)
        .let {
          if (options.scanOptions.includePrivatePreviews) {
            it.includePrivatePreviews()
          } else {
            it
          }
        }
        .getPreviews()
  }

  override fun test(preview: ComposablePreview<AndroidPreviewInfo>) {
    // This prevents infinitely running tests by using some infinite animation
    // https://github.com/takahirom/roborazzi/issues/413
    composeTestRule.mainClock.autoAdvance = false

    // Applying robolectric config from @Preview annotation
    RobolectricPreviewInfosApplier.applyFor(preview)
    composeTestRule.recordScreenshot(
        screenshotName = AndroidPreviewScreenshotIdBuilder(preview).build(),
        roborazziOptions = RoborazziOptionsMapper.createFor(preview),
    ) {
      preview()
    }
  }
}