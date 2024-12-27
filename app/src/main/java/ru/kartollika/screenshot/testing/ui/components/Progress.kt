package ru.kartollika.screenshot.testing.ui.components

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import ru.kartollika.screenshot.testing.ui.theme.ScreenshotTestingSampleAppTheme

@Composable
fun CircularProgress(modifier: Modifier = Modifier) {
  CircularProgressIndicator(
    modifier = modifier
  )
}

@Preview
@Composable
fun CircularProgressPreview() {
  ScreenshotTestingSampleAppTheme {
    Surface {
      CircularProgress()
    }
  }
}