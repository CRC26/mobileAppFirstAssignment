package org.wit.scholar.activities

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso
import org.wit.scholar.R
import org.wit.scholar.databinding.ActivityScholarBinding
import org.wit.scholar.main.ScholarApp
import org.wit.scholar.models.ScholarModel
import org.wit.scholar.helpers.showImagePicker
import timber.log.Timber.Forest.i

class ScholarActivity : AppCompatActivity() {

    private lateinit var binding: ActivityScholarBinding
    var scholar = ScholarModel()
    lateinit var app: ScholarApp
    private lateinit var imageIntentLauncher : ActivityResultLauncher<Intent>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var edit = false

        binding = ActivityScholarBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.toolbarAdd.title = title
        setSupportActionBar(binding.toolbarAdd)

        app = application as ScholarApp

        i("Scholar Activity started...")

        if (intent.hasExtra("scholar_edit")) {
            edit = true
            scholar = intent.extras?.getParcelable("scholar_edit")!!
            binding.scholarTitle.setText(scholar.title)
            binding.gradeYear.setText(scholar.gradeYear)
            binding.btnAdd.setText(R.string.save_scholar)
            Picasso.get()
                .load(scholar.image)
                .into(binding.scholarImage)
            if (scholar.image != Uri.EMPTY) {
                binding.chooseImage.setText(R.string.change_scholar_image)
            }

        }

        binding.btnAdd.setOnClickListener() {
            scholar.title = binding.scholarTitle.text.toString()
            scholar.gradeYear = binding.gradeYear.text.toString()
            if (scholar.title.isEmpty()) {
                Snackbar.make(it,R.string.enter_scholar_title, Snackbar.LENGTH_LONG)
                    .show()
            } else {
                if (edit) {
                    app.scholars.update(scholar.copy())
                } else {
                    app.scholars.create(scholar.copy())
                }
            }
            i("add Button Pressed: $scholar")
            setResult(RESULT_OK)
            finish()
        }

        binding.chooseImage.setOnClickListener {
            showImagePicker(imageIntentLauncher, this)
        }

        registerImagePickerCallback()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_scholar, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_cancel -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun registerImagePickerCallback() {
        imageIntentLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult())
            { result ->
                when (result.resultCode) {
                    RESULT_OK -> {
                        if (result.data != null) {
                            i("Got Result ${result.data!!.data}")

                            val image = result.data!!.data!!
                            contentResolver.takePersistableUriPermission(
                                image,
                                Intent.FLAG_GRANT_READ_URI_PERMISSION
                            )
                            scholar.image = image

                            Picasso.get()
                                .load(scholar.image)
                                .into(binding.scholarImage)
                            binding.chooseImage.setText(R.string.change_scholar_image)
                        } // end of if
                    }

                    RESULT_CANCELED -> {}
                    else -> {}
                }
            }
    }

}