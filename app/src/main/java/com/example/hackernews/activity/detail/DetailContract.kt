package com.example.hackernews.activity.detail

import com.example.hackernews.BaseView
import com.example.hackernews.model.CommentModel

interface DetailContract {
    interface Presenter {
        fun loadComment(id: Int)
    }

    interface View: BaseView<Presenter> {
        fun showComment(comment: CommentModel)
    }
}