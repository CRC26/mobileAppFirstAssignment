package org.wit.scholar.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.wit.scholar.R
import org.wit.scholar.databinding.ActivityScholarListBinding
import org.wit.scholar.databinding.CardScholarBinding
import org.wit.scholar.main.MainApp
import org.wit.scholar.models.ScholarModel

class ScholarListActivity : AppCompatActivity() {

    lateinit var app: MainApp
    private lateinit var binding: ActivityScholarListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScholarListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.toolbar.title = title
        setSupportActionBar(binding.toolbar)

        app = application as MainApp

        val layoutManager = LinearLayoutManager(this)
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = ScholarAdapter(app.scholars)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_add -> {
                val launcherIntent = Intent(this, ScholarActivity::class.java)
                getResult.launch(launcherIntent)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private val getResult =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) {
            if (it.resultCode == Activity.RESULT_OK) {
                (binding.recyclerView.adapter)?.notifyItemRangeChanged(0, app.scholars.size)
            }
        }
}


class ScholarAdapter constructor(private var scholars: List<ScholarModel>) :
    RecyclerView.Adapter<ScholarAdapter.MainHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        val binding = CardScholarBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)

        return MainHolder(binding)
    }

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        val scholar = scholars[holder.adapterPosition]
        holder.bind(scholar)
    }

    override fun getItemCount(): Int = scholars.size

    class MainHolder(private val binding : CardScholarBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(scholar: ScholarModel) {
            binding.scholarName.text = scholar.scholarName
            binding.gradeYear.text = scholar.gradeYear
        }
    }
}








