package com.marcospb.testtribal.ui.categories.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.marcospb.testtribal.R
import com.marcospb.testtribal.databinding.ItemCategoryBinding

class CategoryAdapter(private val onCategoryClick: (category: String) -> Unit) :
    ListAdapter<String, CategoryAdapter.CatViewHolder>(CategoryDIffUtil()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_category, parent, false)
        return CatViewHolder(view)
    }

    override fun onBindViewHolder(holder: CatViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }


    inner class CatViewHolder(view: View) : ViewHolder(view) {

        private val binding = ItemCategoryBinding.bind(view)


        init {
            binding.root.setOnClickListener {
                val pos = adapterPosition

                if (pos != RecyclerView.NO_POSITION) {
                    onCategoryClick(getItem(pos))
                }
            }

        }

        fun bind(category: String) {
            binding.textView.setText(category.uppercase())
        }


    }


    class CategoryDIffUtil : DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }


    }


}