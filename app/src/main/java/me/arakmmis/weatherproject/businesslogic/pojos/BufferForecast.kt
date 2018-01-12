package me.arakmmis.weatherproject.businesslogic.pojos

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class BufferForecast(

        @SerializedName("list")
        @Expose
        var list: List<ListWeather>,

        @SerializedName("city")
        @Expose
        var city: City) {

    class City(
            @SerializedName("id")
            @Expose
            var id: Int,

            @SerializedName("name")
            @Expose
            var name: String,

            @SerializedName("country")
            @Expose
            var country: String
    )

    class ListWeather(
            @SerializedName("dt")
            @Expose
            var dt: Int,

            @SerializedName("main")
            @Expose
            var main: Main,

            @SerializedName("weather")
            @Expose
            var weather: List<Weather>,

            @SerializedName("wind")
            @Expose
            var wind: Wind
    )

    class Main(
            @SerializedName("temp")
            @Expose
            var temp: Double
    )

    class Weather(
            @SerializedName("main")
            @Expose
            var main: String
    )

    class Wind(
            @SerializedName("speed")
            @Expose
            var speed: Double,

            @SerializedName("deg")
            @Expose
            var deg: Double
    )
}
