package com.appsoft.dailyquotes

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class CategoryViewAdapter (context: Context?, data: List<String>) :
    RecyclerView.Adapter<CategoryViewAdapter.ViewHolder>() {
    private var categories: List<String>
    private val mInflater: LayoutInflater
    private var mClickListener: ItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewAdapter.ViewHolder {
        val view: View = mInflater.inflate(R.layout.category_row, parent, false)
        return ViewHolder(view)
    }

    // binds the data to the TextView in each row
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val category = categories[position]
        holder.item.text = category
    }

    override fun getItemCount(): Int {
        return categories.size
    }

    fun updateData(cats : List<String>) {
        categories = cats;
        notifyDataSetChanged()
    }

    inner class ViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        var item: TextView
        override fun onClick(view: View?) {
            if (mClickListener != null) {
                mClickListener?.onItemClick(view, absoluteAdapterPosition)
            }
        }


        init {
            item = itemView.findViewById(R.id.textView)
            itemView.setOnClickListener(this)
        }
    }

    // convenience method for getting data at click position
    fun getItem(id: Int): String {
        return categories[id]
    }

    // allows clicks events to be caught
    fun setClickListener(itemClickListener: ItemClickListener?) {
        mClickListener = itemClickListener
    }

    interface ItemClickListener {
        fun onItemClick(view: View?, position: Int)
    }

    init {
        mInflater = LayoutInflater.from(context)
        categories = data
    }
}