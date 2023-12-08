package org.wit.scholar.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import timber.log.Timber
import timber.log.Timber.Forest.i
import org.wit.scholar.databinding.ActivityScholarBinding
import org.wit.scholar.main.MainApp
import org.wit.scholar.models.ScholarModel


class ScholarActivity : AppCompatActivity() {
    private lateinit var binding: ActivityScholarBinding
    var scholar = ScholarModel()
    //val scholars = ArrayList<ScholarModel>()
    lateinit var app : MainApp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScholarBinding.inflate(layoutInflater)
        setContentView(binding.root)

        app = application as MainApp
        i("Scholar Activity started...")

        binding.btnAdd.setOnClickListener() {
            scholar.scholarName = binding.scholarName.text.toString()
            scholar.gradeYear = binding.gradeYear.text.toString()
            if (scholar.scholarName.isNotEmpty()) {
                app.scholars.add(scholar.copy())
                i("add Button Pressed: ${scholar}")
                for (i in app.scholars.indices) {
                    i("Scholar[$i]:{this.app.scholars[i]}")
                }
                setResult(RESULT_OK)
                finish()
            } else {
                Snackbar.make(it, "Please Enter a Name", Snackbar.LENGTH_LONG)
                    .show()
            }
        }
    }
}