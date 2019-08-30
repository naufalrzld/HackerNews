package com.example.hackernews.activity.detail

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.example.hackernews.R
import com.example.hackernews.adapter.CommentAdapter
import com.example.hackernews.model.CommentModel
import com.example.hackernews.model.StoryModel
import kotlinx.android.synthetic.main.activity_detail.*
import java.text.SimpleDateFormat
import java.util.*

class DetailActivity : AppCompatActivity(), DetailContract.View {
    private lateinit var presenter: DetailContract.Presenter
    private var comments = mutableListOf<CommentModel>()
    private lateinit var adapter: CommentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        presenter = DetailPresenter(this)
        adapter = CommentAdapter()

        val dataIntent = intent
        val storyModel: StoryModel? = dataIntent.getParcelableExtra<StoryModel>("data")

        val sdf = SimpleDateFormat("dd/MM/yyyy")
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = storyModel?.time?.toLong() ?: 0

        tv_title.text = "By ${storyModel?.title}"
        tv_author.text = storyModel?.by ?: "-"
        tv_date.text = sdf.format(calendar.time)

        val comments = storyModel?.kids
        if (comments != null) {
            for (data in comments) {
                presenter.loadComment(data)
            }
        }

        rv_comment.setHasFixedSize(true)
        rv_comment.layoutManager = LinearLayoutManager(this)
        rv_comment.adapter = adapter
    }

    override fun showComment(comment: CommentModel) {
        comments.add(comment)
        adapter.listComment = comments
        adapter.notifyDataSetChanged()
    }

    override fun setPresenter(presenter: DetailContract.Presenter) {
        this.presenter = presenter
    }
}
