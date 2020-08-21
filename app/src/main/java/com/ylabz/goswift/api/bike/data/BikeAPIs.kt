package com.zoewave.breathezy.api.bike.data

data class BikeAPIs(
    val `data`: API_Data,
    val last_updated: Int,
    val ttl: Int
)

data class API_Data(
    val en: En,
    val es: Es,
    val fr: Fr
)

data class En(
    val feeds: List<Feed>
)

data class Es(
    val feeds: List<FeedX>
)

data class Feed(
    val name: String,
    val url: String
)

data class FeedX(
    val name: String,
    val url: String
)

data class FeedXX(
    val name: String,
    val url: String
)

data class Fr(
    val feeds: List<FeedXX>
)