package com.zoewave.breathezy.api.bike.data


data class BikeSysInfo(
    val `data`: BikeDataSysInfo,
    val last_updated: Int,
    val ttl: Int
)

data class BikeDataSysInfo(
    val email: String,
    val language: String,
    val license_url: String,
    val name: String,
    val `operator`: String,
    val phone_number: String,
    val purchase_url: String,
    val short_name: String,
    val start_date: String,
    val system_id: String,
    val timezone: String,
    val url: String
)