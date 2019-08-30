package com.example.hackernews.activity.main

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.util.Log
import com.example.hackernews.R
import com.example.hackernews.activity.detail.DetailActivity
import com.example.hackernews.adapter.StoryAdapter
import com.example.hackernews.model.StoryModel
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity

class MainActivity : AppCompatActivity(), MainContract.View, StoryAdapter.ActionClickListener {
    private lateinit var presenter: MainContract.Presenter
    private var topStories = mutableListOf<StoryModel>()
    private lateinit var adapter: StoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter = MainPresenter(this)
        presenter.start()
        adapter = StoryAdapter()
        adapter.setOnActionClickListener(this)

        rv_list.setHasFixedSize(true)
        rv_list.layoutManager = GridLayoutManager(this, 2)
        rv_list.adapter = adapter

        Log.d("TOP STORIES", topStories.toString())
    }

    override fun showIDTopStories(idStories: List<Int>) {
        for (i in 0..99) {
            presenter.loadStories(idStories[i])
        }
    }

    override fun showStory(story: StoryModel) {
        topStories.add(story)
        adapter.listStory = topStories
        adapter.notifyDataSetChanged()
    }

    override fun setPresenter(presenter: MainContract.Presenter) {
        this.presenter = presenter
    }

    override fun onItemClickListener(story: StoryModel) {
        startActivity<DetailActivity>("data" to story)
    }
}
