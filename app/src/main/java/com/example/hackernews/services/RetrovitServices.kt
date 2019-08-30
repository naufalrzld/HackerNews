package com.example.hackernews.services

class RetrovitServices {
    companion object {
        fun sendStoriesRequest(): EndPointServices? {
            return RetrofitBaseServices.apiClient?.create(EndPointServices::class.java)
        }
    }
}