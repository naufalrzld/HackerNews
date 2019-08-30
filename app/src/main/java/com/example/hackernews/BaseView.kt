package com.example.hackernews

interface BaseView<T> {
    fun setPresenter(presenter: T)
}
