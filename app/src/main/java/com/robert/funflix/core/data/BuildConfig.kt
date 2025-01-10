package com.robert.funflix.core.data

import java.io.File
import java.io.FileInputStream
import java.util.Properties

object BuildConfig {
    private val properties = Properties().apply {
        val localPropertiesFile = File("local.properties")
        if (localPropertiesFile.exists()) {
            load(FileInputStream(localPropertiesFile))
        }
    }

    val tmdbAccessToken: String = properties.getProperty("TMDB_ACCESS_TOKEN", "")
}