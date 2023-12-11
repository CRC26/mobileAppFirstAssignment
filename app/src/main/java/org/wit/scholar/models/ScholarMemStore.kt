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
        var foundScholar: ScholarModel? = scholars.find { p -> p.id == scholar.id }
        if (foundScholar != null) {
            foundScholar.scholarName = scholar.scholarName
            foundScholar.gradeYear = scholar.gradeYear
        logAll()
    }
}

    private fun logAll() {
        scholars.forEach{ i("${it}") }
    }
}