package org.wit.scholar.models

import android.content.Context
import android.net.Uri
import com.google.gson.*
import com.google.gson.reflect.TypeToken
import org.wit.scholar.helpers.*
import timber.log.Timber
import java.lang.reflect.Type
import java.util.*

const val JSON_FILE = "scholars.json"
val gsonBuilder: Gson = GsonBuilder().setPrettyPrinting()
    .registerTypeAdapter(Uri::class.java, UriParser())
    .create()
val listType: Type = object : TypeToken<ArrayList<ScholarModel>>() {}.type

fun generateRandomId(): Long {
    return Random().nextLong()
}

class ScholarJSONStore(private val context: Context) : ScholarStore {

    var scholars = mutableListOf<ScholarModel>()

    init {
        if (exists(context, JSON_FILE)) {
            deserialize()
        }
    }

    override fun findAll(): MutableList<ScholarModel> {
        logAll()
        return scholars
    }

    override fun create(scholar: ScholarModel) {
        scholar.id = generateRandomId()
        scholars.add(scholar)
        serialize()
    }


    override fun update(scholar: ScholarModel) {
        val scholarsList = findAll() as ArrayList<ScholarModel>
        var foundScholar: ScholarModel? = scholars.find { p -> p.id == scholar.id }
        if (foundScholar != null) {
            foundScholar.title = scholar.title
            foundScholar.gradeYear = scholar.gradeYear
            foundScholar.image = scholar.image

        }
        serialize()
    }

    private fun serialize() {
        val jsonString = gsonBuilder.toJson(scholars, listType)
        write(context, JSON_FILE, jsonString)
    }

    private fun deserialize() {
        val jsonString = read(context, JSON_FILE)
        scholars = gsonBuilder.fromJson(jsonString, listType)
    }

    private fun logAll() {
        scholars.forEach { Timber.i("$it") }
    }

    override fun delete(scholar: ScholarModel) {
        scholars.remove(scholar)
        serialize()
    }
}

class UriParser : JsonDeserializer<Uri>,JsonSerializer<Uri> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): Uri {
        return Uri.parse(json?.asString)
    }

    override fun serialize(
        src: Uri?,
        typeOfSrc: Type?,
        context: JsonSerializationContext?
    ): JsonElement {
        return JsonPrimitive(src.toString())
    }


}
