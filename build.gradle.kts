// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false
    alias(libs.plugins.spotless) apply true
}

spotless{
    kotlin {
        target("**/*.kt")
        ktlint().editorConfigOverride(mapOf("ktlint_function_naming_ignore_when_annotated_with" to "Composable"))
        targetExclude("**/generated/**", "**/build/**")
    }
}