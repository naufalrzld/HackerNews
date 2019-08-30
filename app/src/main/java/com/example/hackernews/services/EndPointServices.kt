package com.example.hackernews.services

import com.example.hackernews.model.CommentModel
import com.example.hackernews.model.StoryModel

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface EndPointServices {
    @GET("item/{id}.json")
    fun stories(@Path("id") id: Int): Call<StoryModel>

    @GET("topstories.json")
    fun topStories(): Call<List<Int>>

    @GET("item/{id}.json")
    fun comment(@Path("id") id: Int): Call<CommentModel>
}
