package com.example.hackernews.activity.main

import com.example.hackernews.BasePresenter
import com.example.hackernews.BaseView
import com.example.hackernews.model.StoryModel

interface MainContract {
    interface Presenter: BasePresenter {
        fun loadIDTopStories()
        fun loadStories(id: Int)
    }

    interface View: BaseView<Presenter> {
        fun showIDTopStories(idStories: List<Int>)
        fun showStory(story: StoryModel)
    }
}