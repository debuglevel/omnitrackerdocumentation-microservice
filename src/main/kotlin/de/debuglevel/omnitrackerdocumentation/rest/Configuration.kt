package de.debuglevel.omnitrackerdocumentation.rest

import com.natpryce.konfig.Configuration
import com.natpryce.konfig.ConfigurationProperties
import com.natpryce.konfig.ConfigurationProperties.Companion.systemProperties
import com.natpryce.konfig.EnvironmentVariables
import com.natpryce.konfig.overriding
import java.io.File

object Configuration {
    val configuration: Configuration

    init {
        var config: Configuration = systemProperties()

        config = config overriding
                EnvironmentVariables()

        config = config overriding
                ConfigurationProperties.fromOptionalFile(File("configuration.properties"))

        val defaultsPropertiesFilename = "defaults.properties"
        if (ClassLoader.getSystemClassLoader().getResource(defaultsPropertiesFilename) != null) {
            config = config overriding
                    ConfigurationProperties.fromResource(defaultsPropertiesFilename)
        }

        configuration = config
    }

    //val mongodbUrl = configuration.getOrNull(Key("mongodb.url", stringType)) ?: "localhost:27017"
}