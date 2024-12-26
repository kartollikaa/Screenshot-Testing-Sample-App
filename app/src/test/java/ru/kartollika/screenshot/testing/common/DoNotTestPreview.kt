package ru.kartollika.screenshot.testing.common

/**
 * Annotation to completely ignore previews from testing automatically
 * The scanner implemented in [CustomPreviewTester] excluded all previews with such annotation
 */
annotation class DoNotTestPreview
