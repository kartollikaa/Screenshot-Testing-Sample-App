package ru.kartollika.screenshot.testing.common

import android.content.res.Configuration
import com.github.takahirom.roborazzi.RobolectricDeviceQualifiers
import org.robolectric.RuntimeEnvironment.setFontScale
import org.robolectric.RuntimeEnvironment.setQualifiers
import sergio.sastre.composable.preview.scanner.android.AndroidPreviewInfo
import sergio.sastre.composable.preview.scanner.android.device.domain.RobolectricDeviceQualifierBuilder
import sergio.sastre.composable.preview.scanner.core.preview.ComposablePreview

/**
 * Mapper from annotation [androidx.compose.ui.tooling.preview.Preview] to Robolectric configuration
 * May be not comprehensive, so adapt other parameters due to your demands
 * Full list of supported Robolectric parameters is described [here](https://robolectric.org/device-configuration/)
 */
object RobolectricPreviewInfosApplier {
  fun applyFor(preview: ComposablePreview<AndroidPreviewInfo>) {
    setDevice(preview.previewInfo.device)
    setUiMode(preview.previewInfo.uiMode)
    setLocale(preview.previewInfo.locale)
    setFontScale(preview.previewInfo.fontScale)
    setDeviceWidth(preview.previewInfo.widthDp)
    setDeviceHeight(preview.previewInfo.heightDp)
  }

  private fun setDeviceHeight(heightDp: Int) {
    if (heightDp == -1) return
    setQualifiers("+h${heightDp}dp")
  }

  private fun setDeviceWidth(widthDp: Int) {
    if (widthDp == -1) return
    setQualifiers("+w${widthDp}dp")
  }

  private fun setDevice(device: String) {
    val parsedDevice =
      RobolectricDeviceQualifierBuilder.build(device) ?: RobolectricDeviceQualifiers.Pixel4a
    setQualifiers(parsedDevice)
  }

  private fun setUiMode(uiMode: Int) {
    val nightMode =
      when (uiMode and Configuration.UI_MODE_NIGHT_MASK == Configuration.UI_MODE_NIGHT_YES) {
        true -> "night"
        false -> "notnight"
      }
    setQualifiers("+$nightMode")
  }

  private fun setLocale(locale: String) {
    val localeWithFallback = locale.ifBlank { "en" }
    setQualifiers("+$localeWithFallback")
  }
}