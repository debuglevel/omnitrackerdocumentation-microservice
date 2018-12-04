package de.debuglevel.omnitrackerdocumentation.rest.folder

import de.debuglevel.omnitrackerdatabasebinding.OmnitrackerDatabase
import de.debuglevel.omnitrackerdatabasebinding.models.StringTranslationLanguage
import de.debuglevel.omnitrackerdocumentation.rest.responsetransformer.JsonTransformer
import de.debuglevel.omnitrackerdocumentation.rest.responsetransformer.XmlTransformer
import mu.KotlinLogging
import spark.kotlin.RouteHandler

object FolderController {
    private val logger = KotlinLogging.logger {}

    fun getListJsonHandler(): RouteHandler.() -> String {
        return {
            logger.debug { "Getting folder list via JSON handler..." }
            getListJson()
        }
    }

    private fun RouteHandler.getListJson(): String {
        logger.debug { "Getting folder list as JSON..." }
        type(contentType = "application/json")
        return JsonTransformer.render(getList())
    }

    fun getListXmlHandler(): RouteHandler.() -> String {
        return {
            logger.debug { "Getting folder list via XML handler..." }
            getListXml()
        }
    }

    private fun RouteHandler.getListXml(): String {
        logger.debug { "Getting folder list as XML..." }
        type(contentType = "text/xml")
        return XmlTransformer.render(getList())
    }

    private fun getList(): List<FolderDTO> {
        logger.debug { "Getting folder list..." }
        return OmnitrackerDatabase().folders.values
                .map { folder ->
                    FolderDTO(
                            folder.id,
                            folder.name,
                            folder.path,
                            folder.fields.values
                                    .map { field ->
                                        FieldDTO(
                                                field.id,
                                                field.getName(StringTranslationLanguage.German)?.text,
                                                field.getName(StringTranslationLanguage.German)?.id,
                                                field.getName(StringTranslationLanguage.German)?.guid,
                                                field.getComment(StringTranslationLanguage.German)?.text,
                                                field.getComment(StringTranslationLanguage.German)?.id,
                                                field.getComment(StringTranslationLanguage.German)?.guid,
                                                field.getDescription(StringTranslationLanguage.German)?.text,
                                                field.getDescription(StringTranslationLanguage.German)?.id,
                                                field.getDescription(StringTranslationLanguage.German)?.guid)
                                    })
                }
    }

    fun getListFormatParameter(): RouteHandler.() -> String {
        return {
            logger.debug { "Getting folder list via custom handler..." }
            logger.debug { "Trying to determine by query parameter..." }
            when (request.queryParams("format")) {
                "xml" -> getListXml()
                "json" -> getListJson()
                else -> {
                    logger.debug { "No known query parameter set; trying to determine by accept header..." }
                    when (request.headers("accept")) {
                        "text/xml" -> getListXml()
                        "application/json" -> getListJson()
                        else -> getListJson()
                    }
                }
            }
        }
    }
}