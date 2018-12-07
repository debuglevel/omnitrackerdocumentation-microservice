package de.debuglevel.omnitrackerdocumentation.rest.folder

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty

class FolderDTO {
    /**
     * Primary constructor cannot be used, as @JacksonXmlProperty of "field" would not work anymore (as of 2018-12-04)
     * https://github.com/FasterXML/jackson-module-kotlin/issues/153
     */
    constructor(id: Int,
            //name: String,
                path: String,
                alias: String?,
                name: String?,
                nameStringTranslationId: Int?,
                nameStringTranslationGuid: String?,
                description: String?,
                descriptionStringTranslationId: Int?,
                descriptionStringTranslationGuid: String?,
                fields: List<FieldDTO>) {
        this.id = id
        //this.name = name
        this.path = path
        this.alias = alias
        this.name = name
        this.nameStringTranslationId = nameStringTranslationId
        this.nameStringTranslationGuid = nameStringTranslationGuid
        this.description = description
        this.descriptionStringTranslationId = descriptionStringTranslationId
        this.descriptionStringTranslationGuid = descriptionStringTranslationGuid
        this.fields = fields
    }

    val id: Int
    //val name: String
    val path: String
    val alias: String?

    val name: String?
    val nameStringTranslationId: Int?
    val nameStringTranslationGuid: String?
    val description: String?
    val descriptionStringTranslationId: Int?
    val descriptionStringTranslationGuid: String?

    @JacksonXmlElementWrapper(localName = "fields")
    @JacksonXmlProperty(localName = "field")
    val fields: List<FieldDTO>

}