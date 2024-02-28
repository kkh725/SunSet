package com.test.sunset.itemss

import org.simpleframework.xml.Element
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

@Root(name = "response")
data class WeatherResponse(
    @field:Element(name = "header")
    var header: Header,

    @field:Element(name = "body")
    var body: Body
)

data class Header(
    @field:Element(name = "resultCode")
    var resultCode: String,

    @field:Element(name = "resultMsg")
    var resultMsg: String
)

data class Body(
    @field:Element(name = "items")
    var items: Items,

    @field:Element(name = "numOfRows")
    var numOfRows: Int,

    @field:Element(name = "pageNo")
    var pageNo: Int,

    @field:Element(name = "totalCount")
    var totalCount: Int
)

data class Items(
    @field:ElementList(name = "item", inline = true)
    var item: List<Item>
)

data class Item(
    @field:Element(name = "aste")
    var aste: String,

    @field:Element(name = "astm")
    var astm: String,

    @field:Element(name = "civile")
    var civile: String,

    @field:Element(name = "civilm")
    var civilm: String,

    @field:Element(name = "latitude")
    var latitude: String,

    @field:Element(name = "latitudeNum")
    var latitudeNum: String,

    @field:Element(name = "location")
    var location: String,

    @field:Element(name = "locdate")
    var locdate: String,

    @field:Element(name = "longitude")
    var longitude: String,

    @field:Element(name = "longitudeNum")
    var longitudeNum: String,

    @field:Element(name = "moonrise")
    var moonrise: String,

    @field:Element(name = "moonset")
    var moonset: String,

    @field:Element(name = "moontransit")
    var moontransit: String,

    @field:Element(name = "naute")
    var naute: String,

    @field:Element(name = "nautm")
    var nautm: String,

    @field:Element(name = "sunrise")
    var sunrise: String,

    @field:Element(name = "sunset")
    var sunset: String,

    @field:Element(name = "suntransit")
    var suntransit: String
)