package com.test.sunset.itemss


import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

data class KaKaoRegionResponseX(
    @SerializedName("documents")
    @Expose
    val documents: List<Document>,
    @SerializedName("meta")
    @Expose
    val meta: Meta
)

data class Document(
    @SerializedName("address_name")
    @Expose
    val addressName: String,
    @SerializedName("code")
    @Expose
    val code: String,
    @SerializedName("region_1depth_name")
    @Expose
    val region1depthName: String,
    @SerializedName("region_2depth_name")
    @Expose
    val region2depthName: String,
    @SerializedName("region_3depth_name")
    @Expose
    val region3depthName: String,
    @SerializedName("region_4depth_name")
    @Expose
    val region4depthName: String,
    @SerializedName("region_type")
    @Expose
    val regionType: String,
    @SerializedName("x")
    @Expose
    val x: Double,
    @SerializedName("y")
    @Expose
    val y: Double
)

data class Meta(
    @SerializedName("total_count")
    @Expose
    val totalCount: Int
)
