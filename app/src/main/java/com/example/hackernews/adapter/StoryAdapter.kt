package com.example.hackernews.adapter

import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.hackernews.R
import com.example.hackernews.model.StoryModel
import org.jetbrains.anko.find

class StoryAdapter: RecyclerView.Adapter<StoryAdapter.ViewHolder>() {
    var listStory: List<StoryModel> = mutableListOf()
    private lateinit var actionClickListener: ActionClickListener

    fun setOnActionClickListener(actionClickListener: ActionClickListener) {
        this.actionClickListener = actionClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_list_story, parent, false))

    override fun getItemCount(): Int = listStory.size

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val story = listStory[position]
        viewHolder.bind(story, actionClickListener)
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        private val cvItem: CardView = view.find(R.id.cv_item)
        private val tvTitle: TextView = view.find(R.id.tv_title)
        private val tvComment: TextView = view.find(R.id.tv_comment)
        private val tvScore: TextView = view.find(R.id.tv_score)

        fun bind(story: StoryModel, actionClickListener: ActionClickListener) {
            tvTitle.text = story.title
            tvComment.text = story.kids?.size.toString()
            tvScore.text = story.score.toString()

            cvItem.setOnClickListener { actionClickListener.onItemClickListener(story) }
        }
    }

    interface ActionClickListener {
        fun onItemClickListener(story: StoryModel)
    }
}