package org.wit.scholar.main

import android.app.Application
import org.wit.scholar.models.ScholarJSONStore
import org.wit.scholar.models.ScholarMemStore
import org.wit.scholar.models.ScholarStore
import timber.log.Timber
import timber.log.Timber.Forest.i

class ScholarApp : Application() {
    //val scholars = ArrayList<ScholarModel>()
    //val scholars = ScholarMemStore()

    lateinit var scholars: ScholarStore

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        scholars = ScholarJSONStore(applicationContext)
        i("Scholar started")

    }
}