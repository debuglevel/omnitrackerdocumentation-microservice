package de.debuglevel.omnitrackerdocumentation.rest.folder

import de.debuglevel.omnitrackerdatabasebinding.OmnitrackerDatabase
import de.debuglevel.omnitrackerdatabasebinding.models.StringTranslationLanguage
import de.debuglevel.omnitrackerdocumentation.rest.responsetransformer.JsonTransformer
import de.debuglevel.omnitrackerdocumentation.rest.responsetransformer.XmlTransformer
import mu.KotlinLogging
import spark.kotlin.RouteHandler

object FolderController {
    private val logger = KotlinLogging.logger {}

    fun getListJson(): RouteHandler.() -> String {
        return {
            type(contentType = "application/json")
            JsonTransformer.render(getList())
        }
    }

    fun getListXml(): RouteHandler.() -> String {
        return {
            type(contentType = "text/xml")
            XmlTransformer.render(getList())
        }
    }

    private fun getList(): List<FolderDTO> {
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
}