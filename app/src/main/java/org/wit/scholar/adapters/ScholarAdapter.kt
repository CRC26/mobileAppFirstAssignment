package org.wit.scholar.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.wit.scholar.databinding.CardScholarBinding
import org.wit.scholar.models.ScholarModel

interface ScholarListener {
    fun onScholarClick(scholar: ScholarModel)
}
class ScholarAdapter constructor(private var scholars: List<ScholarModel>,
                                 private val listener: ScholarListener) :
    RecyclerView.Adapter<ScholarAdapter.MainHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        val binding = CardScholarBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return MainHolder(binding)
    }

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        val scholar = scholars[holder.adapterPosition]
        holder.bind(scholar, listener)
    }

    override fun getItemCount(): Int = scholars.size

    class MainHolder(private val binding : CardScholarBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(scholar: ScholarModel, listener: ScholarListener) {
            binding.scholarName.text = scholar.scholarName
            binding.gradeYear.text = scholar.gradeYear
            binding.root.setOnClickListener { listener.onScholarClick(scholar) }
        }
    }
}