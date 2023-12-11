package org.wit.scholar.main

import android.app.Application
import org.wit.scholar.models.ScholarMemStore
import org.wit.scholar.models.ScholarModel
import timber.log.Timber
import timber.log.Timber.Forest.i

class ScholarApp : Application() {
    //val scholars = ArrayList<ScholarModel>()
    val scholars = ScholarMemStore()

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        i("Scholar started")

    }
}