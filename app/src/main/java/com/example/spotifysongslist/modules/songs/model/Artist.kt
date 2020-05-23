package com.example.spotifysongslist.modules.songs.model

import com.google.gson.JsonElement
import com.google.gson.JsonObject
import java.io.Serializable

infix fun <T> Boolean.then(param: T): T? = if (this) param else null

class Artist(newJson: JsonObject?): Serializable {

    lateinit var name: String
    lateinit var image: String
    lateinit var pouplarity: String

    init {
        try {
            name       = getNullAsEmptyString(newJson?.get(NAME))
            image      = getNullAsEmptyString(newJson?.get(IMAGE))
            pouplarity = getNullAsEmptyString(newJson?.get(POPULARITY))
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    companion object {
        private const val NAME       = "title"
        private const val IMAGE      = "story_title"
        private const val POPULARITY = "story_url"
    }

    private fun getNullAsEmptyString(jsonElement: JsonElement?): String {
        jsonElement?.let {
            return it.isJsonNull then "" ?: it.asString
        }
        return ""
    }

}