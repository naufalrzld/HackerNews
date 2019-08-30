package com.example.hackernews.activity.main

import android.util.Log
import com.example.hackernews.model.StoryModel
import com.example.hackernews.services.RetrovitServices
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainPresenter(private val view: MainContract.View):
    MainContract.Presenter {
    override fun loadStories(id: Int) {
        val call = RetrovitServices.sendStoriesRequest()?.stories(id)
        if (call != null) {
            call.enqueue(object : Callback<StoryModel> {
                override fun onResponse(call: Call<StoryModel>, response: Response<StoryModel>) {
                    if (response.isSuccessful) {
                        response.body()?.let { view.showStory(it) }
                    }
                }

                override fun onFailure(call: Call<StoryModel>, t: Throwable) {
                    Log.d("ERROR", "onFailure: " + t.message)
                }
            })
        }
    }

    override fun loadIDTopStories() {
        val call = RetrovitServices.sendStoriesRequest()?.topStories()
        if (call != null) {
            call.enqueue(object : Callback<List<Int>> {
                override fun onResponse(call: Call<List<Int>>, response: Response<List<Int>>) {
                    if (response.isSuccessful) {
                        response.body()?.let { view.showIDTopStories(it) }
                    }
                }

                override fun onFailure(call: Call<List<Int>>, t: Throwable) {
                    Log.d("ERROR", "onFailure: " + t.message)
                }
            })
        }
    }

    override fun start() {
        loadIDTopStories()
    }

}