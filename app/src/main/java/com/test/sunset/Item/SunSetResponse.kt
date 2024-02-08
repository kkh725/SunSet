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

data class Results(
    @SerializedName("astronomical_twilight_begin")
    @Expose
    val astronomicalTwilightBegin: String,
    @SerializedName("astronomical_twilight_end")
    @Expose
    val astronomicalTwilightEnd: String,
    @SerializedName("civil_twilight_begin")
    @Expose
    val civilTwilightBegin: String,
    @SerializedName("civil_twilight_end")
    @Expose
    val civilTwilightEnd: String,
    @SerializedName("day_length")
    @Expose
    val dayLength: String,
    @SerializedName("nautical_twilight_begin")
    @Expose
    val nauticalTwilightBegin: String,
    @SerializedName("nautical_twilight_end")
    @Expose
    val nauticalTwilightEnd: String,
    @SerializedName("solar_noon")
    @Expose
    val solarNoon: String,
    @SerializedName("sunrise")
    @Expose
    val sunrise: String,
    @SerializedName("sunset")
    @Expose
    val sunset: String
)