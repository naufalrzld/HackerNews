package com.example.hackernews.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.hackernews.R
import com.example.hackernews.model.CommentModel
import org.jetbrains.anko.find

class CommentAdapter: RecyclerView.Adapter<CommentAdapter.ViewHolder>() {
    var listComment: List<CommentModel> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_list_comment, parent, false))

    override fun getItemCount(): Int = listComment.size

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val comment = listComment[position]
        viewHolder.bind(comment)
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        private val tvComment: TextView = view.find(R.id.tv_comment)

        fun bind(comment: CommentModel) {
            tvComment.text = comment.text
        }
    }
}