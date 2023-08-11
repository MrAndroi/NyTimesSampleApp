package com.sami.core.datastore.serializers

import androidx.datastore.core.Serializer
import com.sami.core.datastore.models.AppDataModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import java.io.InputStream
import java.io.OutputStream

class AppDataSerializer(override val defaultValue: AppDataModel = AppDataModel()) : Serializer<AppDataModel> {

    override suspend fun readFrom(input: InputStream): AppDataModel {
        return try {
            Json.decodeFromString(
                deserializer = AppDataModel.serializer(),
                string = input.readBytes().decodeToString(),
            )
        } catch (e: SerializationException) {
            e.printStackTrace()
            defaultValue
        }
    }

    override suspend fun writeTo(t: AppDataModel, output: OutputStream) {
        withContext(Dispatchers.IO) {
            output.write(
                Json.encodeToString(
                    serializer = AppDataModel.serializer(),
                    value = t,
                ).toByteArray()
            )
        }
    }
}