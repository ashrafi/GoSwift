package com.ylabz.goswift.api.bike.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

data class BikeStationInfo(
    val `data`: BikeStationsInfo,
    val last_updated: Int,
    val ttl: Int
)

data class BikeStationsInfo(
    val stations: List<Station>
)

@JsonClass(generateAdapter = true)
data class Station(
    val capacity: Int = 0,
    @Json(name = "eightd_has_key_dispenser")
    val eightdHasKeyDispenser: Boolean = false,
    @Json(name = "eightd_station_services")
    val eightdStationServices: List<Any> = listOf(),
    @Json(name = "electric_bike_surcharge_waiver")
    val electricBikeSurchargeWaiver: Boolean = false,
    @Json(name = "external_id")
    val externalId: String = "",
    @Json(name = "has_kiosk")
    val hasKiosk: Boolean = false,
    val lat: Double = 0.0,
    val lon: Double = 0.0,
    val name: String = "",
    @Json(name = "region_id")
    val regionId: String = "",
    @Json(name = "rental_methods")
    val rentalMethods: List<String> = listOf(),
    @Json(name = "rental_uris")
    val rentalUris: RentalUris,
    @Json(name = "short_name")
    val shortName: String = "",
    @Json(name = "station_id")
    val stationId: String = "",
    @Json(name = "station_type")
    val stationType: String = ""
)

data class RentalUris(
    val android: String,
    val ios: String
)

data class Station_Working(
    val capacity: Int?,
    val eightd_has_key_dispenser: Boolean?,
    val eightd_station_services: List<String>?,
    val electric_bike_surcharge_waiver: Boolean?,
    val external_id: String?,
    val has_kiosk: Boolean?,
    val lat: Double?,
    val lon: Double?,
    val name: String?,
    val region_id: String?,
    val rental_methods: List<String>?,
    val rental_uris: RentalUris?,
    val short_name: String?,
    val station_id: String?,
    val station_type: String?
)