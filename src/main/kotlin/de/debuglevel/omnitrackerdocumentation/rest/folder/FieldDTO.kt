package de.debuglevel.omnitrackerdocumentation.rest.folder

data class FieldDTO(val id: Int,
                    val name: String?,
                    val nameStringTranslationId: Int?,
                    val nameStringTranslationGuid: String?,
                    val comment: String?,
                    val commentStringTranslationId: Int?,
                    val commentStringTranslationGuid: String?,
                    val description: String?,
                    val descriptionStringTranslationId: Int?,
                    val descriptionStringTranslationGuid: String?)
