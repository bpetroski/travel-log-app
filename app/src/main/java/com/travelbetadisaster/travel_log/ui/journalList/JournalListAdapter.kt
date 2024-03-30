package com.travelbetadisaster.travel_log.ui.journalList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.travelbetadisaster.travel_log.R
import com.travelbetadisaster.travel_log.data.model.Journal

class JournalListAdapter(private val listener: OnItemClickListener? = null) :
    ListAdapter<JournalListFragment, JournalListAdapter.JournalViewHolder>(JournalDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JournalViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_journal, parent, false)
        return JournalViewHolder(view)
    }

    override fun onBindViewHolder(holder: JournalViewHolder, position: Int) {
        val journal = getItem(position)
        holder.bind(journal)
    }

    inner class JournalViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    listener?.onItemClick(getItem(position))
                }
            }
        }

        fun bind(journal: Journal) {
            itemView.journalTitle.text = journal.title
            itemView.journalDescription.text = journal.description
        }
    }

    interface OnItemClickListener {
        fun onItemClick(journal: Journal)
    }

    class JournalDiffCallback : DiffUtil.ItemCallback<Journal>() {
        override fun areItemsTheSame(oldItem: Journal, newItem: Journal): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Journal, newItem: Journal): Boolean {
            return oldItem == newItem
        }
    }
}
