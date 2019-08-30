package com.example.hackernews.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class StoryModel(
    @SerializedName("by")
    @Expose
    var by: String? = null,
    @SerializedName("descendants")
    @Expose
    var descendants: Int? = null,
    @SerializedName("id")
    @Expose
    var id: Int? = null,
    @SerializedName("kids")
    @Expose
    var kids: List<Int>? = null,
    @SerializedName("score")
    @Expose
    var score: Int? = null,
    @SerializedName("time")
    @Expose
    var time: Int? = null,
    @SerializedName("title")
    @Expose
    var title: String? = null,
    @SerializedName("type")
    @Expose
    var type: String? = null,
    @SerializedName("url")
    @Expose
    var url: String? = null
) : Parcelable {
    constructor(source: Parcel) : this(
        source.readString(),
        source.readValue(Int::class.java.classLoader) as Int?,
        source.readValue(Int::class.java.classLoader) as Int?,
        ArrayList<Int>().apply { source.readList(this, Int::class.java.classLoader) },
        source.readValue(Int::class.java.classLoader) as Int?,
        source.readValue(Int::class.java.classLoader) as Int?,
        source.readString(),
        source.readString(),
        source.readString()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(by)
        writeValue(descendants)
        writeValue(id)
        writeList(kids)
        writeValue(score)
        writeValue(time)
        writeString(title)
        writeString(type)
        writeString(url)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<StoryModel> = object : Parcelable.Creator<StoryModel> {
            override fun createFromParcel(source: Parcel): StoryModel = StoryModel(source)
            override fun newArray(size: Int): Array<StoryModel?> = arrayOfNulls(size)
        }
    }
}
