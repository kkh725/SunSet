package com.test.sunset.Item

data class WeatherResponse(
    val header: Header,
    val body: Body
)

data class Header(
    val resultCode: String,
    val resultMsg: String
)

data class Body(
    val items: Items,
    val numOfRows: Int,
    val pageNo: Int,
    val totalCount: Int
)

data class Items(
    val item: Item
)

data class Item(
    val aste: String,
    val astm: String,
    val civile: String,
    val civilm: String,
    val latitude: String,
    val latitudeNum: String,
    val location: String,
    val locdate: String,
    val longitude: String,
    val longitudeNum: String,
    val moonrise: String,
    val moonset: String,
    val moontransit: String,
    val naute: String,
    val nautm: String,
    val sunrise: String,
    val sunset: String,
    val suntransit: String
)