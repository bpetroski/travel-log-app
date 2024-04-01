package com.travelbetadisaster.travel_log.ui.journalList

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.travelbetadisaster.travel_log.R
import com.travelbetadisaster.travel_log.database.tables.Visit

interface OnItemClickListener {
    fun onItemClick(int: Int)
}

class JournalListAdapter(private val listener: OnItemClickListener) :
    RecyclerView.Adapter<JournalListAdapter.JournalViewHolder>()
{
    private var journalList: List<Visit>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JournalViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_journal, parent, false)
        return JournalViewHolder(view)
    }

    override fun getItemCount(): Int {
        return if (journalList == null) 0 else journalList!!.size
    }

    override fun onBindViewHolder(holder: JournalViewHolder, position: Int) {
        journalList.let {
            holder.visitTitle = it!![position].name
            holder.visitThumbnail = it[position].image //will probably need some kind of find by id
        }
        holder.itemView.setOnClickListener(
            journalList.let { listener.onItemClick(it!![position].id) }
        )
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setContactList(visits: List<Visit>) {
        journalList = visits
        notifyDataSetChanged()
    }

    inner class JournalViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var visitTitle: TextView
        var visitThumbnail: ImageView
        init {
            visitTitle = itemView.findViewById(R.id.visit_title)
            visitThumbnail = itemView.findViewById(R.id.visit_thumbnail)
        }
    }


    class JournalDiffCallback : DiffUtil.ItemCallback<Visit>() {
        override fun areItemsTheSame(oldItem: Visit, newItem: Visit): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Visit, newItem: Visit): Boolean {
            return oldItem == newItem
        }
    }
}
