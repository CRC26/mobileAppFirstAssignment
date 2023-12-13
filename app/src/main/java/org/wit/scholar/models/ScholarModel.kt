package org.wit.scholar.models
import android.net.Uri
import android.os.Parcelable
import kotlinx.parcelize.Parcelize
@Parcelize
data class ScholarModel(var id: Long = 0,
                        var title: String = "",
                        var gradeYear: String = "",
                        var image: Uri = Uri.EMPTY) : Parcelable
