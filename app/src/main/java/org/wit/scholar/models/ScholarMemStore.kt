package org.wit.scholar.models

import timber.log.Timber.Forest.i

var lastId = 0L

internal fun getId(): Long {
    return lastId++
}
class ScholarMemStore : ScholarStore {

    val scholars = ArrayList<ScholarModel>()

    override fun findAll(): List<ScholarModel> {
        return scholars
    }

    override fun create(scholar: ScholarModel) {
        scholars.add(scholar)
        logAll()
    }

    fun logAll() {
        scholars.forEach{ i("${it}") }
    }
}