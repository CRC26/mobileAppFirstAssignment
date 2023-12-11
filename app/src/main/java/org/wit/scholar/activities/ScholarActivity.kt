package org.wit.scholar.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.google.android.material.snackbar.Snackbar
import org.wit.scholar.R
import timber.log.Timber.Forest.i
import org.wit.scholar.databinding.ActivityScholarBinding
import org.wit.scholar.main.ScholarApp
import org.wit.scholar.models.ScholarModel

class ScholarActivity : AppCompatActivity() {
    private lateinit var binding: ActivityScholarBinding
    var scholar = ScholarModel()
    //val scholars = ArrayList<ScholarModel>()
    lateinit var app : ScholarApp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScholarBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toolbarAdd.title = title
        setSupportActionBar(binding.toolbarAdd)

        app = application as ScholarApp
        i("Scholar Activity started...")

        binding.btnAdd.setOnClickListener() {
            scholar.scholarName = binding.scholarName.text.toString()
            scholar.gradeYear = binding.gradeYear.text.toString()
            if (scholar.scholarName.isNotEmpty()) {
                app.scholars.create(scholar.copy())
                setResult(RESULT_OK)
                finish()
            } else {
                Snackbar.make(it, "Please Enter a Name", Snackbar.LENGTH_LONG)
                    .show()
            }
        }
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_scholar, menu)
        return super.onCreateOptionsMenu(menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_cancel -> { finish() }
        }
        return super.onOptionsItemSelected(item)
    }
}