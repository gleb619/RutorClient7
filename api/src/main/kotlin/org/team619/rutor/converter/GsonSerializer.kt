package org.team619.rutor.converter

import com.google.gson.Gson
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonWriter
import org.team619.rutor.converter.gson.RuntimeTypeAdapterFactory
import org.team619.rutor.core.Page
import org.team619.rutor.core.Serializer
import org.team619.rutor.model.EmptyPage
import org.team619.rutor.util.Constants
import java.io.*


class GsonSerializer(private val gson: Gson) : Serializer {

    override fun to(input: Page): InputStream {
        val outputStream = ByteArrayOutputStream()
        val bufferedStream = BufferedOutputStream(outputStream, 65536)
        val streamWriter = OutputStreamWriter(bufferedStream, Constants.CHARSET)
        val writer = JsonWriter(streamWriter)
        gson.toJson(input, input::class.java, writer)
        writer.close()
        streamWriter.close()
        bufferedStream.close()
        outputStream.close()
        val byteArray = outputStream.toByteArray()

        return ByteArrayInputStream(byteArray)
    }

    fun from(input: InputStream): Page {
        val reader = JsonReader(InputStreamReader(input, Constants.CHARSET))
        reader.beginObject()
        val output = if(reader.hasNext()){
            gson.fromJson(reader, Page::class.java)
        } else {
            EmptyPage()
        }
        reader.endObject()
        reader.close()

        return output
    }

}
