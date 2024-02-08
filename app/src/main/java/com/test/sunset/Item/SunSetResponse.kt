package com.test.sunset.Item


import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

data class SunSetResponse(
    @SerializedName("results")
    @Expose
    val results: Results,
    @SerializedName("status")
    @Expose
    val status: String,
    @SerializedName("tzid")
    @Expose
    val tzid: String
)