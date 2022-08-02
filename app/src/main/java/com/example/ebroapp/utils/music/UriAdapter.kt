package com.example.ebroapp.utils.music

import android.net.Uri
import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonToken
import com.google.gson.stream.JsonWriter
import java.io.IOException


class UriAdapter : TypeAdapter<Uri?>() {

    @Throws(IOException::class)
    override fun read(reader: JsonReader): Uri? {
        reader.beginObject()
        var uri: Uri? = null
        var fieldName: String? = null
        while (reader.hasNext()) {
            val token: JsonToken = reader.peek()
            if (token == JsonToken.NAME) {
                fieldName = reader.nextName()
            }
            if ("uri" == fieldName) {
                reader.peek()
                uri = Uri.parse(reader.nextString())
            }
        }
        reader.endObject()
        return uri
    }

    @Throws(IOException::class)
    override fun write(writer: JsonWriter, uri: Uri?) {
        writer.beginObject()
        writer.name("uri")
        writer.value(uri.toString())
        writer.endObject()
    }
}