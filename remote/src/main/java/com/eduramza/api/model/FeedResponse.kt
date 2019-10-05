package com.eduramza.api.model


import com.google.gson.annotations.SerializedName

data class FeedResponse(
    @SerializedName("category")
    val category: String,
    @SerializedName("list")
    val list: List<String>
)