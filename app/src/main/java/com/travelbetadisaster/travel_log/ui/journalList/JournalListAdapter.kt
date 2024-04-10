package com.travelbetadisaster.travel_log.ui.journalList

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.travelbetadisaster.travel_log.R
import com.travelbetadisaster.travel_log.database.tables.Visit

interface OnItemClickListener {
    fun onItemClick(id: Int)
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
            holder.visitTitle.text = it!![position].name
            if (it[position].image != 0) {
                holder.visitThumbnail.setImageResource(it[position].image!!)
            } else
                holder.visitThumbnail.setImageResource(R.drawable.placeholder_image)
        }
        holder.itemView.setOnClickListener{
            journalList.let { listener.onItemClick(it!![position].id) }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setVisitList(visits: List<Visit>) {
        journalList = visits
        notifyDataSetChanged()
    }

    inner class JournalViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var visitTitle: TextView
        var visitThumbnail: ImageView
        init {
            visitTitle = itemView.findViewById(R.id.journalTitle)
            visitThumbnail = itemView.findViewById(R.id.journalImage)
        }
    }


    /*class JournalDiffCallback : DiffUtil.ItemCallback<Visit>() {
        override fun areItemsTheSame(oldItem: Visit, newItem: Visit): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Visit, newItem: Visit): Boolean {
            return oldItem == newItem
        }
    }*/
}
