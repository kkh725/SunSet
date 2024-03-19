package com.test.sunset.itemss


import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

data class TourData(
    @SerializedName("fields")
    @Expose
    val fields: List<Field>,
    @SerializedName("records")
    @Expose
    val records: List<Record>
)

data class Record(
    @SerializedName("경도")
    @Expose
    val 경도: String,
    @SerializedName("공공편익시설정보")
    @Expose
    val 공공편익시설정보: String,
    @SerializedName("관광지구분")
    @Expose
    val 관광지구분: String,
    @SerializedName("관광지명")
    @Expose
    val 관광지명: String,
    @SerializedName("관광지소개")
    @Expose
    val 관광지소개: String,
    @SerializedName("관리기관명")
    @Expose
    val 관리기관명: String,
    @SerializedName("관리기관전화번호")
    @Expose
    val 관리기관전화번호: String,
    @SerializedName("데이터기준일자")
    @Expose
    val 데이터기준일자: String,
    @SerializedName("면적")
    @Expose
    val 면적: String,
    @SerializedName("소재지도로명주소")
    @Expose
    val 소재지도로명주소: String,
    @SerializedName("소재지지번주소")
    @Expose
    val 소재지지번주소: String,
    @SerializedName("수용인원수")
    @Expose
    val 수용인원수: String,
    @SerializedName("숙박시설정보")
    @Expose
    val 숙박시설정보: String,
    @SerializedName("운동및오락시설정보")
    @Expose
    val 운동및오락시설정보: String,
    @SerializedName("위도")
    @Expose
    val 위도: String,
    @SerializedName("접객시설정보")
    @Expose
    val 접객시설정보: String,
    @SerializedName("제공기관명")
    @Expose
    val 제공기관명: String,
    @SerializedName("제공기관코드")
    @Expose
    val 제공기관코드: String,
    @SerializedName("주차가능수")
    @Expose
    val 주차가능수: String,
    @SerializedName("지원시설정보")
    @Expose
    val 지원시설정보: String,
    @SerializedName("지정일자")
    @Expose
    val 지정일자: String,
    @SerializedName("휴양및문화시설정보")
    @Expose
    val 휴양및문화시설정보: String
)

data class Field(
    @SerializedName("id")
    @Expose
    val id: String
)