package com.example.hackernews.activity.detail

import android.util.Log
import com.example.hackernews.model.CommentModel
import com.example.hackernews.services.RetrovitServices
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailPresenter(private val view: DetailContract.View): DetailContract.Presenter {
    override fun loadComment(id: Int) {
        val call = RetrovitServices.sendStoriesRequest()?.comment(id)
        if (call != null) {
            call.enqueue(object : Callback<CommentModel> {
                override fun onResponse(call: Call<CommentModel>, response: Response<CommentModel>) {
                    if (response.isSuccessful) {
                        response.body()?.let { view.showComment(it) }
                    }
                }

                override fun onFailure(call: Call<CommentModel>, t: Throwable) {
                    Log.d("ERROR", "onFailure: " + t.message)
                }
            })
        }
    }

}