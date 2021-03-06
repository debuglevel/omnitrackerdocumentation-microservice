package de.debuglevel.omnitrackerdocumentation.rest.responsetransformer

import com.google.gson.GsonBuilder
import mu.KotlinLogging
import spark.ResponseTransformer

/**
 * Transformer which converts objects to JSON
 * Note: the responseTransformer parameter will be removed in Spark Kotlin and must be called explicitly.
 */
object JsonTransformer : ResponseTransformer {
    private val logger = KotlinLogging.logger {}

    private val gson = GsonBuilder()
            .setPrettyPrinting()
            .create()

    override fun render(model: Any?): String {
        return gson.toJson(model)
    }
}