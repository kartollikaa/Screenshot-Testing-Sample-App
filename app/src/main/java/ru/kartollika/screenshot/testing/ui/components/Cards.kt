package ru.kartollika.screenshot.testing.ui.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.kartollika.screenshot.testing.ui.theme.ScreenshotTestingSampleAppTheme

@Composable
fun SmallCard(
  modifier: Modifier = Modifier,
  content: @Composable ColumnScope.() -> Unit = {}
) {
  Card(
    modifier = modifier,
    shape = MaterialTheme.shapes.small,
    content = content
  )
}

@Composable
fun MediumCard(
  modifier: Modifier = Modifier,
  content: @Composable ColumnScope.() -> Unit = {}
) {
  Card(
    modifier = modifier,
    shape = MaterialTheme.shapes.medium,
    content = content
  )
}

@Composable
fun LargeCard(
  modifier: Modifier = Modifier,
  content: @Composable ColumnScope.() -> Unit = {}
) {
  Card(
    modifier = modifier,
    shape = MaterialTheme.shapes.extraLarge,
    content = content
  )
}

@Composable
private fun DefaultCardContent(
  modifier: Modifier = Modifier
) {
  Column(
    modifier = modifier
      .padding(16.dp),
    verticalArrangement = Arrangement.spacedBy(8.dp),
  ) {
    Text("Test")
    TextField(value = "", onValueChange = {})
    Button(
      onClick = {},
    ) {
      Text("Accept")
    }
  }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun SmallCardPreview() {
  ScreenshotTestingSampleAppTheme {
    SmallCard {
      DefaultCardContent()
    }
  }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun MediumCardPreview() {
  ScreenshotTestingSampleAppTheme {
    MediumCard {
      DefaultCardContent()
    }
  }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun LargeCardPreview() {
  ScreenshotTestingSampleAppTheme {
    LargeCard {
      DefaultCardContent()
    }
  }
}