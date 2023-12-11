package org.wit.scholar.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
@Parcelize
data class ScholarModel(var id: Long = 0,
                        var scholarName: String = "",
                        var gradeYear: String = "") : Parcelable
