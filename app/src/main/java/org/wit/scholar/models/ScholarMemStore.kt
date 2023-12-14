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
        scholar.id = getId()
        scholars.add(scholar)
        logAll()
    }

    override fun update(scholar: ScholarModel) {
        var foundScholar: ScholarModel? = scholars.find { p -> p.id == scholar.id }
        if (foundScholar != null) {
            foundScholar.title = scholar.title
            foundScholar.gradeYear = scholar.gradeYear
            foundScholar.image = scholar.image
            logAll()
        }
    }

    private fun logAll() {
        scholars.forEach { i("$it") }
    }
    override fun delete(scholar: ScholarModel) {
        scholars.remove(scholar)
    }
}