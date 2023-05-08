package com.criticaltechworks.topheadlines.core.network

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonParseException
import com.google.gson.JsonPrimitive
import com.google.gson.JsonSerializationContext
import com.google.gson.JsonSerializer
import org.threeten.bp.LocalDateTime
import org.threeten.bp.ZoneOffset
import org.threeten.bp.format.DateTimeFormatter
import java.lang.reflect.Type

class LocalDateTimeGsonConverter : JsonDeserializer<LocalDateTime>, JsonSerializer<LocalDateTime> {
    @Throws(JsonParseException::class)
    override fun deserialize(
        jsonElement: JsonElement,
        typeOF: Type,
        context: JsonDeserializationContext
    ): LocalDateTime? {
        return DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSSS'Z'")
            .withZone(ZoneOffset.UTC).parse(
                jsonElement.asString,
                LocalDateTime::from
            )
    }

    override fun serialize(
        src: LocalDateTime?,
        typeOfSrc: Type?,
        context: JsonSerializationContext?
    ): JsonElement {
        return JsonPrimitive(src?.format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSSS'Z'")))
    }
}

