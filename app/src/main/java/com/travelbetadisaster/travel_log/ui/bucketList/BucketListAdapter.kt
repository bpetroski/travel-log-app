package com.travelbetadisaster.travel_log.ui.journalList

import android.annotation.SuppressLint
import android.app.PendingIntent.getActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.travelbetadisaster.travel_log.R
import com.travelbetadisaster.travel_log.database.tables.BucketListEntry

interface OnEntryClickListener {
    fun onItemClick(id: Int)
}

interface OnCompleteClickListener {
    fun onCompleteClick(id: Int)
}

class BucketListAdapter(private val entryListener: OnEntryClickListener,
                        private val completeListener: OnCompleteClickListener ) :
    RecyclerView.Adapter<BucketListAdapter.BucketListViewHolder>()
{
    private var bucketList: List<BucketListEntry>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BucketListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_bucket_list, parent, false)
        return BucketListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return if (bucketList == null) 0 else bucketList!!.size
    }

    override fun onBindViewHolder(holder: BucketListViewHolder, position: Int) {
        bucketList.let {
            holder.title.text = it!![position].title
            holder.description.text = it!![position].description

        }
        holder.itemView.setOnClickListener{
            bucketList.let { entryListener.onItemClick(it!![position].id) }
        }
        holder.completed.setOnClickListener{
            bucketList.let { completeListener.onCompleteClick(it!![position].id) }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setBucketList(entries: List<BucketListEntry>) {
        bucketList = entries
        notifyDataSetChanged()
    }

    inner class BucketListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var title: TextView
        var description: TextView
        var completed: ImageView = itemView.findViewById(R.id.completed)
        //todo: find a checkmark resource


        init {
            title = itemView.findViewById(R.id.bucketListTitle)
            description = itemView.findViewById(R.id.bucketListDescription)
            completed.setImageResource(R.drawable.baseline_check_box_24)
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