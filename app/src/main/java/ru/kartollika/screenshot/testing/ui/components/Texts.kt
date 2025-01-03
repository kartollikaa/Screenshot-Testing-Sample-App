package ru.kartollika.screenshot.testing.ui.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun Header(
  modifier: Modifier = Modifier,
  text: String
) {
  Text(
    modifier = modifier,
    text = text,
    style = MaterialTheme.typography.headlineMedium
  )
}