package org.wit.scholar.models

interface ScholarStore {
    fun findAll(): List<ScholarModel>
    fun create(scholar: ScholarModel)

}